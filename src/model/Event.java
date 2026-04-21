package model;

public class Event {
    private String time;
    private String text;

    public Event(String time, String text) {
        this.time = time;
        this.text = text;
    }

    @Override
    public String toString() {
        return time + " -> " + text;
    }
}
