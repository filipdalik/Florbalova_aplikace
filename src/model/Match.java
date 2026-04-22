package model;

import java.util.ArrayList;

public class Match {
    private Team team1;
    private Team team2;
    private int score1;
    private int score2;
    private int seconds;
    private ArrayList<Event> events;

    public Match(Team team1, Team team2) {
        this.team1 = team1;
        this.team2 = team2;
        this.score1 = 0;
        this.score2 = 0;
        this.seconds = 0;
        this.events = new ArrayList<>();
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
        String text = goal.getName() + "scored a goal";
        if (assist != null){
            text += ", assisted " + assist.getName()+".";
        }
        text += " without assistance.";
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

    public void tick() {
        seconds++;
        for (int i = 0; i < team1.getPlayers().size(); i++) {
            Player p = team1.getPlayers().get(i);
            p.tickPenalty();
        }
        for (int i = 0; i < team2.getPlayers().size(); i++) {
            Player p = team2.getPlayers().get(i);
            p.tickPenalty();
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
}
