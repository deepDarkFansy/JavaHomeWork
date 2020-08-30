package etity;

public class Score implements Comparable<Score>{
    private int projectID;
    private int playerID;
    private String score;
    private int rankIt;


    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public int getPlayerID() {
        return playerID;
    }

    @Override
    public String toString() {
        return "Score{" +
                "projectID=" + projectID +
                ", playerID=" + playerID +
                ", score='" + score + '\'' +
                ", rankIt=" + rankIt +
                '}';
    }

    public int getRankIt() {
        return rankIt;
    }

    public void setRankIt(int rankIt) {
        this.rankIt = rankIt;
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



    @Override
    public int compareTo(Score o) {
        if(score.endsWith("sec")){
            String[] temp = score.split("min");
            int min = Integer.valueOf(temp[0]);
            String[] temp1 = temp[1].split("sec");
            int sec = Integer.valueOf(temp1[0]);
            temp = o.score.split("min");
            int min1 = Integer.valueOf(temp[0]);
            temp1 = temp[1].split("sec");
            int sec1 = Integer.valueOf(temp1[0]);
            if(min == min1){
                return sec-sec1;
            }else{
                return min-min1;
            }
        }else if(score.endsWith("m")){
            String[] temp = score.split("m");
            int dis = Integer.valueOf(temp[0]);
            temp = o.score.split("m");
            int dis1 = Integer.valueOf(temp[0]);
            return dis1 - dis;
        }
        return 0;
    }
}
