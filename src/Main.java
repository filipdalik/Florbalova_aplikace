import model.Match;
import model.Player;
import model.Team;
import ui.MainWindow;
import ui.WelcomeWindow;

public class Main {
    public static void main(String[] args) {
        Player player1 = new Player("Adam Cerny",58);
        Player player2 = new Player("Filip Turek",5);
        Player player3 = new Player("Andrej Novak",71);
        Player player4 = new Player("Daniel Novy",7);

        Team team1 = new Team("FBC Praha");
        team1.addPlayer(player1);
        team1.addPlayer(player2);

        Team team2 = new Team("FBC Praha");
        team2.addPlayer(player3);
        team2.addPlayer(player4);

        Match match = new Match(team1,team2);
        MainWindow mainWindow = new MainWindow(match);


    }
}