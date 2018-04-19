package hr.com.vgv.verano.props;

import hr.com.vgv.verano.VrAppContext;
import org.cactoos.map.MapEntry;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

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
