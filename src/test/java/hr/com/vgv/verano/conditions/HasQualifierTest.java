package hr.com.vgv.verano.conditions;

import hr.com.vgv.verano.VrAppContext;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;


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
