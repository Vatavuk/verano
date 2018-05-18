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
package hr.com.vgv.verano.wiring;

import hr.com.vgv.verano.Wire;
import org.cactoos.Scalar;
import org.cactoos.scalar.Ternary;
import org.cactoos.scalar.UncheckedScalar;

/**
 * Represents matching between two conditions.
 * @author Vedran Grgo Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class MatchedWires implements Scalar<Boolean> {

    /**
     * First condition.
     */
    private final Wire first;

    /**
     * Second condition.
     */
    private final Wire second;

    /**
     * Ctor.
     * @param base Base condition
     * @param compared Comparing condition
     */
    public MatchedWires(final Wire base, final Wire compared) {
        this.first = base;
        this.second = compared;
    }

    @Override
    public Boolean value() {
        return new UncheckedScalar<>(
            new Ternary<>(
                new EqualClass(this.first.getClass(), this.second.getClass()),
                () -> this.first.toString().equals(this.second.toString()),
                () -> false
            )
        ).value();
    }
}
