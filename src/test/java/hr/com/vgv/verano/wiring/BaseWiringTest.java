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
package hr.com.vgv.verano.wiring;

import hr.com.vgv.verano.Instance;
import hr.com.vgv.verano.VrAppContext;
import hr.com.vgv.verano.fakes.FkScalar;
import hr.com.vgv.verano.instances.VrInstance;
import java.io.IOException;
import org.cactoos.iterable.IterableOf;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Test case for {@link BaseWiring}.
 * @author Vedran Grgo Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @checkstyle JavadocMethodCheck (500 lines)
 * @checkstyle ClassDataAbstractionCouplingCheck (500 lines)
 * @since 0.1
 */
@SuppressWarnings("PMD.AvoidDuplicateLiterals")
public final class BaseWiringTest {

    @Test
    public void wiresPlainInstance() throws Exception {
        MatcherAssert.assertThat(
            new BaseWiring<>(
                new VrAppContext(),
                new IterableOf<Instance<Boolean>>(
                    new VrInstance<>(new FkScalar())
                )
            ).wire("namespace").value(),
            new IsEqual<>(true)
        );
    }

    @Test
    public void wiresInstanceWithWireCondition() throws Exception {
        MatcherAssert.assertThat(
            new BaseWiring<>(
                new VrAppContext("--profile=test"),
                new IterableOf<Instance<Boolean>>(
                    new VrInstance<>(new FkScalar(), new ProfileWire("test"))
                )
            ).wire("namespace2").value(),
            new IsEqual<>(true)
        );
    }

    @Test
    public void wiresInstanceWithDynamicWireCondition() throws Exception {
        MatcherAssert.assertThat(
            new BaseWiring<>(
                new VrAppContext("--profile=dev"),
                new IterableOf<Instance<Boolean>>(
                    new VrInstance<>(() -> false, new ProfileWire("dev")),
                    new VrInstance<>(new FkScalar(), new ProfileWire("test"))
                )
            ).with(new IterableOf<>(new ProfileWire("test")))
                .wire("namespace3").value(),
            new IsEqual<>(true)
        );
    }

    @Test(expected = IOException.class)
    @Ignore
    public void throwsExceptionIfWiringConditionsAreNotMet() throws Exception {
        new BaseWiring<>(
            new VrAppContext(),
            new IterableOf<Instance<Boolean>>(
                new VrInstance<>(() -> false, new QualifierWire("dev"))
            )
        ).with(new IterableOf<>(new QualifierWire("test")))
            .wire("namespace4").value();
    }
}
