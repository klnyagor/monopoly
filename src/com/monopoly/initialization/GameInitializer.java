package com.monopoly.initialization;

import com.monopoly.model.PawnColor;
import com.monopoly.model.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameInitializer {
    public List<Player> initializePlayers() {
        List<Player> players = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int numPlayers = 0;
        while (true) {
            System.out.print("Entre com o número de jogadores [2-8]: ");
            try {
                numPlayers = Integer.parseInt(scanner.nextLine().trim());
                if (numPlayers >= 2 && numPlayers <= 8) {
                    break;
                }
            } catch (NumberFormatException e) {}
            System.out.println("Número inválido de jogadores. Tente novamente.");
        }
        for (int i = 1; i <= numPlayers; i++) {
            System.out.print("Entre com o nome do jogador no. " + i + " : ");
            String name = scanner.nextLine().trim();
            PawnColor chosenColor = null;
            while (chosenColor == null) {
                System.out.println("Escolha a cor do peão do jogador no. " + i + " entre as opções seguintes:");
                System.out.println("[preto][branco][vermelho][verde][azul][amarelo][laranja][rosa]");
                System.out.print(": ");
                String colorInput = scanner.nextLine().trim().toUpperCase();
                try {
                    chosenColor = PawnColor.valueOf(colorInput);
                } catch (IllegalArgumentException e) {
                    System.out.println("Cor inválida. Tente novamente.");
                }
            }
            players.add(new Player(name, chosenColor));
        }
        return players;
    }
}
