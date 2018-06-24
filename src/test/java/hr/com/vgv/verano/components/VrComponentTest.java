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
package hr.com.vgv.verano.components;

import hr.com.vgv.verano.AppContext;
import hr.com.vgv.verano.VrAppContext;
import hr.com.vgv.verano.instances.VrInstance;
import hr.com.vgv.verano.wiring.ProfileWire;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.junit.Test;

/**
 * Test case for {@link VrComponent}.
 * @author Vedran Grgo Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @checkstyle JavadocMethodCheck (500 lines)
 * @since 0.1
 */
@SuppressWarnings("PMD.AvoidDuplicateLiterals")
public final class VrComponentTest {

    @Test
    public void retrievesInstance() throws Exception {
        MatcherAssert.assertThat(
            new VrComponent<Boolean>(
                new VrAppContext(),
                () -> true
            ).value(),
            new IsEqual<>(true)
        );
    }

    @Test
    public void retrievesInstanceWithWireCondition() throws Exception {
        MatcherAssert.assertThat(
            new VrComponentTest.CustomComponent(
                new VrAppContext("--profile=test")
            ).value(),
            new IsEqual<>(true)
        );
    }

    @Test
    public void retrievesInstanceWithSpecifiedWireCondition() throws Exception {
        MatcherAssert.assertThat(
            new VrComponentTest.CustomComponent(
                new VrAppContext()
            ).with(new ProfileWire("test")).value(),
            new IsEqual<>(true)
        );
    }

    @Test
    public void handlesMultipleInstances() throws Exception {
        final CustomComponent component =
            new CustomComponent(new VrAppContext());
        MatcherAssert.assertThat(
            component.with(new ProfileWire("test")).value(),
            new IsEqual<>(true)
        );
        MatcherAssert.assertThat(
            component.with(new ProfileWire("dev")).value(),
            new IsEqual<>(false)
        );
    }

    /**
     * Custom component.
     */
    private static class CustomComponent extends VrComponent<Boolean> {

        /**
         * Ctor.
         * @param ctx Application context
         */
        CustomComponent(final AppContext ctx) {
            super(ctx,
                new VrInstance<>(() -> false, new ProfileWire("dev")),
                new VrInstance<>(() -> true, new ProfileWire("test"))
            );
        }
    }
}
