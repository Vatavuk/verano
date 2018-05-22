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
package hr.com.vgv.verano;

import hr.com.vgv.verano.wiring.BaseWiring;
import org.cactoos.iterable.IterableOf;

/**
 * Factory.
 * @author Vedran Grgo Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @param <T> Return type.
 * @since 0.1
 * @checkstyle AbstractClassNameCheck (500 lines)
 */
@SuppressWarnings("PMD.AbstractNaming")
public class VrComponent<T> implements Component<T> {

    /**
     * Wiring functionality.
     */
    private final Wiring<T> wired;

    /**
     * Ctor.
     * @param ctx Context
     * @param cmps Components
     */
    @SafeVarargs
    @SuppressWarnings({"unchecked", "varargs"})
    public VrComponent(final AppContext ctx, final Instance<T>... cmps) {
        this(ctx, new IterableOf<>(cmps));
    }

    /**
     * Ctor.
     * @param ctx Context
     * @param cmps Components
     */
    public VrComponent(final AppContext ctx, final Iterable<Instance<T>> cmps) {
        this(new BaseWiring<>(cmps, ctx));
    }

    /**
     * Ctor.
     * @param wiring Wiring functionality
     */
    public VrComponent(final Wiring<T> wiring) {
        this.wired = wiring;
    }

    /**
     * Creates new factory with additional wires.
     * @param wires Wires
     * @return Factory Factory
     */
    public final VrComponent<T> with(final Wire... wires) {
        return this.with(new IterableOf<>(wires));
    }

    /**
     * Creates new factory with additional wires.
     * @param wires Wires
     * @return Factory Factory
     */
    public final VrComponent<T> with(final Iterable<Wire> wires) {
        return new VrComponent<>(this.wired.with(wires));
    }

    @Override
    @SuppressWarnings("unchecked")
    public final T instance() throws Exception {
        return this.wired.instance(this.getClass().getName());
    }
}
