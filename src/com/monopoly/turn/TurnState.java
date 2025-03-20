package com.monopoly.turn;

import com.monopoly.GameEngine;
import com.monopoly.model.Player;
import java.util.Scanner;

public interface TurnState {
    void handleTurn(GameEngine engine, Player player, Scanner scanner);
}
