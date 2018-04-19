package hr.com.vgv.verano.conditions;

import hr.com.vgv.verano.AppContext;
import hr.com.vgv.verano.Condition;
import hr.com.vgv.verano.Props;
import hr.com.vgv.verano.props.UserInput;


public final class HasProfile implements Condition {

    private final String value;

    public HasProfile(final String profile) {
        this.value = profile;
    }

    @Override
    //TODO: refactor runtime exception
    public Boolean check(final AppContext context) {
        try {
            final Props props = new UserInput(context);
            return props.has("profile") &&
                props.value("profile").equals(this.value);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
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
