
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


        // instantiation of the arrays for players and teams;

        Player players[] = new Player[totalPlayers];
        Team teams[] = new Team[totalTeams];

        // METHODS
        // instantiation of players in an array and assigning <playerName> properties;

        // printing out a message about the numbers of players, teams and the size of a team;

        System.out.println("Team size: " + teamSize);
        System.out.println("Total players: " + totalPlayers);
        System.out.println("Total teams: " + totalTeams + '\n');
        System.out.println();

        // short-circuit AND is used here to control
        // the number of players, teams and the team size

        if ((totalPlayers % teamSize == 0) && (totalPlayers / teamSize == totalTeams)) {


            // instantiation of players in an array and assigning <playerName> properties;

            for (int i = 0; i < totalPlayers; i++) {

                players[i] = new Player();
                players[i].playerName = playerNames[i];
            }


            // instantiation of teams in an array, and assigning <teamName> properties;

            for (int i = 0; i < totalTeams; i++) {

                teams[i] = new Team();
                teams[i].teamName = teamNames[i];
            }


            // assigning players to teams and printing rosterOfPlayers;

            for (int i = 0; i < totalTeams; i++) {

                System.out.println();
                System.out.println(teams[i].teamName + ": ");


                // instantiating <rosterOfPlayers> property for each team;

                teams[i].rosterOfPlayers = new String[teamSize];


                // assigning players to teams by writing their names to the rosterOfPlayers array;

                for (int j = 0; j < teamSize; j++) {

                    teams[i].rosterOfPlayers[j] = players[i * teamSize + j].playerName;
                    System.out.println((j + 1) + ". " + teams[i].rosterOfPlayers[j]);
                }
            }

            ////////////////////////////////////////////////////////////////////////////////////////////////

            Random r = new Random();

            Game newGame = new Game();


            // finding out home and away teams;

            int indexOfHomeTeam, indexOfAwayTeam;

            indexOfHomeTeam = indexOfAwayTeam = r.nextInt(totalTeams - 1);
            newGame.homeTeam = teams[indexOfHomeTeam].teamName;

            while (indexOfHomeTeam == indexOfAwayTeam) {
                indexOfAwayTeam = r.nextInt(totalTeams - 1);
                newGame.awayTeam = teams[indexOfAwayTeam].teamName;
            }

            System.out.println();
            System.out.println(newGame.homeTeam + " vs " + newGame.awayTeam);
            System.out.println();

            // finding out the number of goals during the game;

            int numberOfGoals = r.nextInt(5);

            Goal scoredGoals[] = new Goal[numberOfGoals];

            int playerScored; // the index of player who scored

            if (numberOfGoals == 0) {
                System.out.println("Nobody scored in this game.");
            }
            // finding out the players who scored;

            for (int i = 0; i < numberOfGoals; i++) {
                scoredGoals[i] = new Goal();
                playerScored = r.nextInt(teamSize * 2 - 1);
                scoredGoals[i].scoredTime = Math.round(r.nextDouble() * 90);

                if (playerScored < teamSize) {
                    scoredGoals[i].scoredTeam = newGame.homeTeam;
                    scoredGoals[i].scoredPlayer = teams[indexOfHomeTeam].rosterOfPlayers[playerScored];
                } else {
                    scoredGoals[i].scoredTeam = newGame.awayTeam;
                    scoredGoals[i].scoredPlayer = teams[indexOfAwayTeam].rosterOfPlayers[playerScored - 6];
                }
                System.out.println(scoredGoals[i].scoredPlayer + " from " +
                        scoredGoals[i].scoredTeam + " scored at " + scoredGoals[i].scoredTime + " minutes.");
            }

            newGame.scoredGoals = scoredGoals;


            // Printing out The Game Object Content;

            System.out.println();
            System.out.println("Info from the Game object: ");
            System.out.println();
            System.out.println("Home Team: " + newGame.homeTeam);
            System.out.println("Away Team: " + newGame.awayTeam);
            System.out.println();
            for (int i = 0; i < newGame.scoredGoals.length; i++) {
                System.out.println("Goal " + (i+1));
                System.out.println("Player scored: " + newGame.scoredGoals[i].scoredPlayer);
                System.out.println("Team scored: " + newGame.scoredGoals[i].scoredTeam);
                System.out.println("Time scored: " + newGame.scoredGoals[i].scoredTime);
                System.out.println();

            }

        } else {
            System.out.println(errorMessage);
        }

    }
}