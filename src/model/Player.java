package model;

public class Player {
    private String name;
    private int number;
    private int goals;
    private int assists;
    private int points;
    private int penaltyMinutes;

    public Player(String name, int number) {
        this.name = name;
        this.number = number;
        this.goals = 0;
        this.assists = 0;
        this.points = 0;
        this.penaltyMinutes = 0;
    }

    public void addGoal(){
        goals++;
        points++;
    }
    public void addAssist(){
        assists++;
        points++;
    }

    public void addPenalty(int time){
        int i = time/60;
        penaltyMinutes += i;
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
    public int getPenaltyMinutes() {
        return penaltyMinutes;
    }

    @Override
    public String toString() {
        return number + " - " + name;
    }
}
