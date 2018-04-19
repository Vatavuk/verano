package hr.com.vgv.verano.conditions;

import hr.com.vgv.verano.AppContext;
import hr.com.vgv.verano.Condition;

/**
 * <p>
 * <b>Title: HasQualifier </b>
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
