package model;

import java.io.*;
import java.util.ArrayList;

public class TeamManager {

    private ArrayList<Team> teams;
    private final String file = "teams.dat";

    public TeamManager() {
        load();
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }
    public void removeTeam(String name){
        for (int i = 0; i < teams.size(); i++) {
            if (teams.get(i).getName().equals(name)) {
                teams.remove(i);
                save();
            }
        }

    }

    public void addTeam(Team team) {
        for (int i = 0; i < teams.size(); i++) {
            if (teams.get(i).getName().equals(team.getName())) {
                teams.remove(i);
            }
        }
        teams.add(team);
        save();
    }

    private void save() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(teams);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void load() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            teams = (ArrayList<Team>) in.readObject();
        } catch (Exception e) {
            teams = new ArrayList<>();
        }
    }
}