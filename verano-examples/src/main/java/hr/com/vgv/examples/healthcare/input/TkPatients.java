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
package hr.com.vgv.examples.healthcare.input;

import hr.com.vgv.examples.healthcare.accounts.UserCreate;
import hr.com.vgv.examples.healthcare.nurses.Nurses;
import hr.com.vgv.examples.healthcare.patients.Patient;
import hr.com.vgv.examples.healthcare.patients.PatientCreate;
import hr.com.vgv.examples.healthcare.patients.Patients;
import hr.com.vgv.examples.healthcare.accounts.Accounts;
import java.io.IOException;
import org.takes.Request;
import org.takes.Response;
import org.takes.Take;
import org.takes.rs.RsText;

/**
 * TkPatients.
 *
 * @author Vedran Grgo Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class TkPatients {

    /**
     * Creates patient.
     */
    public static class Create implements Take {

        /**
         * Patients.
         */
        private final Patients patients;

        /**
         * Nurses.
         */
        private final Nurses nurses;

        /**
         * Users.
         */
        private final Accounts accounts;

        /**
         * Ctor.
         * @param patients Patients
         * @param nurses Nurses
         * @param accounts Users
         */
        public Create(final Patients patients, final Nurses nurses,
            final Accounts accounts) {
            this.patients = patients;
            this.nurses = nurses;
            this.accounts = accounts;
        }

        @Override
        public Response act(final Request request) throws IOException {
            final PatientCreate req = new PatientCreate(request);
            final Patient patient = this.patients.create(req);
            this.nurses.get(req.nurseId()).addPatient(patient.id());
            this.accounts.create(
                new UserCreate(req.toJson().getString("username"))
            );
            return new RsText(patient.id());
        }
    }
}
