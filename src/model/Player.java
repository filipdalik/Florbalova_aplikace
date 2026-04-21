package model;

public class Player {
    private String name;
    private int number;

    public Player(String name, int number) {
        this.name = name;
        this.number = number;
    }

    @Override
    public String toString() {
        return number + " - " + name;
    }
}
