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
package net.reallifegames.ow.api.v1.players;

import io.javalin.http.Context;
import net.reallifegames.ow.Balancer;
import net.reallifegames.ow.DbModule;
import net.reallifegames.ow.Permissions;
import net.reallifegames.ow.SecurityModule;
import net.reallifegames.ow.api.v1.ApiController;
import net.reallifegames.ow.models.PlayerModel;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.List;

/**
 * Base users api controller, which dispatches information about users in this application. This is a secure api
 * endpoint.
 *
 * @author Tyler Bucher
 */
public class PlayersController {

    /**
     * Default list of permissions for this endpoint.
     */
    private static final List<Integer> PERMISSIONS = Arrays.asList(
            Permissions.IS_USER_SUPER_ADMIN.value,
            Permissions.IS_USER_ADMIN.value,
            Permissions.CAN_USER_MOD_PLAYER.value
    );

    /**
     * Attempts to create a new user from the post data.
     *
     * @param context the REST request context to modify.
     */
    public static void getPlayer(@Nonnull final Context context) throws Exception {
        getPlayer(context, Balancer.getDbModule(), Balancer.getSecurityModule());
    }

    /**
     * Attempts to create a new user from the post data.
     *
     * @param context        the REST request context to modify.
     * @param dbModule       the module instance to use.
     * @param securityModule the module instance to use.
     */
    public static void getPlayer(@Nonnull final Context context,
                                 @Nonnull final DbModule dbModule,
                                 @Nonnull final SecurityModule securityModule) throws Exception {
        ApiController.beforeApiAuthentication(context, dbModule, securityModule, PERMISSIONS);
        final String[] path = context.path().split("/");
        if (path.length != 5) {
            context.status(400);
            context.result("Bad Request");
            return;
        }
        // Set the response type
        final List<PlayerModel> tt = dbModule.getAllPlayerModels();
        ApiController.jsonContextResponse(new PlayersResponse(dbModule.getPlayerModel(path[4])), context);
    }
}
