package hr.com.vgv.verano.props;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * <p>
 * <b>Title: CliPropsTest </b>
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

public class CliPropsTest {

    @Test
    public void getsProfileFromArguments() {
        MatcherAssert.assertThat(
            new CliProps("--profile=act").value("profile"),
            Matchers.equalTo("act")
        );
    }
}
