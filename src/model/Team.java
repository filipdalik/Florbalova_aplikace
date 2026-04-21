package model;

import java.util.ArrayList;

public class Team {
    private String name;
    private ArrayList<Player> players;

    public Team(String name) {
        this.name = name;
        this.players = new ArrayList<>();
    }
    public void addPlayer(Player player) {
        players.add(player);
    }

    public String getName() {
        return name;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }
}
