package hr.com.vgv.verano.conditions;

import hr.com.vgv.verano.Condition;
import org.cactoos.Scalar;

/**
 * <p>
 * <b>Title: MatchConditions </b>
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

public final class MatchedConditions implements Scalar<Boolean> {

    private final Condition first;

    private final Condition second;

    public MatchedConditions(final Condition base, final Condition compared) {
        this.first = base;
        this.second = compared;
    }

    @Override
    public Boolean value() {
        final boolean result;
        if (
            new EqualClass(
                this.first.getClass(),
                this.second.getClass()
            ).value()) {
            result = this.first.toString().equals(this.second.toString());
        } else {
            result = false;
        }
        return result;
    }
}
