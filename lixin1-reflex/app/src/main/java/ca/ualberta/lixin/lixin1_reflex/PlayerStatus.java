/*
    lixin-reflex
    Copyright (C) 2015  Lixin Jin lixin1@ualberta.ca

    This program is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License along
    with this program; if not, write to the Free Software Foundation, Inc.,
    51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package ca.ualberta.lixin.lixin1_reflex;

import java.util.Objects;

public class PlayerStatus extends Object {
    private String playerName;
    private long reflexTime;

    public PlayerStatus(String playerName, long reflexTime){
        this.setName(playerName);
        this.setReflexTime(reflexTime);
    }

    public PlayerStatus(long reflexTime) {
        //this.setName("");
        this.setReflexTime(reflexTime);
    }

    public String getName(){
        return playerName;
    }

    public void setName(String playerName) {
        this.playerName = playerName;
    }

    public long getReflexTime(){
        return reflexTime;
    }

    public void setReflexTime(long reflexTime){
        this.reflexTime = reflexTime;
    }
}
