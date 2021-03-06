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

import hr.com.vgv.verano.Instance;
import java.util.ArrayList;
import java.util.Map;
import org.cactoos.iterable.IterableEnvelope;
import org.cactoos.iterable.Mapped;

/**
 * Cached instances.
 * @author Vedran Grgo Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @param <T> Return type
 * @since 0.1
 */
public final class CachedInstances<T> extends IterableEnvelope<Instance<T>> {

    /**
     * Ctor.
     * @param instances Instances
     * @param component Component name
     */
    public CachedInstances(final Iterable<Instance<T>> instances,
        final String component) {
        this(component,
            new ComponentsContainer(
                component, new Mapped<>(input -> input, instances)
            )
        );
    }

    /**
     * Ctor.
     * @param component Component name
     * @param map Map of instances
     */
    @SuppressWarnings("unchecked")
    public CachedInstances(final String component,
        final Map<String, Iterable<Instance<?>>> map) {
        super(() -> new Mapped<>(
            input -> (Instance<T>) input,
            map.getOrDefault(component, new ArrayList<>(0))
            )
        );
    }
}
