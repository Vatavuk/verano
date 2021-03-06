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
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Test case for {@link AppProps}.
 *
 * @author Vedran Grgo Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 0.1
 * @checkstyle JavadocMethodCheck (500 lines)
 */
public final class AppPropsTest {

    @Test
    public void retrievesBaseProperty() throws Exception {
        MatcherAssert.assertThat(
            AppPropsTest.propsProd().value("host"),
            Matchers.equalTo("localhost")
        );
    }

    @Test
    public void retrievesOverriddenProperty() throws Exception {
        MatcherAssert.assertThat(
            AppPropsTest.propsProd().value("port"),
            Matchers.equalTo("9000")
        );
    }

    @Test
    public void retrievesMultipleProperties() throws Exception {
        MatcherAssert.assertThat(
            new CollectionOf<>(
                AppPropsTest.propsTest().values("formats")
            ).size(),
            Matchers.equalTo(2)
        );
    }

    @Test
    public void getsDefaultProperty() throws Exception {
        final String defaults = "default";
        MatcherAssert.assertThat(
            AppPropsTest.propsTest().value("unknown", defaults),
            Matchers.equalTo(defaults)
        );
    }

    @Test
    public void ignoresDefaultProperty() throws Exception {
        MatcherAssert.assertThat(
            AppPropsTest.propsTest().value("domain", "defaults"),
            Matchers.equalTo("hr.com.vgv")
        );
    }

    @Test(expected = IOException.class)
    public void propertyNotFound() throws Exception {
        AppPropsTest.propsTest().value("xxx");
    }

    @Test
    public void propertyFound() throws Exception {
        MatcherAssert.assertThat(
            AppPropsTest.propsTest().has("scheme"),
            Matchers.equalTo(true)
        );
    }

    @Test(expected = IOException.class)
    public void noResourcePropertiesFoundOnClasspath() throws Exception {
        new AppProps("").value("prop");
    }

    /**
     * Make prod props.
     * @return Props Resource props
     */
    private static AppProps propsProd() {
        return new AppProps("--profile=prod");
    }

    /**
     * Make test props.
     * @return
     */
    private static AppProps propsTest() {
        return new AppProps("--profile=test");
    }
}
