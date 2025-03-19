package com.monopoly.commands;

import com.monopoly.GameEngine;
import com.monopoly.model.Player;
import com.monopoly.turn.TurnStrategy;
import com.monopoly.turn.NormalTurnStrategy;
import com.monopoly.turn.JailTurnStrategy;

public class JogarCommand implements ICommand {
    @Override
    public void execute(GameEngine engine) {
        Player currentPlayer = engine.getCurrentPlayer();
        TurnStrategy strategy = currentPlayer.isInPrison() 
                ? new JailTurnStrategy() 
                : new NormalTurnStrategy();
        strategy.executeTurn(engine, currentPlayer);
    }
}
