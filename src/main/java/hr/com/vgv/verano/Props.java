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
package hr.com.vgv.verano;

/**
 * Properties.
 *
 * @author Vedran Grgo Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public interface Props {

    /**
     * Retrieve property value.
     *
     * @param property Property
     * @return String Value
     * @throws Exception If fails
     */
    String value(String property) throws Exception;

    /**
     * Retrieve property value or return default if the property is not found.
     *
     * @param property Property
     * @param defaults Default value
     * @return String Value
     * @throws Exception If fails
     */
    String value(String property, String defaults) throws Exception;

    /**
     * Return property values.
     *
     * @param property Property
     * @return Values Values
     * @throws Exception If fails
     */
    Iterable<String> values(String property) throws Exception;

    /**
     * Check if property anyActive.
     *
     * @param property Property
     * @return Boolean Boolean
     * @throws Exception If fails
     */
    boolean has(String property) throws Exception;
}
