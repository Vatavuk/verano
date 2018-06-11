package hr.com.vgv.verano.props;

import org.cactoos.collection.Mapped;
import org.cactoos.iterable.IterableEnvelope;
import org.cactoos.iterable.IterableOf;
import org.cactoos.iterable.Joined;

/**
 * Property file names.
 * @author Vedran Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class PropertyFileNames extends IterableEnvelope<String> {

    public PropertyFileNames(final String prefix,
        final Iterable<String> suffixes) {
        super(() ->
            new Joined<String>(
                new IterableOf<>(
                    String.format("%s.%s", prefix, "properties")
                ),

                new Mapped<>(
                    suffix -> String.format(
                        "%s-%s.%s", prefix, suffix, "properties"
                    ),
                    suffixes
                )
            )
        );
    }
}
