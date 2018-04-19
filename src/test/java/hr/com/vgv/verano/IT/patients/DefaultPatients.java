package hr.com.vgv.verano.IT.patients;

import com.mongodb.MongoClient;
import hr.com.vgv.verano.AppContext;
import hr.com.vgv.verano.Factory;
import hr.com.vgv.verano.IT.dao.JnMongo;
import org.cactoos.Scalar;

/**
 * <p>
 * <b>Title: DefaultPatients </b>
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

class DefaultPatients implements Patients {

    private final Factory<Scalar<MongoClient>> client;

    public DefaultPatients(AppContext context) {
        this(new JnMongo(context));
    }

    public DefaultPatients(Factory<Scalar<MongoClient>> factory) {
        System.out.println("Default patients ctor");
        this.client = factory;
    }

    @Override
    public void create() throws Exception {
        this.client.instance().value();
        System.out.println("Default patients value");
    }
}
