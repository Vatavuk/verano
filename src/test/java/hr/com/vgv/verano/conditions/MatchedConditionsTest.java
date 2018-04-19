package hr.com.vgv.verano.conditions;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;


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
