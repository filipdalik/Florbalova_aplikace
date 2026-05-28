package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Třída, která reprezentuje florbalový tým v systému. Má v sobě týmy a název týmu.
 * Implementuje rozhraní Serializable pro ukládání celého týmu i s hráči do souboru.
 */
public class Team implements Serializable {
    private String name;
    private ArrayList<Player> players;

    /**
     * Vytvoří novou instanci týmu se zadaným názvem.
     */
    public Team(String name) {
        this.name = name;
        this.players = new ArrayList<>();
    }

    /**
     * Metoda, která přidá hráče do týmu.
     */
    public void addPlayer(Player player) {
        players.add(player);
    }

    /**
     * Metoda, která odebere hráče z týmu.
     */
    public void removePlayer(Player player) {
        players.remove(player);
    }

    public String getName() {
        return name;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    @Override
    public String toString() {
        return name;
    }
}
