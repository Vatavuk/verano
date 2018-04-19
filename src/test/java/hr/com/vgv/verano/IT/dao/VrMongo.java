package hr.com.vgv.verano.IT.dao;

import com.mongodb.MongoClient;
import hr.com.vgv.verano.VrCached;
import hr.com.vgv.verano.VrFactory;
import hr.com.vgv.verano.VrComponent;
import hr.com.vgv.verano.AppContext;
import hr.com.vgv.verano.conditions.HasProfile;
import org.cactoos.Scalar;


public class VrMongo extends VrFactory<Scalar<MongoClient>> {

    public VrMongo(final AppContext ctx) {
        super(
            ctx,
            new VrComponent<Scalar<MongoClient>>(
                new VrCached<>(() -> new ProdMongo(ctx)),
                new HasProfile("prod")
            ),
            new VrComponent<Scalar<MongoClient>>(
                new VrCached<>(() -> new TestMongo(ctx)),
                new HasProfile("test")
            )
        );
    }
}
