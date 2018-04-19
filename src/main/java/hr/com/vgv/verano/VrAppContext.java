package hr.com.vgv.verano;

import hr.com.vgv.verano.props.CliProps;
import java.util.HashMap;
import java.util.Map;
import org.cactoos.map.MapEntry;
import org.cactoos.map.MapEnvelope;

/**
 * <p>
 * <b>Title: AppContext </b>
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

public final class VrAppContext extends MapEnvelope<String, Props>
    implements AppContext {

    public VrAppContext() {
        this(new String[]{});
    }

    public VrAppContext(final String... args) {
        this(new MapEntry<>("userInput", new CliProps(args)));
    }

    /**
     * Ctor.
     * @param entries Map entries
     */
    @SuppressWarnings({"unchecked", "varargs"})
    public VrAppContext(final Map.Entry<String, Props>... entries) {
        super(() -> {
            final Map<String, Props> map = new HashMap<>(entries.length - 1);
            for (final Map.Entry<String, Props> entry: entries) {
                map.put(entry.getKey(), entry.getValue());
            }
            return map;
        });
    }
}
