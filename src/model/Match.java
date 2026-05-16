package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Match {
    private Team team1;
    private Team team2;
    private int score1;
    private int score2;
    private int seconds;
    private int minutes;
    private ArrayList<Event> events;
    private int period;
    private final int periodTime = 20;

    public Match(Team team1, Team team2) {
        this.team1 = team1;
        this.team2 = team2;
        this.score1 = 0;
        this.score2 = 0;
        this.seconds = 0;
        this.events = new ArrayList<>();
        this.period = 1;
        addEvent("The match was started: " + team1.getName() + " vs " + team2.getName());
    }

    public boolean tick() {
        seconds++;
        if (seconds == 60) {
            minutes++;
        }
        if (minutes > periodTime) {
            return true;
        }
        if (this.minutes >= 20) {
            this.minutes = 20;
            this.seconds = 0;
            return true;
        }
        return false;
    }

    public void nextPeriod() {
        period++;
        minutes = 0;
        seconds = 0;
        addEvent("The " + period + ". period has begun.");
    }

    public boolean addGoal(Team team, Player goal, Player assist){
        if (goal == null){
            return false;
        }
        if (team == team1){
            score1++;
        }else if (team == team2){
            score2++;
        }
        goal.addGoal();
        if (assist != null){
            assist.addAssist();
        }
        String time = getMinutes() + ":" + String.format("%02d", getSeconds());
        String text = "Player with number #"+goal.getNumber()+" "+goal.getName() + " scored a goal";
        if (assist != null){
            text += ", assisted " +"#"+assist.getNumber()+" "+ assist.getName()+".";
        } else if (assist == null) {
            text += " without assistance.";
        }

        events.add(new Event(time, text));
        return true;
    }

    public void addPenalty(Player player, int seconds, String type) {
        if (player == null){
            return;
        }
        player.addPenalty(seconds);
        String time = getMinutes() + ":" + String.format("%02d", getSeconds());
        events.add(new Event(time, player.getName() + " - PENALTY: " + type));
    }

    public void addEvent(String text) {
        String time = getMinutes() + ":" + String.format("%02d", getSeconds());
        events.add(new Event(time, text));
    }

    public void saveToHistory() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("history.txt", true))) {
            bw.write("=== Match: " + team1.getName() + " vs " + team2.getName() + " ===");
            bw.newLine();
            bw.write("Score in the end: " + score1 + ":" + score2);
            bw.newLine();
            bw.write("Events of match:");
            bw.newLine();
            for (Object e : events) {
                bw.write(e.toString());
                bw.newLine();
            }
            bw.write("------------------------------------------");
            bw.newLine();
            bw.flush();
        } catch (IOException e) {
            System.err.println("Error writing history: " + e.getMessage());
        }
    }


    public int getMinutes() {
        return seconds / 60;
    }

    public int getSeconds() {
        return seconds % 60;
    }

    public int getScore1() {
        return score1;
    }

    public int getScore2() {
        return score2;
    }

    public Team getTeam1() {
        return team1;
    }

    public Team getTeam2() {
        return team2;
    }
    public ArrayList<Event> getEvents() {
        return events;
    }
    public int getPeriod() {
        return period;
    }
}
