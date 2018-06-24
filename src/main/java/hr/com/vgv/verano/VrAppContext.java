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

import hr.com.vgv.verano.props.AppProps;
import hr.com.vgv.verano.props.CliProps;
import hr.com.vgv.verano.props.DependencyProps;
import java.io.IOException;
import java.util.Map;
import org.cactoos.map.MapEntry;
import org.cactoos.map.MapOf;
import org.cactoos.scalar.Ternary;

/**
 * Application context.
 * @author Vedran Grgo Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 0.1
 * @checkstyle ClassDataAbstractionCouplingCheck (500 lines)
 */
public final class VrAppContext implements AppContext {

    /**
     * Map of properties.
     */
    private final Map<String, Props> map;

    /**
     * Ctor.
     */
    public VrAppContext() {
        this(new String[]{});
    }

    /**
     * Ctor.
     * @param args Arguments
     */
    @SuppressWarnings({"unchecked", "varargs"})
    public VrAppContext(final String... args) {
        this(
            new MapEntry<>("app", new AppProps(args)),
            new MapEntry<>("cli", new CliProps(args)),
            new MapEntry<>("dependencies", new DependencyProps())
        );
    }

    /**
     * Ctor.
     * @param entries Map entries
     */
    @SafeVarargs
    @SuppressWarnings({"unchecked", "varargs"})
    public VrAppContext(final Map.Entry<String, Props>... entries) {
        this(new MapOf<>(entries));
    }

    /**
     * Ctor.
     * @param map Map of properties
     */
    public VrAppContext(final Map<String, Props> map) {
        this.map = map;
    }

    @Override
    public Props props(final String type) throws Exception {
        return new Ternary<>(
            () -> this.map.containsKey(type),
            () -> this.map.get(type),
            () -> {
                throw new IOException(
                    String.format(
                        "No properties found for namespace %s", type
                    )
                );
            }
        ).value();
    }
}
