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
package hr.com.vgv.examples.healthcare.accounts;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

/**
 * Jdbc account.
 *
 * @author Vedran Grgo Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 0.1
 */
final class JdbcAccounts implements Accounts {

    private final DataSource source;

    public JdbcAccounts(final DataSource source) {
        this.source = source;
    }

    @Override
    public Account create(final UserCreate user) throws IOException {
        try (Connection connection = this.source.getConnection()) {
            final StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO ACCOUNTS");
            sql.append("(USERNAME) VALUES");
            sql.append("(?)");
            final PreparedStatement statement = connection.prepareStatement(
                sql.toString()
            );
            statement.setString(1, user.name());
            statement.executeUpdate();
            try (final ResultSet result = statement.getGeneratedKeys()) {
                return new JdbcAccount(result.getInt("ID"));
            }
        } catch (SQLException e) {
            throw new IOException(e);
        }
    }
}
