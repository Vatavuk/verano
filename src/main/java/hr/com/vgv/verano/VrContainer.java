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

import hr.com.vgv.verano.conditions.TernaryRunnable;
import java.util.HashMap;
import java.util.Map;
import org.cactoos.map.MapEnvelope;

/**
 * Container of components.
 * @author Vedran Grgo Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class VrContainer extends MapEnvelope<String, Components<?>> {

    /**
     * Map of components.
     */
    private static final Map<String, Components<?>> MAP = new HashMap<>(0);

    /**
     * Ctor.
     * @param namespace Namespace
     * @param cmps Components
     */
    public VrContainer(final String namespace, final Components<?> cmps) {
        super(() -> {
            new TernaryRunnable(
                () -> !VrContainer.MAP.containsKey(namespace),
                () -> VrContainer.MAP.put(namespace, cmps)
            ).run();
            return VrContainer.MAP;
        });
    }
}
