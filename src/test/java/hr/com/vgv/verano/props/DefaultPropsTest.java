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

import java.io.IOException;
import org.cactoos.collection.CollectionOf;
import org.cactoos.io.InputOf;
import org.cactoos.text.TextOf;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Test case for {@link DefaultProps}.
 * @author Vedran Grgo Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 0.1
 * @checkstyle JavadocMethodCheck (500 lines)
 */
public final class DefaultPropsTest {

    @Test
    public void getsPropertyValue() throws Exception {
        final String property = "db.url";
        final String value = "http://localhost";
        MatcherAssert.assertThat(
            DefaultPropsTest.cfgProps(property, value).value(property),
            Matchers.equalTo(value)
        );
    }

    @Test
    public void getsPropertyValues() throws Exception {
        final String property = "db.hosts";
        final String value = "localhost;domain";
        MatcherAssert.assertThat(
            new CollectionOf<>(
                DefaultPropsTest.cfgProps(property, value).values(property)
            ).size(),
            Matchers.equalTo(2)
        );
    }

    @Test
    public void returnsDefaultPropertyValue() throws Exception {
        final String property = "unknown";
        final String value = "value";
        final String defaults = "default";
        MatcherAssert.assertThat(
            DefaultPropsTest.cfgProps(property, value).value("prop", defaults),
            Matchers.equalTo(defaults)
        );
    }

    @Test(expected = IOException.class)
    public void propertyNotFound() throws Exception {
        DefaultPropsTest.cfgProps("xx", "yy").value("val");
    }

    /**
     * Creates config properties.
     * @param property Property
     * @param value Value
     * @return CfgProps Props
     */
    private static DefaultProps cfgProps(final String property,
        final String value) {
        return new DefaultProps(
            new InputOf(new TextOf(String.format("%s=%s", property, value)))
        );
    }
}
