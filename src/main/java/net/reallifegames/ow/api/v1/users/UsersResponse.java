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

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A standard users api fetch response structure.
 *
 * @author Tyler Bucher
 */
class UsersResponse extends ApiResponse {

    /**
     * The list of users in this application.
     */
    public final List<UserObj> users;

    /**
     * Response constructor for Jackson json marshalling.
     *
     * @param apiResponse  the root api response.
     * @param usernameList list of users in this application.
     */
    public UsersResponse(@Nonnull final ApiResponse apiResponse,
                         @Nonnull final List<Map.Entry<Integer, String>> usernameList,
                         @Nonnull final Map<Integer, String> userList) {
        super(apiResponse.version);
        this.users = new ArrayList<>();
        for (final Map.Entry<Integer, String> user : userList.entrySet()) {
            this.users.add(new UserObj(user.getKey(), user.getValue(), getNames(user.getKey(), usernameList)));
        }
    }

    /**
     * @param id           the users id.
     * @param usernameList a map of user ids.
     * @return a list of names for a users id.
     */
    private List<String> getNames(final int id, @Nonnull final List<Map.Entry<Integer, String>> usernameList) {
        final List<String> names = new ArrayList<>();
        for (final Map.Entry<Integer, String> entry : usernameList) {
            if (id == entry.getKey()) {
                names.add(entry.getValue());
            }
        }
        return names;
    }
}
