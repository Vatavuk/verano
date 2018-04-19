package hr.com.vgv.verano.conditions;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * <p>
 * <b>Title: MatchedConditionsTest </b>
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

public final class MatchedConditionsTest {

    @Test
    public void conditionsMatched() {
        MatcherAssert.assertThat(
            new MatchedConditions(
                new HasProfile("act"),
                new HasProfile("act")
            ).value(),
            Matchers.equalTo(true)
        );
    }

    @Test
    public void conditionValuesDoesntMatch() {
        MatcherAssert.assertThat(
            new MatchedConditions(
                new HasProfile("act"),
                new HasProfile("dev")
            ).value(),
            Matchers.equalTo(false)
        );
    }

    @Test
    public void conditionsDoesntMatch() {
        MatcherAssert.assertThat(
            new MatchedConditions(
                new HasProfile("act"),
                new HasQualifier("act")
            ).value(),
            Matchers.equalTo(false)
        );
    }
}
