package hr.com.vgv.verano.conditions;

import hr.com.vgv.verano.Condition;
import org.cactoos.Scalar;


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
