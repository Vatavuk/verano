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

import hr.com.vgv.verano.AppContext;
import hr.com.vgv.verano.Instance;
import hr.com.vgv.verano.Wire;
import hr.com.vgv.verano.Wiring;
import hr.com.vgv.verano.instances.ApplicableInstances;
import hr.com.vgv.verano.instances.CachedInstances;
import hr.com.vgv.verano.instances.WiredInstances;
import org.cactoos.iterable.IterableOf;
import org.cactoos.scalar.Ternary;

/**
 * Base wiring.
 * @param <T> Input type
 * @author Vedran Grgo Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class BaseWiring<T> implements Wiring<T> {

    /**
     * Application context.
     */
    private final AppContext context;

    /**
     * Components applicable for wiring.
     */
    private final Iterable<Instance<T>> instances;

    /**
     * Wires.
     */
    private final Iterable<Wire> wires;

    /**
     * Ctor.
     * @param cmps Components
     * @param context Application context
     */
    public BaseWiring(final Iterable<Instance<T>> cmps,
        final AppContext context) {
        this(context, cmps, new IterableOf<>());
    }

    /**
     * Ctor.
     * @param context Application context
     * @param instances Instances
     * @param wires Wires
     */
    public BaseWiring(final AppContext context,
        final Iterable<Instance<T>> instances, final Iterable<Wire> wires) {
        this.context = context;
        this.instances = instances;
        this.wires = wires;
    }

    @Override
    public Wiring<T> with(final Iterable<Wire> wires) {
        return new BaseWiring<>(this.context, this.instances, wires);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Instance<T> instance(final String namespace) throws Exception {
        final ApplicableInstances<T> candidates = new ApplicableInstances<>(
            new CachedInstances<>(this.instances, namespace),
            this.context
        );
        return (Instance<T>) new WiredInstances(
            namespace,
            new Ternary<>(
                () -> this.wires.iterator().hasNext(),
                () -> candidates.with(this.wires),
                () -> candidates
            ).value()
        ).get(namespace);
    }
}
