package hr.com.vgv.verano.IT.dao;

import com.mongodb.MongoClient;
import hr.com.vgv.verano.AppContext;
import org.cactoos.Scalar;

/**
 * <p>
 * <b>Title: TestMongo </b>
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

class TestMongo implements Scalar<MongoClient> {

    public TestMongo(AppContext context) {}

    @Override
    public MongoClient value() throws Exception {
        System.out.println("TestMongo value");
        return new MongoClient();
    }
}
