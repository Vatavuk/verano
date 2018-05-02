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

import hr.com.vgv.verano.Component;
import java.io.IOException;
import org.cactoos.scalar.Ternary;

/**
 * AutoWired component.
 * @author Vedran Grgo Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @param <T> Return type
 * @since 0.1
 */
public final class AutoWiredComponent<T> extends ComponentTemplate<T> {

    /**
     * Ctor.
     * @param components Components
     */
    public AutoWiredComponent(final Iterable<Component<T>> components) {
        super(() -> new Ternary<>(
            () -> components.iterator().hasNext(),
            () -> components.iterator().next(),
            () -> {
                throw new IOException("No components found");
            }).value()
        );
    }

    /**
     * Ctor.
     * @param components Original components
     * @param wired Wired components
     */
    public AutoWiredComponent(final Iterable<Component<T>> components,
        final Iterable<Component<T>> wired) {
        super(() -> new Ternary<>(
            () -> wired.iterator().hasNext(),
            () -> wired.iterator().next(),
            () -> new AutoWiredComponent<>(components)
            ).value()
        );
    }
}
