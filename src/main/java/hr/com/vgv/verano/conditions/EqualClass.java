package hr.com.vgv.verano.conditions;

import org.cactoos.Scalar;
import org.cactoos.scalar.InheritanceLevel;

/**
 * <p>
 * <b>Title: EqualClass </b>
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
