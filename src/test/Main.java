package test;

import dao.impl.ProjectDaoImpl;
import etity.Project;
import service.PlayerManage;
import service.impl.IOManagerImpl;
import service.impl.PlayerManagerImpl;

import java.sql.Date;
import java.sql.Time;
import java.util.List;



public class Main {
    public static void main(String[] args) {
//        ProjectDaoImpl project = new ProjectDaoImpl();
//        String sql = "SELECT * from project";
//        List<Project> rc = project.getProject(sql, null);
//        for (Project aRc : rc) {
//            System.out.println(aRc.toString());
//        }

//        PlayerManagerImpl managerTest = new PlayerManagerImpl();
//        Project dd1 = new Project();
//        Project dd2 = new Project();
//        dd1.setDate(Date.valueOf("2020-08-27"));
//        dd1.setTime(Time.valueOf("6:25:00"));
//        dd1.setTimeLength(60);
//
//        dd2.setDate(Date.valueOf("2020-08-27"));
//        dd2.setTime(Time.valueOf("6:00:00"));
//        dd2.setTimeLength(24);
//
//        System.out.println(managerTest.isOK(dd1, dd2));

        IOManagerImpl IOManager = new IOManagerImpl();
        IOManager.printPlayerList();
        System.out.println("----------------------------");
        IOManager.printCertainPlayer("瑞克");
    }
}
