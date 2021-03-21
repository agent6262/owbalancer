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

public class TeamRoleSRBalancer {

    public int totalSRDifference;

    public float calcTeamRoleDifference(final float div,
                                        @Nonnull final int[] idArray,
                                        @Nonnull final PlayerModel[] userInfoList) {
        final int team1TankSr = calcSr(userInfoList[idArray[0]].tankSr) + calcSr(userInfoList[idArray[1]].tankSr);
        final int team1DpsSr = calcSr(userInfoList[idArray[2]].dpsSr) + calcSr(userInfoList[idArray[3]].dpsSr);
        final int team1SupportSr = calcSr(userInfoList[idArray[4]].supportSr) + calcSr(userInfoList[idArray[5]].supportSr);
        final int team1Sr = team1TankSr + team1DpsSr + team1SupportSr;

        final int team2TankSr = calcSr(userInfoList[idArray[6]].tankSr) + calcSr(userInfoList[idArray[7]].tankSr);
        final int team2DpsSr = calcSr(userInfoList[idArray[8]].dpsSr) + calcSr(userInfoList[idArray[9]].dpsSr);
        final int team2SupportSr = calcSr(userInfoList[idArray[10]].supportSr) + calcSr(userInfoList[idArray[11]].supportSr);
        final int team2Sr = team2TankSr + team2DpsSr + team2SupportSr;

        totalSRDifference = Math.abs(team1TankSr - team2TankSr) + Math.abs(team1DpsSr - team2DpsSr) + Math.abs(team1SupportSr - team2SupportSr);
        final int maxSr = Math.max(team2Sr, team1Sr);
        return (((float) (maxSr - totalSRDifference)) * div) / ((float) maxSr);
    }

    public int calcSr(final int sr) {
        return (int) ((Math.tan(((Math.PI / 6000) * sr) - (((5.0 / 6.0) * Math.PI) / 2)) * 2500.0) + 2500.0);
    }
}
