package service.impl;

import dao.impl.PlayerDaoImpl;
import dao.impl.ProjectDaoImpl;
import etity.Player;
import etity.Project;
import service.MainSystem;
import service.PlayerManage;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainSystemImpl implements MainSystem {
    private IOManagerImpl IOManager = new IOManagerImpl();
    private PlayerManagerImpl playerManager = new PlayerManagerImpl();
    private ScoreManagerImpl scoreManager = new ScoreManagerImpl();

    @Override
    public void run(){
        Scanner input = new Scanner(System.in);
        // 循环控制条件
        boolean flag = true;
        // 选项
        while(flag){
            IOManager.printMenu(0);
            System.out.print("您的选择是：  ");
            try{
                int option;
                option = input.nextInt();
                switch (option){
                    case 1:
                        flag = projectPart();
                        break;
                    case 2:
                        flag = playerPart();
                        break;
                    case 3:
                        flag = scorePart();
                        break;
                    case 4:
                        flag = false;
                        quitSystem();
                        break;
                    default:
                        System.out.println("您输入的选项有误，请重新输入！");
                }
            }catch (InputMismatchException e){
                System.out.println("您输入的选项有误，请重新输入！");
                input.next();
            }

        }
    }

    private boolean projectPart(){
        Scanner input = new Scanner(System.in);
        int option = -1;
        while(true){
            IOManager.printMenu(1);
            System.out.print("您的选择是：  ");
            try{
                option = input.nextInt();
            }catch (Exception e){
                System.out.println("您输入的选项有误，请重新输入！");
                input.next();
                continue;
            }

            switch (option){
                case 1:
                    IOManager.printProjectList();
                    break;
                case 2:
                    System.out.print("请输入你想查询的比赛:  ");
                    IOManager.printCertainProject(input.next());
                    break;
                case 3:
                    Project project = IOManager.projectInput();
                    ProjectDaoImpl projectDao = new ProjectDaoImpl();
                    projectDao.insertProject(project);
                    break;
                case 4:
                    return true;
                case 5:
                    quitSystem();
                    return false;
                default:
                    System.out.println("您输入的选项有误，请重新输入！");
            }
        }
    }

    private boolean playerPart(){
        Scanner input = new Scanner(System.in);
        int option = -1;
        while(true){
            IOManager.printMenu(2);
            System.out.print("您的选择是：  ");
            try{
                option = input.nextInt();
            }catch (Exception e){
                System.out.println("您输入的选项有误，请重新输入！");
                input.next();
                continue;
            }
            switch (option){
                case 1:
                    IOManager.printPlayerList();
                    break;
                case 2:
                    System.out.print("请输入选手的名字： ");
                    IOManager.printCertainPlayer(input.next());
                    break;
                case 3:
                    Player player = IOManager.playerInput();
                    PlayerDaoImpl playerDao = new PlayerDaoImpl();
                    playerDao.insertPlayer(player);
                    break;
                case 4:
                    System.out.print("请输入选手ID:  ");
                    Player player1 = new Player();
                    player1.setPlayerID(input.nextInt());
                    System.out.print("请输入比赛ID:  ");
                    Project project = new Project();
                    project.setProjectID(input.nextInt());
                    playerManager.takePart(player1, project);
                    break;
                case 5:
                    return true;
                case 6:
                    quitSystem();
                    return false;
                default:
                    System.out.println("您输入的选项有误，请重新输入！");
            }
        }
    }

    private boolean scorePart(){
        Scanner input = new Scanner(System.in);
        int option = -1;
        while(true){
            IOManager.printMenu(3);
            System.out.print("您的选择是：  ");
            try{
                option = input.nextInt();
                switch (option){
                    case 1:
                        System.out.print("请输入你想记录的比赛项目:  ");
                        scoreManager.recordIt(input.next());
                        break;
                    case 2:
                        break;
                    case 3:
                        return true;
                    case 4:
                        quitSystem();
                        return false;
                    default:
                        System.out.println("您输入的选项有误，请重新输入！");
                }
            }catch (Exception e){
                System.out.println("您输入的选项有误，请重新输入！");
                input.next();
            }

        }
    }

    private void quitSystem(){
        System.out.println("成功退出系统！！！");
        System.out.println("感谢您的使用！");
    }

}
