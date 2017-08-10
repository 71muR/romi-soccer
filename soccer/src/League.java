
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class League {

    public static void main(String[] args) {

        // the arrays of players' and teams' names;

        String playerNames[] = {"Juan Mata",
                "Gerard Pique",
                "Iker Casillas",
                "Cesc Fabregas",
                "Radamel Falcao",
                "Robin Van Persie",
                "Wayne Rooney",
                "Neymar Da Silva",
                "Zlatan Ibrahimovic",
                "Andres Iniesta",
                "Lionel Messi",
                "Christiano Ronaldo",
                "Xavi",
                "Thiago Silva",
                "Bastian Schweinsteiger",
                "Luis Suarez",
                "Sergio Ramos",
                "Vincent Kompany",
                "Marco Reus",
                "Franck Ribery",
                "Thomas Muller",
                "Karim Benzema",
                "Fernandinho",
                "Angel Di Maria"};


        String teamNames[] = {"Greens", "Reds", "Blues", "Yellows"};


        // error message variable;

        String errorMessage = "!!! Program error: The players cannot be divided in teams equally. Check it!";


        // variables to hold the total numbers of teams, players and the size of a team;

        int teamSize = 6;
        int totalPlayers = playerNames.length;
        int totalTeams = teamNames.length;


        // printing out a message about the numbers of players, teams and the size of a team;

        System.out.println("Team size: " + teamSize);
        System.out.println("Total players: " + totalPlayers);
        System.out.println("Total teams: " + totalTeams + '\n');
        System.out.println();


        // short-circuit AND is used here to control
        // the number of players, teams and the team size

        if ((totalPlayers % teamSize == 0) && (totalPlayers / teamSize == totalTeams)) {

            // creating Player and Team Objects;
            Player[] players = createPlayers(totalPlayers, playerNames);
            Team[] teams = createTeams(totalTeams, teamNames);

            // assigning players to teams;
            assignPlayersToTeams(teamSize, players, teams);

            // creating the Game;
            Game newGame = new Game();

            // tossing up;
            int[] twoTeamsToPlay = tossUp(teams);
            newGame.homeTeam = teams[twoTeamsToPlay[0]];
            newGame.awayTeam = teams[twoTeamsToPlay[1]];

            // finding out the number of goals during the game;
            Random r = new Random();
            int numberOfGoals = r.nextInt(5);

            // writing to the game object, who scored and at what time;
            if (numberOfGoals != 0) {
                scoredGoals(teamSize, numberOfGoals, newGame);
            }

            // PRINTING ON SCREEN

            printRoster(teamSize, teams);
            printTheGame(newGame);

        } else {
            System.out.println(errorMessage);
        }
    }

     /********************************** METHODS ***************************************/


    // method to create Player Objects;
    static Player[] createPlayers(int numOfPlayers, String[] listOfNames) {

        Player[] players = new Player[numOfPlayers];

        for (int i = 0; i < numOfPlayers; i++) {

            players[i] = new Player();
            players[i].playerName = listOfNames[i];
        }

        return players;
    }


    // method to create Team Objects;

    static Team[] createTeams(int numOfTeams, String[] listOfNames) {
        Team[] teams = new Team[numOfTeams];
        for (int i = 0; i < numOfTeams; i++) {

            teams[i] = new Team();
            teams[i].teamName = listOfNames[i];
        }
        return teams;
    }


    // method to assign players to teams;

    static void assignPlayersToTeams(int teamSize, Player[] players, Team[] teams) {

        for (int i = 0; i < teams.length; i++) {
            teams[i].rosterOfPlayers = new Player[teamSize];

            for (int j = 0; j < teamSize; j++) {
                teams[i].rosterOfPlayers[j] = players[i * teamSize + j];
            }
        }
    }


    // method for tossing-up of two teams, that will play the game;

    static int[] tossUp(Team[] teams) {

        int numberOfTeams = teams.length;
        int[] indicesOfTeams = new int[2];
        Random rand = new Random();

        indicesOfTeams[0] = indicesOfTeams[1] = rand.nextInt(numberOfTeams - 1);

        while (indicesOfTeams[0] == indicesOfTeams[1]) {
            indicesOfTeams[1] = rand.nextInt(numberOfTeams - 1);
        }
        return indicesOfTeams;

    }


    // method to print the roster of players;

    static void printRoster(int teamSize, Team[] teams) {

        System.out.println();

        for (int i = 0; i < teams.length; i++) {
            System.out.println(teams[i].teamName + ": ");

            for (int j = 0; j < teamSize; j++) {
                System.out.println((j + 1) + ". " + teams[i].rosterOfPlayers[j].playerName);

            }
            System.out.println();
        }
    }


    // the players who scored, the times of goals ...

    static void scoredGoals(int teamSize, int numberOfGoals, Game newGame) {

        newGame.scoredGoals = new Goal[numberOfGoals];
        int playerScored;
        Random rand = new Random();

        for (int i = 0; i < numberOfGoals; i++) {

            Goal goal = new Goal();

            playerScored = rand.nextInt(teamSize * 2 - 1);
            goal.scoredTime = Math.round(rand.nextDouble() * 90);

            if (playerScored < teamSize) {
                goal.scoredTeam = newGame.homeTeam;
                goal.scoredPlayer = newGame.homeTeam.rosterOfPlayers[playerScored];
            } else {
                goal.scoredTeam = newGame.awayTeam;
                goal.scoredPlayer = newGame.awayTeam.rosterOfPlayers[playerScored - 6];
            }

            newGame.scoredGoals[i] = goal;

        }
        // sort goals by time;
        Arrays.sort(newGame.scoredGoals, new Comparator<Goal>() {
            @Override
            public int compare(Goal goal, Goal t1) {
                return Double.compare(goal.scoredTime,t1.scoredTime);
            }
        });

    }

    // Printing out The Game Object Content;

    static void printTheGame(Game game) {

        System.out.println();

        System.out.println("Home Team: " + game.homeTeam.teamName);
        System.out.println("Away Team: " + game.awayTeam.teamName);

        if (game.scoredGoals.length == 0) {
            System.out.println("Draw game");
        }
        System.out.println();

        for (int i = 0; i < game.scoredGoals.length; i++) {
            System.out.println("Goal " + (i + 1));
            System.out.println("Player scored: " + game.scoredGoals[i].scoredPlayer.playerName);
            System.out.println("Team scored: " + game.scoredGoals[i].scoredTeam.teamName);
            System.out.println("Time scored: " + game.scoredGoals[i].scoredTime);
            System.out.println();


        }
    }
}