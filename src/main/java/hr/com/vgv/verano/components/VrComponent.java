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
package hr.com.vgv.verano.components;

import hr.com.vgv.verano.AppContext;
import hr.com.vgv.verano.Component;
import hr.com.vgv.verano.Wire;
import hr.com.vgv.verano.wire.MatchedWires;
import org.cactoos.Func;
import org.cactoos.Scalar;
import org.cactoos.iterable.IterableOf;
import org.cactoos.scalar.Or;

/**
 * Component.
 * @author Vedran Grgo Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @param <T> Return type.
 * @since 0.1
 */
public final class VrComponent<T> implements Component<T> {

    /**
     * Instance.
     */
    private final Scalar<T> scalar;

    /**
     * Conditions.
     */
    private final Iterable<Wire> wires;

    /**
     * Ctor.
     * @param instance Instance
     * @param cnds Conditioins
     */
    public VrComponent(final Scalar<T> instance,
        final Wire... cnds) {
        this(instance, new IterableOf<>(cnds));
    }

    /**
     * Ctor.
     * @param instance Instance
     * @param ext Wires
     */
    public VrComponent(final Scalar<T> instance,
        final Iterable<Wire> ext) {
        this.scalar = instance;
        this.wires = ext;
    }

    @Override
    public boolean isActive(final AppContext context) throws Exception {
        return new Or(
            (Func<Wire, Boolean>) input -> input.isActive(context),
            this.wires
        ).value();
    }

    @Override
    public boolean isActive(final Iterable<Wire> ext)
        throws Exception {
        return new Or(
            (Func<Wire, Boolean>) input -> new Or(
                (Func<Wire, Boolean>) wire -> new MatchedWires(
                    input, wire
                ).value(),
                ext
            ).value(),
            this.wires
        ).value();
    }

    @Override
    public T instance() throws Exception {
        return this.scalar.value();
    }
}
