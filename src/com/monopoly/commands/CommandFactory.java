package com.monopoly.commands;

public class CommandFactory {
    public static ICommand getCommand(String input) {
        if (input.equalsIgnoreCase("jogar")) {
            return new JogarCommand();
        } else if (input.equalsIgnoreCase("sair")) {
            return new SairCommand();
        } else if (input.equalsIgnoreCase("status")) {
            return new StatusCommand();
        }
        throw new IllegalArgumentException("Comando inválido: " + input);
    }
}
