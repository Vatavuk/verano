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

import hr.com.vgv.verano.fakes.FkProps;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Test case for {@link PropsEnvelope}.
 * @author Vedran Grgo Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 0.1
 * @checkstyle JavadocMethodCheck (500 lines)
 */
public final class PropsEnvelopeTest {

    @Test
    public void getsProperty() throws Exception {
        final String prop = "prop";
        MatcherAssert.assertThat(
            new PropsEnvelopeTest.CustomProps(prop).value(""),
            Matchers.equalTo(prop)
        );
    }

    @Test
    public void getsProperties() throws Exception {
        final String prop = "other";
        MatcherAssert.assertThat(
            new PropsEnvelopeTest.CustomProps(prop)
                .values("").iterator().next(),
            Matchers.equalTo(prop)
        );
    }

    @Test
    public void defaultProperties() throws Exception {
        final String defaults = "defaults";
        MatcherAssert.assertThat(
            new PropsEnvelopeTest.CustomProps("")
                .value("", defaults),
            Matchers.equalTo(defaults)
        );
    }

    /**
     * Custom props.
     */
    private static class CustomProps extends PropsEnvelope {

        /**
         * Ctor.
         * @param prop Property
         */
        CustomProps(final String prop) {
            super(new FkProps(prop));
        }
    }
}
