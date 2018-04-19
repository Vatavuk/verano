package hr.com.vgv.verano.IT.patients;

import hr.com.vgv.verano.AppContext;

/**
 * <p>
 * <b>Title: InMemoryPatients </b>
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

class InMemoryPatients implements Patients {

    public InMemoryPatients(final AppContext context) {}

    @Override
    public void create() throws Exception {
        System.out.println("InMemorypatients value");
    }
}
