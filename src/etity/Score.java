package etity;

public class Score {
    private int projectID;
    private int playerID;
    private String score;
    private int rank;


    @Override
    public String toString() {
        return "Score{" +
                "projectID=" + projectID +
                ", playerID=" + playerID +
                ", score='" + score + '\'' +
                ", rank=" + rank +
                '}';
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
