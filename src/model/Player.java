package model;

import java.io.Serializable;

public class Player implements Serializable {
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
        this.penaltySeconds += seconds;
        this.penaltyMinutes = this.penaltySeconds / 60;
    }
    public void tickPenalty() {
        if (penaltySeconds > 0) {
            penaltySeconds--;
            penaltyMinutes = penaltySeconds / 60;
        }
    }
    public void clearPenalty() {
        penaltySeconds = 0;
        penaltyMinutes = 0;
    }

    public String getFormattedPenalty() {
        int mins = penaltySeconds / 60;
        int secs = penaltySeconds % 60;
        return String.format("%02d:%02d - #%d", mins, secs, number);
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
