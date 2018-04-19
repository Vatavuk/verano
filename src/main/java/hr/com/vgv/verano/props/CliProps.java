package hr.com.vgv.verano.props;

import hr.com.vgv.verano.Props;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.cactoos.func.SolidFunc;
import org.cactoos.func.UncheckedFunc;

/**
 * <p>
 * <b>Title: CliProps </b>
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
 * PA1 16.4.2018.
 * @since 16.4.2018.
 */

//TODO: extract options to a ctor.
public final class CliProps implements Props {

    private static final UncheckedFunc<String[], CommandLine> SINGLETON =
        new UncheckedFunc<>(
            new SolidFunc<>(
                args -> {
                    Options options = new Options();
                    options.addOption("p","profile",
                        true, "Unite profile"
                    );
                    return new DefaultParser().parse(options, args);
                }
            )
        );

    private final String[] args;

    public CliProps(final String... arguments) {
        this.args = arguments;
    }

    @Override
    public String value(final String property) {
        return CliProps.SINGLETON.apply(this.args).getOptionValue(property);
    }

    @Override
    public String value(final String property, final String defaults) {
        throw new UnsupportedOperationException("#value()");
    }

    @Override
    public Iterable<String> values(final String property) {
        throw new UnsupportedOperationException("#values()");
    }

    @Override
    public boolean has(final String property) {
        return CliProps.SINGLETON.apply(this.args).hasOption(property);
    }
}
