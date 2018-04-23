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
 * Factory.
 * @author Vedran Grgo Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @param <T> Return type.
 * @since 0.1
 * @checkstyle AbstractClassNameCheck (500 lines)
 */
@SuppressWarnings("PMD.AbstractNaming")
public abstract class VrFactory<T> implements Factory<T> {

    /**
     * Application context.
     */
    private final AppContext context;

    /**
     * Components.
     */
    private final Components<T> components;

    /**
     * Ctor.
     * @param ctx Context
     * @param components Components
     */
    @SafeVarargs
    @SuppressWarnings({"unchecked", "varargs"})
    public VrFactory(final AppContext ctx, final Component<T>... components) {
        this(ctx, new VrComponents<>(components));
    }

    /**
     * Ctor.
     * @param ctx Context
     * @param components Components
     */
    private VrFactory(final AppContext ctx, final Components<T> components) {
        this.context = ctx;
        this.components = new CachedComponents<>(
            this.getClass().getName(),
            components
        );
    }

    @Override
    @SuppressWarnings("unchecked")
    public final T instance() throws Exception {
        return this.components.get(this.context).instance();
    }
}
