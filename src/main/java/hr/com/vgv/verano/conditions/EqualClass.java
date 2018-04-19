package hr.com.vgv.verano.conditions;

import org.cactoos.Scalar;
import org.cactoos.scalar.InheritanceLevel;


public class EqualClass implements Scalar<Boolean> {

    private final InheritanceLevel level;

    public EqualClass(final Class<?> derived, final Class<?> base) {
        this(new InheritanceLevel(derived, base));
    }

    public EqualClass(final InheritanceLevel lev) {
        this.level = lev;
    }

    @Override
    public Boolean value() {
        return this.level.value() == 0;
    }
}
