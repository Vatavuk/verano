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
package hr.com.vgv.verano.props;

import org.cactoos.Scalar;
import org.cactoos.scalar.StickyScalar;

/**
 * Sticky scalar that can be refreshed dynamically.
 * @author Vedran Grgo Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @param <T> Type of input
 * @since 0.1
 */
public final class RefreshableScalar<T> implements Scalar<T> {

    /**
     * Refreshed scalar.
     */
    private StickyScalar<T> refreshed;

    /**
     * Original scalar.
     */
    private final Scalar<T> origin;

    /**
     * Ctor.
     * @param origin Original scalar
     */
    public RefreshableScalar(final Scalar<T> origin) {
        this.origin = origin;
        this.refreshed = new StickyScalar<>(origin);
    }

    @Override
    public T value() throws Exception {
        return this.refreshed.value();
    }

    /**
     * Refresh scalar.
     */
    public void refresh() {
        this.refreshed = new StickyScalar<>(this.origin);
    }
}
