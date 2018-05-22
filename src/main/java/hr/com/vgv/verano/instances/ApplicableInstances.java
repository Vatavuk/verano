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
package hr.com.vgv.verano.instances;

import hr.com.vgv.verano.AppContext;
import hr.com.vgv.verano.Instance;
import hr.com.vgv.verano.Wire;
import org.cactoos.iterable.Filtered;
import org.cactoos.iterable.IterableEnvelope;
import org.cactoos.iterable.IterableOf;
import org.cactoos.iterable.Joined;

/**
 * Components that are applicable for wiring.
 *
 * @author Vedran Grgo Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @param <T> Return type
 * @since 0.1
 */
public final class ApplicableInstances<T> extends
    IterableEnvelope<Instance<T>> {

    /**
     * Original instances.
     */
    private final Iterable<Instance<T>> origin;

    /**
     * Components applicable for wiring.
     */
    private final Iterable<Instance<T>> candidates;

    /**
     * Ctor.
     * @param components Components
     * @param context Application context
     */
    public ApplicableInstances(final Iterable<Instance<T>> components,
        final AppContext context) {
        this(components, new Filtered<>(
            cmp -> cmp.applicable(context), components
            )
        );
    }

    /**
     * Ctor.
     * @param components Components
     * @param applicable Components applicable for wiring
     */
    public ApplicableInstances(final Iterable<Instance<T>> components,
        final Iterable<Instance<T>> applicable) {
        super(() -> applicable);
        this.origin = components;
        this.candidates = applicable;
    }

    /**
     * Creates new iterable of applicable instances based on additional wires.
     * @param wires Wires
     * @return ApplicableComponents Components applicable for wiring
     */
    public ApplicableInstances<T> with(final Wire... wires) {
        return this.with(new IterableOf<>(wires));
    }

    /**
     * Creates new iterable of applicable instances based on additional wires.
     * @param wires Wires
     * @return ApplicableComponents Components applicable for wiring
     */
    public ApplicableInstances<T> with(final Iterable<Wire> wires) {
        return new ApplicableInstances<>(
            this.origin,
            new Joined<Instance<T>>(
                new Filtered<>(
                    input -> input.applicable(wires),
                    this.origin
                ),
                this.candidates
            )
        );
    }
}
