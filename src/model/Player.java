package model;

import java.io.Serializable;

/**
 * Tato třída reprezentuje florbalového hráče a má zaznamenané zápasové statistiky.
 * Ve třídě je imple,entované Serializable pro ukládání dat.
 */
public class Player implements Serializable {
    private String name;
    private int number;
    private int goals;
    private int assists;
    private int points;
    private int penaltySeconds;
    private int penaltyMinutes;

    /**
     * Vytvoří novou instanci hráče se zadaným jménem a číslem.
     */
    public Player(String name, int number) {
        this.name = name;
        this.number = number;
        this.goals = 0;
        this.assists = 0;
        this.points = 0;
        this.penaltySeconds = 0;
        this.penaltyMinutes = 0;
    }

    /**
     * Navyšuje počet golů a celkových bodů.
     */
    public void addGoal(){
        goals++;
        points++;
    }

    /**
     * Navyšuje počet asistencí a celkových bodů.
     */
    public void addAssist(){
        assists++;
        points++;
    }

    /**
     * Přidává trestné minuty a sekundy.
     */
    public void addPenalty(int seconds) {
        this.penaltySeconds += seconds;
        this.penaltyMinutes = this.penaltySeconds / 60;
    }

    /**
     * Sníží zbývající čas trestu o jednu sekundu.
     */
    public void tickPenalty() {
        if (penaltySeconds > 0) {
            penaltySeconds--;
            penaltyMinutes = penaltySeconds / 60;
        }
    }

    /**
     * Vrátí naformátovaný text s časem trestu a číslem hráče,
     * @return String text
     */
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
