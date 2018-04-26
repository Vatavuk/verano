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
package hr.com.vgv.verano.props;

import hr.com.vgv.verano.Props;
import java.io.File;
import java.io.IOException;
import java.util.Properties;
import org.cactoos.Func;
import org.cactoos.Input;
import org.cactoos.func.SolidFunc;
import org.cactoos.io.InputOf;
import org.cactoos.iterable.IterableOf;
import org.cactoos.scalar.Ternary;

/**
 * Configuration properties.
 * @author Vedran Grgo Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class DefaultProps implements Props {

    /**
     * Properties as singleton.
     */
    private static final Func<Input, Properties> SINGLETON =
        new SolidFunc<>(
            inp -> {
                final Properties props = new Properties();
                props.load(inp.stream());
                return props;
            }
        );

    /**
     * Input.
     */
    private final Input input;

    /**
     * Ctor.
     * @param file File
     */
    public DefaultProps(final File file) {
        this(new InputOf(file));
    }

    /**
     * Ctor.
     * @param input Input
     */
    public DefaultProps(final Input input) {
        this.input = input;
    }

    @Override
    public String value(final String property) throws Exception {
        return new Ternary<>(
            () -> this.has(property),
            () -> this.props().getProperty(property),
            () -> {
                throw new IOException(
                    String.format("Property %s not found", property)
                );
            }
        ).value();
    }

    @Override
    public String value(final String property, final String defaults)
        throws Exception {
        return new Ternary<>(
            () -> this.has(property),
            () -> this.value(property),
            () -> defaults
        ).value();
    }

    @Override
    public Iterable<String> values(final String prop) throws Exception {
        return new IterableOf<>(this.value(prop).split(";"));
    }

    @Override
    public boolean has(final String property) throws Exception {
        return this.props().containsKey(property);
    }

    /**
     * Make properties.
     * @return Properties
     * @throws Exception If fails
     */
    private Properties props() throws Exception {
        return DefaultProps.SINGLETON.apply(this.input);
    }
}
