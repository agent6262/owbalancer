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

import net.reallifegames.ow.api.v1.ApiResponse;
import net.reallifegames.ow.models.UserInfo;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * Json response object for a single user.
 */
public class SingleUserResponse extends ApiResponse {

    /**
     * The info for a user.
     */
    public final UserInfo userInfo;

    /**
     * The users overwatch names.
     */
    public final List<String> owNames;

    /**
     * @param apiResponse standard api response structure.
     * @param userInfo    the info for a user.
     * @param owNames     the users overwatch names.
     */
    public SingleUserResponse(@Nonnull final ApiResponse apiResponse, @Nonnull final UserInfo userInfo, @Nonnull final List<String> owNames) {
        super(apiResponse.version);
        this.userInfo = userInfo;
        this.owNames = owNames;
    }
}
