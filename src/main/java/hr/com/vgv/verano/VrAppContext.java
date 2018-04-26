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

import hr.com.vgv.verano.props.CliProps;
import hr.com.vgv.verano.props.ResourceProps;
import hr.com.vgv.verano.props.VrProfiles;
import java.util.Map;
import org.cactoos.map.MapEntry;
import org.cactoos.map.MapEnvelope;
import org.cactoos.map.MapOf;

/**
 * Application context.
 * @author Vedran Grgo Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class VrAppContext extends MapEnvelope<String, Props> implements
    AppContext {

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
        this(new CliProps(args));
    }

    /**
     * Ctor.
     * @param options Options
     */
    public VrAppContext(final Props options) {
        this(
            new MapEntry<>("options", options),
            new MapEntry<>("config", new ResourceProps(new VrProfiles(options)))
        );
    }

    /**
     * Ctor.
     * @param entries Map entries
     */
    @SafeVarargs
    @SuppressWarnings({"unchecked", "varargs"})
    public VrAppContext(final Map.Entry<String, Props>... entries) {
        super(() -> new MapOf<>(entries));
    }
}
