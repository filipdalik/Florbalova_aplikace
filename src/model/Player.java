package model;

public class Player {
    private String name;
    private int number;
    private int goals;
    private int assists;
    private int points;

    public Player(String name, int number) {
        this.name = name;
        this.number = number;
        this.goals = 0;
        this.assists = 0;
        this.points = 0;
    }

    public void addGoals(){
        goals++;
        points++;
    }
    public void addAssists(){
        assists++;
        points++;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public int getGoals() {
        return goals;
    }

    public int getAssists() {
        return assists;
    }

    public int getPoints() {
        return points;
    }

    @Override
    public String toString() {
        return number + " - " + name + " (G:" + goals + ", A:" + assists + ")";
    }
}
