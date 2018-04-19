package hr.com.vgv.verano.conditions;

import hr.com.vgv.verano.VrAppContext;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * <p>
 * <b>Title: HasQualifierTest </b>
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
 * PA1 19.4.2018.
 * @since 19.4.2018.
 */

public final class HasQualifierTest {

    @Test
    public void matchesQualifiers() {
        MatcherAssert.assertThat(
            new HasQualifier("desc").check(new HasQualifier("desc")),
            Matchers.equalTo(true)
        );
    }

    @Test
    public void qualifierNotMatched() {
        MatcherAssert.assertThat(
            new HasQualifier("sth").check(new VrAppContext()),
            Matchers.equalTo(false)
        );
    }
}
