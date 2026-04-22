package model;

public class Player {
    private String name;
    private int number;
    private int goals;
    private int assists;
    private int points;
    private int penaltySeconds;
    private int penaltyMinutes;

    public Player(String name, int number) {
        this.name = name;
        this.number = number;
        this.goals = 0;
        this.assists = 0;
        this.points = 0;
        this.penaltySeconds = 0;
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

    public void addPenalty(int seconds) {
        penaltySeconds += seconds;
        penaltyMinutes += seconds / 60;
    }
    public void tickPenalty(){
        if(penaltyMinutes > 0){
            penaltyMinutes--;
        }
    }
    public void clearPenalty() {
        penaltySeconds = 0;
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

    public int getPenaltySeconds() {
        return penaltySeconds;
    }


    @Override
    public String toString() {
        return number + " - " + name;
    }
}
