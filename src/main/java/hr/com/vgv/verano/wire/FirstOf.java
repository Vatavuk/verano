/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2018 Vedran Grgo Vatavuk
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package hr.com.vgv.verano.wire;

import org.cactoos.Func;
import org.cactoos.Scalar;

/**
 * Returns first element in the list that met specified condition.
 * If element is not found it returns fallback value.
 * @author Vedran Grgo Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @param <T> Result type
 * @since 0.1
 */
public final class FirstOf<T> implements Scalar<T> {

    /**
     * Condition.
     */
    private final Func<T, Boolean> condition;

    /**
     * Source.
     */
    private final Iterable<T> source;

    /**
     * Fallback.
     */
    private final Scalar<T> fallback;

    /**
     * Ctor.
     * @param cond Condition
     * @param src Source
     * @param flbk Fallback
     */
    public FirstOf(final Func<T, Boolean> cond, final Iterable<T> src,
        final Scalar<T> flbk) {
        this.condition = cond;
        this.source = src;
        this.fallback = flbk;
    }

    @Override
    public T value() throws Exception {
        Scalar<T> result = this.fallback;
        for (final T element: this.source) {
            if (this.condition.apply(element)) {
                result = () -> element;
                break;
            }
        }
        return result.value();
    }
}
