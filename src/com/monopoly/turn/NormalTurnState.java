package com.monopoly.turn;

import com.monopoly.GameEngine;
import com.monopoly.model.Player;
import com.monopoly.commands.CommandFactory;
import com.monopoly.commands.ICommand;
import java.util.Scanner;

public class NormalTurnState implements TurnState {
    @Override
    public void handleTurn(GameEngine engine, Player player, Scanner scanner) {
        boolean turnComplete = false;
        while (!turnComplete && !engine.isGameOver()) {
            System.out.println("\nA jogada de " + player.getName() + " começou.");
            System.out.println("Comandos disponíveis: [jogar][status][sair]");
            System.out.print("Entre com um comando: ");
            String input = scanner.nextLine().trim();
            try {
                ICommand command = CommandFactory.getCommand(input);
                command.execute(engine);
                if (input.equalsIgnoreCase("jogar") || input.equalsIgnoreCase("sair")) {
                    turnComplete = true;
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
