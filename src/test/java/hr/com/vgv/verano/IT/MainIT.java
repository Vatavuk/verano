package hr.com.vgv.verano.IT;

import hr.com.vgv.verano.AppContext;
import hr.com.vgv.verano.VrAppContext;
import hr.com.vgv.verano.VrCached;
import hr.com.vgv.verano.VrComponent;
import hr.com.vgv.verano.VrFactory;
import hr.com.vgv.verano.conditions.HasProfile;
import org.cactoos.Scalar;
import org.junit.Test;


public class MainIT {

    @Test
    public void mainFlow() throws Exception {
        AppContext context = new VrAppContext("--profile=prod");
        new MongoCmp(context).instance().value();
        new MongoCmp(context).instance().value();
    }

    private static final class MongoCmp extends VrFactory<Scalar<String>> {

        public MongoCmp(final AppContext ctx) {
            super(
                ctx,
                new VrComponent<>(
                    new VrCached<>(() -> new ProdMongo(ctx)),
                    new HasProfile("prod")
                ),
                new VrComponent<>(
                    new VrCached<>(() -> new DevMongo(ctx)),
                    new HasProfile("dev")
                )
            );
        }
    }

    private static final class ProdMongo implements Scalar<String> {

        public ProdMongo(final AppContext context) {
            System.out.println("Prod ctor.");
        }

        @Override
        public String value() throws Exception {
            System.out.println("Prod value");
            return "";
        }
    }

    private static final class DevMongo implements Scalar<String> {

        public DevMongo(final AppContext context) {
            System.out.println("Dev ctor.");
        }

        @Override
        public String value() throws Exception {
            System.out.println("Dev value");
            return "";
        }
    }
}
