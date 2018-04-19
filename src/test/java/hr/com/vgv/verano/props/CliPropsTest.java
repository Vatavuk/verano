package hr.com.vgv.verano.props;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;


public class CliPropsTest {

    @Test
    public void getsProfileFromArguments() {
        MatcherAssert.assertThat(
            new CliProps("--profile=act").value("profile"),
            Matchers.equalTo("act")
        );
    }
}
