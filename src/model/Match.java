package model;

public class Match {
    private Team team1;
    private Team team2;
    private int score1;
    private int score2;
    private int seconds;
    public Match(Team team1, Team team2) {
        this.team1 = team1;
        this.team2 = team2;
        this.score1 = 0;
        this.score2 = 0;
        this.seconds = 0;
    }
    public boolean addGoalToTeam1(String nameGoal, String nameAsist) {
        score1++;
        addAsistToTeam1(nameAsist);
        for (int i = 0; i < team1.getPlayers().size(); i++) {
            if (nameGoal.equals(team1.getPlayers().get(i).getName())) {
                team1.getPlayers().get(i).addGoal();
                return true;
            }
        }
        System.out.println("Player to goal" + nameGoal + " is not registred.");
        return false;
    }
    public boolean addAsistToTeam1(String name) {
        for (int i = 0; i < team1.getPlayers().size(); i++) {
            if (name.equals(team1.getPlayers().get(i).getName())) {
                team1.getPlayers().get(i).addAssist();
                return true;
            }
        }
        System.out.println("Player to asist " + name + " is not registred.");
        return false;
    }
    public boolean addGoalToTeam2(String nameGoal, String nameAsist) {
        score2++;
        addAsistToTeam2(nameAsist);
        for (int i = 0; i < team2.getPlayers().size(); i++) {
            if (nameGoal.equals(team2.getPlayers().get(i).getName())) {
                team2.getPlayers().get(i).addGoal();
                return true;
            }
        }
        System.out.println("Player to goal" + nameGoal + " is not registred.");
        return false;
    }
    public boolean addAsistToTeam2(String name) {
        for (int i = 0; i < team2.getPlayers().size(); i++) {
            if (name.equals(team2.getPlayers().get(i).getName())) {
                team2.getPlayers().get(i).addAssist();
                return true;
            }
        }
        System.out.println("Player to asist " + name + " is not registred.");
        return false;
    }
    public void tick() {
        seconds++;
    }

    public int getMinutes() {
        return seconds/60;
    }

    public int getSeconds() {
        return seconds%60;
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
}
}
