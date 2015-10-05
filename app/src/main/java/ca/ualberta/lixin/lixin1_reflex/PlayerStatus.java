package ca.ualberta.lixin.lixin1_reflex;

import java.util.Objects;

/**
 * Created by SouL on 10/5/2015.
 */
public abstract class PlayerStatus extends Object {
    private String playerName;
    private long reflexTime;

    public PlayerStatus(String playerName, long reflexTime){
        this.playerName = playerName;
        this.reflexTime = reflexTime;
    }

    public PlayerStatus(String playerName){
        this.playerName = playerName;
    }
}
