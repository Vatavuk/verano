package hr.com.vgv.verano.props;

import hr.com.vgv.verano.VrAppContext;
import org.cactoos.map.MapEntry;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * <p>
 * <b>Title: UserInputTest </b>
 * </p>
 * <p>
 * <b> Description:
 * </b>
 * </p>
 * <p>
 * <b>Copyright:(</b> Copyright (c) ETK 2017
 * </p>
 * <p>
 * <b>Company:(</b> Ericsson Nikola Tesla d.d.
 * </p>
 * @author evedvat
 * @version PA1
 * <p>
 * <b>Version History:(</b>
 * </p>
 * <br>
 * PA1 16.4.2018.
 * @since 16.4.2018.
 */

public final class UserInputTest {

    @Test
    public void getsProfileFromUserInput() throws Exception {
        MatcherAssert.assertThat(
            new UserInput(
                new VrAppContext(
                    new MapEntry<>(
                        "userInput",
                        new CliProps("--profile=act")
                    )
                )
            ).has("profile"),
            Matchers.equalTo(true)
        );
    }
}
