package model;

import java.io.*;
import java.util.ArrayList;

/**
 * Tato třída reprezentuje správce týmů, který dohlíží na jejím ukládání do teams.dat.
 */
public class TeamManager {

    private ArrayList<Team> teams;
    private final String file = "src/resources/teams.dat";

    /**
     * Vytvoří novou instanci správce týmů.
     */
    public TeamManager() {
        load();
    }
    /**
     * Vrátí všechny teamy
     * @return Arraylist
     */
    public ArrayList<Team> getTeams() {
        return teams;
    }

    /**
     * Odebere daný team.
     */
    public void removeTeam(String name){
        for (int i = 0; i < teams.size(); i++) {
            if (teams.get(i).getName().equals(name)) {
                teams.remove(i);
                save();
            }
        }

    }

    /**
     * Přidá daný team do seznamu teamu.
     */
    public void addTeam(Team team) {
        teams.add(team);
        save();
    }

    /**
     * Tato metoda serializuje a ukládá aktualní seznam týmů do souboru, když nastane chyba, vyvolá stack trace
     */
    public void save() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(this.teams);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Načte serializovaný seznam týmů ze souboru, pokud nastane chyba vyvolá výjimku.
     */
    private void load() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            teams = (ArrayList<Team>) in.readObject();
        } catch (Exception e) {
            teams = new ArrayList<>();
        }
    }
}