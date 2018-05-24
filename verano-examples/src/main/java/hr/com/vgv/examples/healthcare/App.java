/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2018 Vedran Grgo Vatavuk
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package hr.com.vgv.examples.healthcare;

import hr.com.vgv.examples.healthcare.accounts.Accounts;
import hr.com.vgv.examples.healthcare.accounts.VrAccounts;
import hr.com.vgv.examples.healthcare.input.TkPatients;
import hr.com.vgv.examples.healthcare.nurses.Nurses;
import hr.com.vgv.examples.healthcare.nurses.VrNurses;
import hr.com.vgv.examples.healthcare.patients.Patients;
import hr.com.vgv.examples.healthcare.patients.VrPatients;
import hr.com.vgv.verano.AppContext;
import hr.com.vgv.verano.VrAppContext;
import org.takes.facets.fork.FkMethods;
import org.takes.facets.fork.FkRegex;
import org.takes.facets.fork.TkFork;
import org.takes.http.Exit;
import org.takes.http.FtBasic;

/**
 * App.
 *
 * @author Vedran Grgo Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class App {

    public static void Main(String... args) throws Exception {
        final AppContext context = new VrAppContext(args);
        final Patients patients = new VrPatients(context).instance();
        final Nurses nurses = new VrNurses(context).instance();
        final Accounts accounts = new VrAccounts(context).instance();
        new FtBasic(
            new TkFork(
                new FkRegex(
                    "/patient",
                    new TkFork(
                        new FkMethods("POST",
                            new TkPatients.Create(patients, nurses, accounts)
                        )
                    )
                )
            )
        ).start(Exit.NEVER);
    }
}
