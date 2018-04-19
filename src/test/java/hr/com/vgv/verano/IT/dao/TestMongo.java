package hr.com.vgv.verano.IT.dao;

import com.mongodb.MongoClient;
import hr.com.vgv.verano.AppContext;
import org.cactoos.Scalar;


class TestMongo implements Scalar<MongoClient> {

    public TestMongo(AppContext context) {}

    @Override
    public MongoClient value() throws Exception {
        System.out.println("TestMongo value");
        return new MongoClient();
    }
}
