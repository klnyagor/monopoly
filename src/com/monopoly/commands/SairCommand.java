package com.monopoly.commands;

import com.monopoly.GameEngine;
import java.util.Scanner;

public class SairCommand implements ICommand {
    @Override
    public void execute(GameEngine engine) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Você tem certeza de que quer sair (Sim/Não)? ");
        String response = scanner.nextLine().trim().toLowerCase();
        if (response.startsWith("s")) {
            System.out.println("Jogo encerrado.");
            System.exit(0);
        } else {
            System.out.println("Continua o jogo.");
        }
    }
}
