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
package net.reallifegames.ow.balancer;

import net.reallifegames.ow.models.PlayerModel;

import javax.annotation.Nonnull;

public class PlayerPositionBalancer {

    public int team1PositionPreferenceCount;
    public int team2PositionPreferenceCount;

    public float calcPlayerPrimaryScore(final float div,
                                        @Nonnull final int[] idArray,
                                        @Nonnull final PlayerModel[] userInfoList) {
        team1PositionPreferenceCount = calcTeamPrimaryPosition(0, idArray, userInfoList);
        team2PositionPreferenceCount = calcTeamPrimaryPosition(6, idArray, userInfoList);
        return (((float) team1PositionPreferenceCount + team2PositionPreferenceCount) * div) / 24.0f;
    }

    private static int calcTeamPrimaryPosition(final int offset, @Nonnull final int[] idArray, @Nonnull final PlayerModel[] userInfoList) {
        return userInfoList[idArray[offset]].tankPreference +
                userInfoList[idArray[offset + 1]].tankPreference +
                userInfoList[idArray[offset + 2]].dpsPreference +
                userInfoList[idArray[offset + 3]].dpsPreference +
                userInfoList[idArray[offset + 4]].supportPreference +
                userInfoList[idArray[offset + 5]].supportPreference;
    }
}
