package hr.com.vgv.verano.IT.patients;

import com.mongodb.MongoClient;
import hr.com.vgv.verano.AppContext;
import hr.com.vgv.verano.Factory;
import hr.com.vgv.verano.IT.dao.VrMongo;
import org.cactoos.Scalar;


class DefaultPatients implements Patients {

    private final Factory<Scalar<MongoClient>> client;

    public DefaultPatients(AppContext context) {
        this(new VrMongo(context));
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
