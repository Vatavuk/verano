package hr.com.vgv.verano.conditions;

import hr.com.vgv.verano.AppContext;
import hr.com.vgv.verano.Condition;


public final class HasQualifier implements Condition {

    private final String value;

    public HasQualifier(final String qualifier) {
        this.value = qualifier;
    }

    @Override
    public Boolean check(final AppContext context) {
        return false;
    }

    @Override
    public Boolean check(final Condition condition) {
        return new MatchedConditions(this, condition).value();
    }

    @Override
    public String toString() {
        return this.value;
    }
}
