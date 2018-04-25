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
import java.util.ArrayList;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.cactoos.func.FuncWithFallback;
import org.cactoos.func.SolidFunc;
import org.cactoos.func.UncheckedFunc;
import org.cactoos.iterable.IterableOf;

/**
 * Command line properties.
 * @author Vedran Grgo Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class CliProps implements Props {

    /**
     * CommandLine as singleton.
     */
    private static final UncheckedFunc<String[], CommandLine> SINGLETON =
        new UncheckedFunc<>(
            new SolidFunc<>(
                arguments -> {
                    final Options options = new Options();
                    options.addOption(
                        "p", "profile", true, "Verano profile"
                    );
                    return new DefaultParser().parse(options, arguments);
                }
            )
        );

    /**
     * Arguments.
     */
    private final String[] args;

    /**
     * Ctor.
     * @param arguments Arguments
     */
    public CliProps(final String... arguments) {
        this.args = arguments;
    }

    @Override
    public String value(final String property) {
        return CliProps.SINGLETON.apply(this.args).getOptionValue(property);
    }

    @Override
    public String value(final String property, final String defaults) {
        final String result;
        if (this.has(property)) {
            result = this.value(property);
        } else {
            result = defaults;
        }
        return result;
    }

    @Override
    public Iterable<String> values(final String property) {
        Iterable<String> result = new ArrayList<>(0);
        if (this.has(property)) {
            result = new IterableOf<>(
                CliProps.SINGLETON.apply(this.args)
                    .getOptionValue(property).split(",")
            );
        }
        return result;
    }

    @Override
    public boolean has(final String property) {
        return new UncheckedFunc<>(
            new FuncWithFallback<String, Boolean>(
                input -> CliProps.SINGLETON.apply(this.args).hasOption(input),
                throwable -> false
            )
        ).apply(property);
    }
}
