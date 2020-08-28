package service.impl;

import dao.PlayerDao;
import dao.ProjectDao;
import dao.impl.PlayerDaoImpl;
import dao.impl.ProjectDaoImpl;
import dao.impl.ScoreDaoImpl;
import etity.Player;
import etity.Project;
import etity.Score;
import service.Inputable;
import service.Printable;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Scanner;

public class IOManagerImpl implements Inputable, Printable {
    public Player playerInput(){
        Player player = new Player();
        Scanner input = new Scanner(System.in);
        System.out.print("请输入选手的ID： ");
        player.setPlayerID(input.nextInt());
        System.out.print("请输入选手姓名： ");
        player.setName(input.next());
        System.out.print("请输入选手性别(0：男， 1：女)： ");
        player.setSex(input.nextInt());
        System.out.print("请输入选手年龄： ");
        player.setAge(input.nextInt());
        return player;
    }

    public Project projectInput(){
        Project project = new Project();
        Scanner input = new Scanner(System.in);
        System.out.print("请输入项目ID： ");
        project.setProjectID(input.nextInt());
        System.out.print("请输入项目名称： ");
        project.setName(input.next());
        int flag = 1;
        do {
            System.out.print("请输入比赛日期（年-月-日）： ");
            try {
                Date temp = Date.valueOf(input.next());
                project.setDate(temp);
                flag = 0;
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                System.out.println("输入的格式不对，请重新输入！");
            }
        } while (flag == 1);
        flag = 1;
        do{
            System.out.print("请输入比赛开始时间（时：分：秒）： ");
            try{
                Time temp = Time.valueOf(input.next());
                project.setTime(temp);
                flag = 0;
            }catch (IllegalArgumentException e) {
                e.printStackTrace();
                System.out.println("输入的格式不对，请重新输入！");
            }
        } while(flag == 1);
        System.out.print("请输入比赛的类型： ");
        project.setType(input.next());
        System.out.println("请输入比赛的时长： ");
        project.setTimeLength(input.nextInt());
        return project;
    }


    public void printProjectList(){
        ProjectDaoImpl projectDao =new ProjectDaoImpl();
        List<Project> projectList = null;
        String sql= "SELECT * FROM project";
        projectList = projectDao.getProject(sql, null);
        if(projectList.size() == 0){
            System.out.println("抱歉，没有任何比赛项目信息！");
        }else{
            System.out.println("比赛项目汇总如下：");
            for(Project project: projectList){
                System.out.println("\t"+ project.getName());
            }
            System.out.println("#####################");
        }
    }
    public void printPlayerList(){
        PlayerDaoImpl playerDao = new PlayerDaoImpl();
        List<Player> playerList = null;
        String sql = "SELECT * FROM player";
        playerList = playerDao.getPlayer(sql, null);
        if(playerList.size() == 0){
            System.out.println("抱歉，没有任何参赛选手的信息");
        }else{
            System.out.println("全部参数选手名单如下");
            for(Player player: playerList){
                System.out.println("\t"+player.getName());
            }
            System.out.println("#####################");
        }
    }
    public boolean printCertainProject(String projectName){
        ProjectDaoImpl projectDao = new ProjectDaoImpl();
        List<Project> projectList = null;
        String sql = "SELECT * FROM project WHERE name=?";
        String[] params = new String[1];
        params[0] = projectName;
        projectList = projectDao.getProject(sql, params);
        if (projectList.size() == 0){
            System.out.println("查询不到该项目！");
            return false;
        }else{
            Project project = projectList.get(0);
            System.out.println(project.toString());
            ScoreDaoImpl scoreDao = new ScoreDaoImpl();
            String sql_1 = "SELECT * FROM score WHERE projectID=?";
            String[] params_1 = new String[1];
            params_1[0] = project.getProjectID()+"";
            List<Score> scoreList = scoreDao.getScore(sql_1, params_1);
            int num = scoreList.size();
            System.out.println("\t参赛人数: "+ num);
        }
        return true;
    }
    public boolean printCertainPlayer(String playerName){
        PlayerDaoImpl playerDao = new PlayerDaoImpl();
        List<Player> playerList = null;
        String sql = "SELECT * FROM player WHERE name=?";
        String[] params = new String[1];
        params[0] = playerName;
        playerList = playerDao.getPlayer(sql, params);
        if (playerList.size() == 0){
            System.out.println("查询不到该运动员！");
            return false;
        }else {
            Player player = playerList.get(0);
            System.out.println(player.toString());
            ScoreDaoImpl scoreDao = new ScoreDaoImpl();
            String sql_1 = "SELECT * FROM score WHERE playerID=?";
            String[] params_1 = new String[1];
            params_1[0] = player.getPlayerID()+"";
            List<Score> scoreList = scoreDao.getScore(sql_1, params_1);
            int num = scoreList.size();
            System.out.println("\t参加的比赛项目数: "+ num);
            System.out.print("\t\t分别是: ");
            ProjectDaoImpl projectDao = new ProjectDaoImpl();
            for(Score score: scoreList){
                sql = "SELECT * FROM project WHERE projectID=?";
                params[0] = score.getProjectID()+"";
                List<Project> project =  projectDao.getProject(sql, params);
                System.out.print(project.get(0).getName()+"  ");
            }
            System.out.println();
        }
        return true;
    }

    /**
     * 打印菜单
     * @param type 0：打印主菜单， 1：打印项目菜单， 2：打印选手菜单， 3：打印成绩菜单
     */
    @Override
    public void printMenu(int type){
        String mainMenu = "欢迎来到运动会管理系统\n" +
                "请选择你想要的服务:\n" +
                "\t1). 比赛项目管理\n" +
                "\t2). 选手管理\n" +
                "\t3). 成绩管理\n" +
                "\t4). 退出系统\n";

        String projectMenu = "比赛项目管理\n" +
                "请选择你想要的服务:\n" +
                "\t1). 查看所有比赛信息\n" +
                "\t2). 查询某个比赛相关信息\n" +
                "\t3). 添加比赛信息\n" +
                "\t4). 返回上一层\n" +
                "\t5). 退出系统\n";

        String playerMenu = "选手信息管理\n" +
                "请选择你想要的服务:\n" +
                "\t1). 查看所有参赛选手信息\n" +
                "\t2). 查询某个选手相关信息\n" +
                "\t3). 添加选手信息\n" +
                "\t4). 选手参赛\n" +
                "\t5). 返回上一层\n" +
                "\t6). 退出系统\n";

        String scoreMenu = "成绩信息管理\n" +
                "请选择你想要的服务:\n" +
                "\t1). 记录比赛成绩\n" +
                "\t2). 查询成绩\n" +
                "\t3). 返回上一层\n" +
                "\t4). 退出系统\n";

        if (type == 0){
            System.out.println(mainMenu);
        }else if(type == 1){
            System.out.println(projectMenu);
        }else if(type == 2){
            System.out.println(playerMenu);
        }else if(type == 3){
            System.out.println(scoreMenu);
        }else{
            System.out.println("没有该菜单！");
        }
    }

}
