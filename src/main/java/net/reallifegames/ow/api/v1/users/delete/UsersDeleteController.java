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
package net.reallifegames.ow.api.v1.users.delete;

import io.javalin.http.Context;
import net.reallifegames.ow.Balancer;
import net.reallifegames.ow.DbModule;
import net.reallifegames.ow.api.v1.ApiController;
import net.reallifegames.ow.api.v1.users.post.UsersPostResponse;

import javax.annotation.Nonnull;
import java.io.IOException;

public class UsersDeleteController {

    /**
     * Default create user success response.
     */
    private static final UsersPostResponse successResponse = new UsersPostResponse(ApiController.apiResponse, "success");

    /**
     * Default create user error response.
     */
    private static final UsersPostResponse errorResponse = new UsersPostResponse(ApiController.apiResponse, "error");

    /**
     * Attempts to create a new user from the post data.
     *
     * @param context the REST request context to modify.
     */
    public static void deleteUser(@Nonnull final Context context) throws IOException {
        deleteUser(context, Balancer.getDbModule());
    }

    /**
     * Attempts to create a new user from the post data.
     *
     * @param context  the REST request context to modify.
     * @param dbModule the module instance to use.
     * @throws IOException if the object could not be marshaled.
     */
    public static void deleteUser(@Nonnull final Context context,
                                  @Nonnull final DbModule dbModule) throws IOException {
        // Set the response type
        final Integer param = context.pathParam(":id", Integer.class).getOrNull();
        if (param == null || param == -1) {
            ApiController.LOGGER.debug("Delete user  path param error");
            context.status(400);
            context.result("Bad Request");
        } else {
            final DeleteUserRequest deleteUserRequest = new DeleteUserRequest(param);
            if (!deleteUserRequest.deletePlayer(dbModule)) {
                context.status(406);
                context.result("Not Acceptable");
            } else {
                context.status(200);
                context.result("Success");
            }
        }
    }
}
