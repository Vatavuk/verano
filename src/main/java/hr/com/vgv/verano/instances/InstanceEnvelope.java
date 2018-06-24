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
import org.cactoos.Scalar;

/**
 * Envelope for instances.
 *
 * @author Vedran Grgo Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @param <T> Return type
 * @since 0.1
 * @checkstyle AbstractClassNameCheck (500 lines)
 */
@SuppressWarnings("PMD.AbstractNaming")
public abstract class InstanceEnvelope<T> implements Instance<T> {

    /**
     * Original component.
     */
    private final Scalar<Instance<T>> origin;

    /**
     * Ctor.
     * @param instance Component
     */
    public InstanceEnvelope(final Scalar<Instance<T>> instance) {
        this.origin = instance;
    }

    @Override
    public final boolean applicable(final AppContext context,
        final String component) throws Exception {
        return this.origin.value().applicable(context, component);
    }

    @Override
    public final boolean applicable(final Iterable<Wire> wires)
        throws Exception {
        return this.origin.value().applicable(wires);
    }

    @Override
    public final T value() throws Exception {
        return this.origin.value().value();
    }

    @Override
    public final void refresh() throws Exception {
        this.origin.value().refresh();
    }
}
