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
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.cactoos.map.MapEnvelope;

/**
 * Container of wired instances.
 * @author Vedran Grgo Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class WiredInstances extends MapEnvelope<String, Instance<?>> {

    /**
     * Map of wired instances.
     */
    private static final Map<String, Instance<?>> MAP =
        new ConcurrentHashMap<>(0);

    /**
     * Ctor.
     * @param namespace Namespace
     * @param instances Instances
     * @param <T> Input type
     */
    public <T> WiredInstances(final String namespace,
        final Iterable<Instance<T>> instances) {
        super(() -> {
            WiredInstances.MAP.put(namespace, new WiredInstance<>(instances));
            return WiredInstances.MAP;
        });
    }
}
