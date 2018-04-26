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
import org.cactoos.collection.CollectionOf;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Test case for {@link CliProps}.
 *
 * @author Vedran Grgo Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 0.1
 * @checkstyle JavadocMethodCheck (500 lines)
 */
public final class CliPropsTest {

    @Test
    public void getsProfile() throws Exception {
        final String profile = "act";
        MatcherAssert.assertThat(
            new ProfileOf(CliPropsTest.props(profile)).value(),
            Matchers.equalTo(profile)
        );
    }

    @Test
    public void getsDefaultProfile() {
        final String defaults = "default";
        MatcherAssert.assertThat(
            new CliProps("").value("value", defaults),
            Matchers.equalTo(defaults)
        );
    }

    @Test
    public void ignoresDefaultProfile() throws Exception {
        final String profile = "prod";
        MatcherAssert.assertThat(
            new ProfileOf(CliPropsTest.props(profile)).value("def"),
            Matchers.equalTo(profile)
        );
    }

    @Test
    public void getsDefaultProfiles() throws Exception {
        MatcherAssert.assertThat(
            new CollectionOf<>(
                new ProfileOf(CliPropsTest.props("dev,test")).values()
            ).size(),
            Matchers.equalTo(2)
        );
    }

    @Test
    public void argumentDoesntExist() {
        MatcherAssert.assertThat(
            new CliProps("--unknown=un").has("other"),
            Matchers.equalTo(false)
        );
    }

    @Test
    public void noProfileValuesFound() {
        MatcherAssert.assertThat(
            new CollectionOf<>(
                CliPropsTest.props("").values("unknown")
            ).isEmpty(),
            Matchers.equalTo(true)
        );
    }

    /**
     * Create command lin properties from given profile.
     * @param profile Profile
     * @return CliProps Properties
     */
    private static CliProps props(final String profile) {
        return new CliProps(String.format("--profile=%s", profile));
    }

    /**
     * Profile.
     */
    private static final class ProfileOf {

        /**
         * Properties.
         */
        private final Props props;

        /**
         * Profile value.
         */
        private final String profile;

        /**
         * Ctor.
         * @param props Properties
         */
        ProfileOf(final Props props) {
            this.props = props;
            this.profile = "profile";
        }

        public String value() throws Exception {
            return this.props.value(this.profile);
        }

        public String value(final String defaults) throws Exception {
            return this.props.value(this.profile, defaults);
        }

        public Iterable<String> values() throws Exception {
            return this.props.values(this.profile);
        }
    }
}
