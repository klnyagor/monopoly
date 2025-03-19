package com.monopoly.turn;

import com.monopoly.GameEngine;
import com.monopoly.model.Player;

public interface TurnStrategy {
    void executeTurn(GameEngine engine, Player player);
}
