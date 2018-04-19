package hr.com.vgv.verano.IT.dao;

import com.mongodb.MongoClient;
import hr.com.vgv.verano.VrCached;
import hr.com.vgv.verano.VrFactory;
import hr.com.vgv.verano.VrComponent;
import hr.com.vgv.verano.AppContext;
import hr.com.vgv.verano.conditions.HasProfile;
import org.cactoos.Scalar;

/**
 * <p>
 * <b>Title: CmMongoClient </b>
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
 * PA1 18.4.2018.
 * @since 18.4.2018.
 */

public class JnMongo extends VrFactory<Scalar<MongoClient>> {

    public JnMongo(final AppContext ctx) {
        super(
            ctx,
            new VrComponent<>(
                new VrCached<>(() -> new ProdMongo(ctx)),
                new HasProfile("prod")
            ),
            new VrComponent<>(
                new VrCached<>(() -> new TestMongo(ctx)),
                new HasProfile("test")
            )
        );
    }
}
