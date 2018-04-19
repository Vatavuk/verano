package hr.com.vgv.verano.IT.dao;

import com.mongodb.MongoClient;
import hr.com.vgv.verano.AppContext;
import org.cactoos.Scalar;


class ProdMongo implements Scalar<MongoClient> {

    public ProdMongo(AppContext context) {
        System.out.println("ProdMongo ctor");
    }

    @Override
    public MongoClient value() throws Exception {
        System.out.println("ProdMongo value");
        return new MongoClient();
    }
}
