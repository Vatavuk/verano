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

import hr.com.vgv.verano.components.AutoWiredComponent;
import hr.com.vgv.verano.components.CachedComponents;
import hr.com.vgv.verano.components.DynamicComponents;
import hr.com.vgv.verano.components.WiredComponents;
import org.cactoos.Func;
import org.cactoos.collection.Joined;
import org.cactoos.func.UncheckedFunc;
import org.cactoos.iterable.IterableOf;

/**
 * Factory.
 * @param <T> Return type.
 * @author Vedran Grgo Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @checkstyle AbstractClassNameCheck (500 lines)
 * @since 0.1
 */
@SuppressWarnings("PMD.AbstractNaming")
public class VrFactory<T> implements Factory<T> {

    /**
     * Components.
     */
    private final Iterable<Component<T>> components;

    /**
     * Wired components.
     */
    private final Iterable<Component<T>> wired;

    /**
     * Func that chooses component from components iterable.
     */
    private final Func<Iterable<Component<T>>, Component<T>> func;

    /**
     * Ctor.
     * @param ctx Context
     * @param cmps Components
     */
    @SafeVarargs
    @SuppressWarnings({"unchecked", "varargs"})
    public VrFactory(final AppContext ctx, final Component<T>... cmps) {
        this(ctx, new IterableOf<>(cmps));
    }

    /**
     * Ctor.
     * @param ctx Context
     * @param cmps Components
     */
    public VrFactory(final AppContext ctx, final Iterable<Component<T>> cmps) {
        this(cmps,
            input -> new CachedComponents<>(
                input,
                new WiredComponents<>(cmps, ctx)
            ),
            input -> new AutoWiredComponent<>(cmps, input)
        );
    }

    /**
     * Ctor.
     * @param cmps Components
     * @param wcmps Wired components
     * @param function Function that chooses component from components iterable
     */
    public VrFactory(final Iterable<Component<T>> cmps,
        final Func<String, Iterable<Component<T>>> wcmps,
        final Func<Iterable<Component<T>>, Component<T>> function) {
        this.components = cmps;
        this.wired = new UncheckedFunc<>(wcmps)
            .apply(this.getClass().getName());
        this.func = function;
    }

    /**
     * Creates Factory with additional wiring conditions.
     * @param wires Wires
     * @return Factory Factory
     */
    public final Factory<T> with(final Wire... wires) {
        return new VrFactory<>(
            this.components,
            input -> new Joined<Component<T>>(
                new DynamicComponents<>(this.components, wires),
                this.wired
            ),
            this.func
        );
    }

    @Override
    @SuppressWarnings("unchecked")
    public final T instance() throws Exception {
        return this.func.apply(this.wired).instance();
    }
}
