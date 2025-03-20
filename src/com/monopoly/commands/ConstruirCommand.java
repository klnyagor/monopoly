package com.monopoly.commands;

import com.monopoly.GameEngine;
import com.monopoly.board.PropertyTile;
import com.monopoly.board.Tile;
import com.monopoly.board.TitleType;
import com.monopoly.model.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConstruirCommand implements ICommand {
    @Override
    public void execute(GameEngine engine) {
        Player player = engine.getCurrentPlayer();
        // Obter propriedades elegíveis chamando o método polimórfico de cada propriedade
        List<PropertyTile> eligibleProperties = getEligibleProperties(engine, player);
        if (eligibleProperties.isEmpty()) {
            System.out.println("Você não possui monopólio ou não há propriedades elegíveis para construir.");
            return;
        }
        
        Scanner scanner = new Scanner(System.in);
        while (!eligibleProperties.isEmpty()) {
            System.out.println(player.getName() + " possui $" + player.getMoney());
            System.out.println("Escolha onde quer construir:");
            int index = 1;
            for (PropertyTile pt : eligibleProperties) {
                String buildType;
                if (pt.getNumHouses() < 4) {
                    buildType = "casa";
                } else if (pt.getNumHouses() == 4) {
                    buildType = "hotel";
                } else {
                    buildType = "nenhuma construção disponível";
                }
                System.out.println(index + " – " + pt.getName() + " tem " + pt.getNumHouses() 
                        + " casa(s) construídas, próxima construção (" + buildType + ") custa $" + pt.getHousePrice());
                index++;
            }
            System.out.println("Entre com o número da propriedade (0 para sair): ");
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida.");
                continue;
            }
            if (choice == 0) {
                break;
            }
            if (choice < 1 || choice > eligibleProperties.size()) {
                System.out.println("Opção inválida.");
                continue;
            }
            PropertyTile selected = eligibleProperties.get(choice - 1);
            int cost = selected.getHousePrice();
            if (selected.getNumHouses() < 4) {
                // Construir casa
                if (player.getMoney() < cost) {
                    System.out.println("Dinheiro insuficiente para construir.");
                    continue;
                }
                if (engine.getAvailableHouses() <= 0) {
                    System.out.println("Não há casas disponíveis no banco.");
                    continue;
                }
                player.deductMoney(cost);
                selected.setNumHouses(selected.getNumHouses() + 1);
                engine.decrementAvailableHouses(1);
                System.out.println("Construída uma casa em " + selected.getName() + ".");
            } else if (selected.getNumHouses() == 4) {
                // Construir hotel
                if (player.getMoney() < cost) {
                    System.out.println("Dinheiro insuficiente para construir um hotel.");
                    continue;
                }
                if (engine.getAvailableHotels() <= 0) {
                    System.out.println("Não há hotéis disponíveis no banco.");
                    continue;
                }
                player.deductMoney(cost);
                selected.setNumHouses(5); // 5 indica hotel
                engine.decrementAvailableHotels(1);
                engine.incrementAvailableHouses(4); // as 4 casas voltam ao banco
                System.out.println("Construído um hotel em " + selected.getName() + ".");
            }
            // Atualiza a lista de propriedades elegíveis
            eligibleProperties = getEligibleProperties(engine, player);
        }
    }
    
    private List<PropertyTile> getEligibleProperties(GameEngine engine, Player player) {
        List<PropertyTile> eligible = new ArrayList<>();
        for (Tile tile : engine.getBoard().getTiles()) {
            if (tile.getTitleType() == TitleType.PROPERTY && player.equals(tile.getOwner())) {
                PropertyTile pt = (PropertyTile) tile;
                if (pt.isEligibleToBuild(engine)) {
                    eligible.add(pt);
                }
            }
        }
        return eligible;
    }
}

