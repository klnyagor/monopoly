package com.monopoly.turn;

import com.monopoly.model.Player;

public class TurnStateFactory {
    public static TurnState getTurnState(Player player) {
        if (player.isInPrison()) {
            return new PrisonTurnState();
        } else {
            return new NormalTurnState();
        }
    }
}
