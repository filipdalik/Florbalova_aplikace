package model;
/**
 * Tato třída reprezentuje konkrétní událost v zápase.
 * Pro tuto událost je důležitý čas a textový popis události
 */
public class Event {
    private String time;
    private String text;

    /**
     * Konstruktor pro vytvoření nové instance události.
     */
    public Event(String time, String text) {
        this.time = time;
        this.text = text;
    }

    /**
     * Vrátí čas, kdy k události došlo.
     * @return String time
     */
    public String getTime() {
        return time;
    }

    /**
     * Vrátí textový popis události.
     * @return String text.
     */
    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return time + " -> " + text;
    }
}
