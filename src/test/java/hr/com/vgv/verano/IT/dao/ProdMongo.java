package hr.com.vgv.verano.IT.dao;

import com.mongodb.MongoClient;
import hr.com.vgv.verano.AppContext;
import org.cactoos.Scalar;

/**
 * <p>
 * <b>Title: ProdMongo </b>
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
