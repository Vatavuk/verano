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

import hr.com.vgv.verano.fakes.FkScalar;
import java.io.File;
import org.cactoos.collection.CollectionOf;
import org.cactoos.io.InputOf;
import org.cactoos.text.TextOf;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Test case for {@link XmlProps}.
 * @author Vedran Grgo Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 0.1
 * @checkstyle JavadocMethodCheck (500 lines)
 */
public final class XmlPropsTest {

    @Test
    public void getsPropertyValue() throws Exception {
        final String property = "prop";
        final String value = "value";
        MatcherAssert.assertThat(
            XmlPropsTest.props(property, value)
                .value(XmlPropsTest.prop(property)),
            Matchers.equalTo(value)
        );
    }

    @Test
    public void getsPropertyValues() throws Exception {
        final String property = "multi";
        final String value = "val";
        MatcherAssert.assertThat(
            new CollectionOf<>(XmlPropsTest.props(property, value)
                .values(XmlPropsTest.prop(property))
            ).size(),
            Matchers.equalTo(1)
        );
    }

    @Test
    public void getsDefaultProperty() throws Exception {
        final String property = "unknown";
        final String value = "no";
        final String defaults = "defaults";
        MatcherAssert.assertThat(
            XmlPropsTest.props(property, value).value("", defaults),
            Matchers.equalTo(defaults)
        );
    }

    @Test
    public void getsPropertyFromFile() throws Exception {
        MatcherAssert.assertThat(
            new XmlProps(
                new File(this.getClass().getResource("/qualifiers.xml")
                    .toURI()
                )
            ).value("//class/qualifier", "def"),
            Matchers.equalTo(FkScalar.class.getSimpleName())
        );
    }

    /**
     * Make props.
     * @param property Property
     * @param value Value
     * @return XmlProps props
     */
    private static XmlProps props(final String property, final String value) {
        return new XmlProps(
            new InputOf(
                new TextOf(
                    String.format("<%s>%s</%s>", property, value, property)
                )
            )
        );
    }

    /**
     * Make property.
     * @param property Property
     * @return String Property
     */
    private static String prop(final String property) {
        return String.format("//%s", property);
    }
}
