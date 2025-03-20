package com.monopoly.board;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private List<Tile> tiles;

    public Board() {
        tiles = new ArrayList<>();
        initializeBoard();
    }

    private void initializeBoard() {
        tiles.add(new PropertyTile(0, "Mediterranean Avenue", 60, 2, "roxa"));
        tiles.add(new CommunityChestTile(1, "Community Chest 1"));
        tiles.add(new PropertyTile(2, "Baltic Avenue", 60, 4, "roxa"));
        tiles.add(new TaxTile(3, "Income Tax", 200));
        tiles.add(new RailroadTile(4, "Reading Railroad", 200));
        tiles.add(new PropertyTile(5, "Oriental Avenue", 100, 6, "azul claro"));
        tiles.add(new ChanceTile(6, "Chance 1"));
        tiles.add(new PropertyTile(7, "Vermont Avenue", 100, 6, "azul claro"));
        tiles.add(new PropertyTile(8, "Connecticut Avenue", 120, 8, "azul claro"));
        tiles.add(new FreeParkingTile(9, "Jail - Just Visiting"));
        tiles.add(new PropertyTile(10, "St. Charles Place", 140, 10, "rosa"));
        tiles.add(new UtilityTile(11, "Electric Company", 150));
        tiles.add(new PropertyTile(12, "States Avenue", 140, 10, "rosa"));
        tiles.add(new PropertyTile(13, "Virginia Avenue", 160, 12, "rosa"));
        tiles.add(new RailroadTile(14, "Pennsylvania Railroad", 200));
        tiles.add(new PropertyTile(15, "St. James Place", 180, 14, "laranja"));
        tiles.add(new CommunityChestTile(16, "Community Chest 2"));
        tiles.add(new PropertyTile(17, "Tennessee Avenue", 180, 14, "laranja"));
        tiles.add(new PropertyTile(18, "New York Avenue", 200, 16, "laranja"));
        tiles.add(new FreeParkingTile(19, "Free Parking"));
        tiles.add(new PropertyTile(20, "Kentucky Avenue", 220, 18, "vermelho"));
        tiles.add(new ChanceTile(21, "Chance 2"));
        tiles.add(new PropertyTile(22, "Indiana Avenue", 220, 18, "vermelho"));
        tiles.add(new PropertyTile(23, "Illinois Avenue", 240, 20, "vermelho"));
        tiles.add(new RailroadTile(24, "B & O Railroad", 200));
        tiles.add(new PropertyTile(25, "Atlantic Avenue", 260, 22, "amarelo"));
        tiles.add(new PropertyTile(26, "Ventnor Avenue", 260, 22, "amarelo"));
        tiles.add(new UtilityTile(27, "Water Works", 150));
        tiles.add(new PropertyTile(28, "Marvin Gardens", 280, 24, "amarelo"));
        tiles.add(new FreeParkingTile(29, "Go to Jail"));
        tiles.add(new PropertyTile(30, "Pacific Avenue", 300, 26, "verde"));
        tiles.add(new PropertyTile(31, "North Carolina Avenue", 300, 26, "verde"));
        tiles.add(new CommunityChestTile(32, "Community Chest 3"));
        tiles.add(new PropertyTile(33, "Pennsylvania Avenue", 320, 28, "verde"));
        tiles.add(new RailroadTile(34, "Short Line Railroad", 200));
        tiles.add(new ChanceTile(35, "Chance 3"));
        tiles.add(new PropertyTile(36, "Park Place", 350, 35, "azul"));
        tiles.add(new TaxTile(37, "Luxury Tax", 75));
        tiles.add(new PropertyTile(38, "Boardwalk", 400, 50, "azul"));
        tiles.add(new GoTile(39, "Go"));
    }

    public Tile getTile(int position) {
        return tiles.get(position);
    }
    
    public List<Tile> getTiles() {
        return tiles;
    }
}
