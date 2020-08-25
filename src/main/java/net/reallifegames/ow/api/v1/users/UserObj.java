/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2020 Tyler Bucher
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
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package net.reallifegames.ow.api.v1.users;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * Response object for multiple single users.
 */
public class UserObj {

    /**
     * The users id.
     */
    public final int id;

    /**
     * A users discord name.
     */
    public final String discordName;

    /**
     * A users overwatch names.
     */
    public final List<String> owNames;

    /**
     * @param id          the users id.
     * @param discordName a users discord name.
     * @param owNames     a users overwatch names.
     */
    public UserObj(final int id, @Nonnull final String discordName, @Nonnull final List<String> owNames) {
        this.id = id;
        this.discordName = discordName;
        this.owNames = owNames;
    }
}
