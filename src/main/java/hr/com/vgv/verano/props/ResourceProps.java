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
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import org.cactoos.Func;
import org.cactoos.Input;
import org.cactoos.collection.CollectionOf;
import org.cactoos.collection.Mapped;
import org.cactoos.iterable.Filtered;
import org.cactoos.iterable.IterableOf;
import org.cactoos.iterable.StickyIterable;
import org.cactoos.list.ListOf;
import org.cactoos.scalar.Or;
import org.cactoos.scalar.Ternary;

/**
 * Properties fetched from resources.
 * @author Vedran Grgo Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 0.1
 * @checkstyle ClassDataAbstractionCouplingCheck (500 lines)
 */
public final class ResourceProps implements Props {

    /**
     * Resource properties (Cached).
     */
    private final Iterable<Props> resources;

    /**
     * Ctor.
     * @param suffixes Resource name suffixes
     */
    public ResourceProps(final String... suffixes) {
        this(new CollectionOf<>(suffixes));
    }

    /**
     * Ctor.
     * @param suffixes Resource name suffixes
     */
    public ResourceProps(final Collection<String> suffixes) {
        this("app", suffixes);
    }

    /**
     * Ctor.
     * @param prefix Resource name prefix
     * @param suffixes Resource name suffixes
     */
    public ResourceProps(final String prefix, final Iterable<String> suffixes) {
        this(new VrResources(prefix, suffixes));
    }

    /**
     * Ctor.
     * @param inputs Inputs
     */
    public ResourceProps(final Iterable<Input> inputs) {
        this.resources = new StickyIterable<>(
            new Mapped<>(DefaultProps::new, inputs)
        );
    }

    @Override
    public String value(final String property) throws Exception {
        final List<String> values = new ListOf<>(
            new Mapped<>(
                input -> input.value(property),
                new Filtered<>(
                    input -> input.has(property),
                    this.resources
                )
            )
        );
        return new Ternary<>(
            () -> !values.isEmpty(),
            () -> values.get(values.size() - 1),
            () -> {
                throw new IOException(
                    String.format(
                        "Property %s not found on classpath", property
                    )
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
    public Iterable<String> values(final String property) throws Exception {
        return new IterableOf<>(this.value(property).split(";"));
    }

    @Override
    public boolean has(final String property) throws Exception {
        return new Or(
            (Func<Props, Boolean>) props -> props.has(property),
            this.resources
        ).value();
    }
}
