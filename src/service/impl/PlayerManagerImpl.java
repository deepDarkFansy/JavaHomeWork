package service.impl;

import dao.impl.PlayerDaoImpl;
import dao.impl.ProjectDaoImpl;
import dao.impl.ScoreDaoImpl;
import etity.Player;
import etity.Project;
import etity.Score;
import service.PlayerManage;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class PlayerManagerImpl implements PlayerManage {

    /**
     *
     * 比较两个项目时间是否冲突
     * @param project1 第一个项目
     * @param project2 第二个项目
     * @return
     */
    public boolean isOK(Project project1, Project project2){
        if (!project1.getDate().equals(project2.getDate())){
            return true;
        }
        long beginTime1 = project1.getTime().getTime();
        long endTime1 = beginTime1 + project1.getTimeLength()*60*1000;
        long beginTime2 = project2.getTime().getTime();
        long endTime2 = beginTime2 + project2.getTimeLength()*60*1000;

        System.out.println(beginTime1+" "+ endTime1);
        System.out.println(beginTime2+" "+ endTime2);
        return beginTime1 >= endTime2 || endTime1 <= beginTime2;
    }

    /**
     * 选手参赛
     * @param player
     * @param project
     * @return
     */
    public boolean takePart(Player player, Project project){
        ScoreDaoImpl scoreDao = new ScoreDaoImpl();
        List<Score> scoreList = null;
        String sql = "SELECT * FROM score WHERE playerID=?";
        String[] params = new String[1];
        params[0] = player.getPlayerID() + "";
        scoreList = scoreDao.getScore(sql, params);
        if(scoreList == null){
            String sql_1 = "INSERT INTO score (projectID, playerID)" +
                    "VALUES (?, ?)";
            String[] params_1 = new String[2];
            params_1[0] = project.getProjectID() + "";
            params_1[1] = player.getPlayerID() + "";
            scoreDao.updateScore(sql_1, params_1);
            return true;
        }else{
            List<Project> projectlist = null;
            ProjectDaoImpl projectDao = new ProjectDaoImpl();
            String sql_1 = "SELECT * FROM project WHERE projectID=?";
            int length = scoreList.size();
            String[] params_1 = new String[length];
            for(int i=0; i<length; i++){
                params_1[i] = scoreList.get(i).getProjectID() + "";
            }
            projectlist = projectDao.getProject(sql_1, params_1);
            int flag = 1;
            for(Project myproject: projectlist){
                if(!isOK(myproject, project)){
                    flag = 0;
                    break;
                }
            }
            if(flag == 1){
                String sql_2 = "INSERT INTO score (projectID, playerID) " +
                        "VALUES (?, ?)";
                String[] params_2 = new String[2];
                params_2[0] = project.getProjectID() + "";
                params_2[1] = player.getPlayerID() + "";
                scoreDao.updateScore(sql_2, params_2);
                return true;
            }
        }
        return false;
    }

    /**
     * 记录成绩
     * @param player
     * @param project
     * @param score
     * @return
     */
    public boolean recordResult(Player player, Project project, String score){
        String sql = "UPDATE score SET score=? " +
                "WHERE projectID=? AND playerID=?";
        String[] params = new String[3];
        params[0] = score;
        params[1] = player.getPlayerID()+"";
        params[2] = project.getProjectID()+"";
        ScoreDaoImpl scoreDao = new ScoreDaoImpl();
        int rc = scoreDao.updateScore(sql, params);
        if(rc == 0){
            return false;
        }
        return true;
    }

    public boolean dropOutGame(Player player, Project project){
        String sql = "DELETE FROM score " +
                "WHERE playerID=? AND projectID=?";
        String[] params = new String[2];
        params[0] = player.getPlayerID()+"";
        params[1] = project.getProjectID()+"";
        ScoreDaoImpl scoreDao = new ScoreDaoImpl();
        int rc = scoreDao.updateScore(sql, params);
        if(rc == 0){
            return false;
        }
        return true;
    }
}
