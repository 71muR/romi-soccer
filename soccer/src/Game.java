

public class Game {

    Team homeTeam;
    Team awayTeam;
    Goal[] scoredGoals;

    // constructor;
    Game(){
        homeTeam = new Team();
        awayTeam = new Team();
        scoredGoals = new Goal[0];
    }
}

