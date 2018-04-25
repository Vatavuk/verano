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

import java.util.Map;

/**
 * Cached components.
 *
 * @author Vedran Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @param <T> Return type
 * @since 1.0
 */
public final class VrCachedComponents<T> implements Components<T> {

    /**
     * Namespace.
     */
    private final String namespace;

    /**
     * Cache.
     */
    private final Map<String, Components<?>> cache;

    /**
     * Ctor.
     * @param namespace Namespace
     * @param cmps Components
     */
    public VrCachedComponents(final String namespace,
        final Components<T> cmps) {
        this(namespace, new VrContainer(namespace, cmps));
    }

    /**
     * Ctor.
     * @param namespace Namespace
     * @param map Cache
     */
    public VrCachedComponents(final String namespace,
        final Map<String, Components<?>> map) {
        this.namespace = namespace;
        this.cache = map;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Component<T> get(final AppContext context) throws Exception {
        return (Component<T>) this.cache.get(this.namespace).get(context);
    }
}
