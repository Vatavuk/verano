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
import hr.com.vgv.verano.Instance;
import hr.com.vgv.verano.Wire;
import hr.com.vgv.verano.Wiring;
import hr.com.vgv.verano.instances.VrInstance;
import hr.com.vgv.verano.wiring.BaseWiring;
import org.cactoos.Scalar;
import org.cactoos.iterable.IterableOf;
import org.cactoos.iterable.Mapped;

/**
 * Component that can control instance lifecycle.
 *
 * @author Vedran Grgo Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @param <T> Input type.
 * @since 0.1
 * @checkstyle AbstractClassNameCheck (500 lines)
 */
public class VrRefreshableComponent<T> implements Component<T> {

    /**
     * Wiring functionality.
     */
    private final Wiring<T> wiring;

    /**
     * Ctor.
     * @param ctx Application context
     * @param instances Instances
     */
    @SafeVarargs
    @SuppressWarnings({"unchecked", "varargs"})
    public VrRefreshableComponent(final AppContext ctx,
        final Scalar<T>... instances) {
        this(ctx, new Mapped<>(input -> new VrInstance<>(input), instances));
    }

    /**
     * Ctor.
     * @param ctx Context
     * @param cmps Components
     */
    @SafeVarargs
    @SuppressWarnings({"unchecked", "varargs"})
    public VrRefreshableComponent(final AppContext ctx,
        final Instance<T>... cmps) {
        this(ctx, new IterableOf<>(cmps));
    }

    /**
     * Ctor.
     * @param ctx Context
     * @param cmps Components
     */
    public VrRefreshableComponent(final AppContext ctx,
        final Iterable<Instance<T>> cmps) {
        this(new BaseWiring<>(ctx, cmps));
    }

    /**
     * Ctor.
     * @param wiring Wiring functionality
     */
    public VrRefreshableComponent(final Wiring<T> wiring) {
        this.wiring = wiring;
    }

    /**
     * Creates new factory with additional wires.
     * @param wires Wires
     * @return Factory Factory
     */
    public final VrRefreshableComponent<T> with(final Wire... wires) {
        return this.with(new IterableOf<>(wires));
    }

    /**
     * Creates new component with additional wires.
     * @param wires Wires
     * @return VrComponent Component
     */
    public final VrRefreshableComponent<T> with(final Iterable<Wire> wires) {
        return new VrRefreshableComponent<>(this.wiring.with(wires));
    }

    @Override
    @SuppressWarnings("unchecked")
    public final T instance() throws Exception {
        return this.wiring.wire(this.getClass().getName()).value();
    }

    /**
     * Refreshes component.
     * @throws Exception If fails
     */
    public final void refresh() throws Exception {
        this.refreshInstance();
    }

    /**
     * Returns refreshed component instance value.
     * @return T Instance value
     * @throws Exception If fails
     */
    public final T refreshed() throws Exception {
        final Instance<T> instance = this.refreshInstance();
        return instance.value();
    }

    /**
     * Refreshes component instance.
     * @return Instance Instance
     * @throws Exception If fails
     */
    private Instance<T> refreshInstance() throws Exception {
        final Instance<T> instance = this.wiring.wire(
            this.getClass().getName()
        );
        instance.refresh();
        return instance;
    }
}
