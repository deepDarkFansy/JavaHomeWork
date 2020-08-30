package service.impl;


import dao.impl.PlayerDaoImpl;
import dao.impl.ProjectDaoImpl;
import dao.impl.ScoreDaoImpl;
import etity.Score;
import service.ScoreManager;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ScoreManagerImpl implements ScoreManager {
    public void recordIt(String project){
        ProjectDaoImpl projectDao = new ProjectDaoImpl();
        String[] params = new String[1];
        params[0] = project;
        if(projectDao.getProject("select * from project where name=?", params).size() == 0){
            System.out.println("不存在该项目！");
            return;
        }
        int id = projectDao.getProject("select * from project where name=?", params).get(0).getProjectID();
        ScoreDaoImpl scoreDao = new ScoreDaoImpl();
        List<Score> scoreList =null;
        params[0] = id+"";
        scoreList = scoreDao.getScore("select * from score where projectID=?", params);
        for(Score score: scoreList){
            PlayerDaoImpl playerDao = new PlayerDaoImpl();
            params[0] = score.getPlayerID()+"";
            String player = playerDao.getPlayer("SELECT * FROM player WHERE playerID=?", params).get(0).getName();
            Scanner input = new Scanner(System.in);
            System.out.print("请输入 "+player+" 选手的成绩：  ");
            score.setScore(input.next());
        }
        Collections.sort(scoreList);
        int index = 1;

        for(Score score: scoreList){
            score.setRankIt(index);
            index++;
            String sql = "UPDATE score SET score=?, rankIt=? " +
                    "WHERE projectID=? AND playerID=?";
            String[] params_1 = new String[4];
            params_1[0] = score.getScore();
            params_1[1] = score.getRankIt()+"";
            params_1[2] = score.getProjectID()+"";
            params_1[3] = score.getPlayerID()+"";
            scoreDao.updateScore(sql, params_1);
        }
    }
    public void printRecord(String  project){
        ProjectDaoImpl projectDao = new ProjectDaoImpl();
        String[] params = new String[1];
        params[0] = project;
        if(projectDao.getProject("select * from project where name=?", params).size() == 0){
            System.out.println("不存在该项目！");
            return;
        }
        int id = projectDao.getProject("select * from project where name=?", params).get(0).getProjectID();
        ScoreDaoImpl scoreDao = new ScoreDaoImpl();
        List<Score> scoreList =null;
        params[0] = id+"";
        scoreList = scoreDao.getScore("select * from score where projectID=?", params);
        System.out.println("比赛信息如下: ");
        for(Score score: scoreList){
            PlayerDaoImpl playerDao = new PlayerDaoImpl();
            params[0] = score.getPlayerID()+"";
            String player = playerDao.getPlayer("SELECT * FROM player WHERE playerID=?", params).get(0).getName();
            System.out.println("\t"+player+"\t成绩:"+score.getScore()+"\t排名:"+score.getRankIt());
        }
    }
}
