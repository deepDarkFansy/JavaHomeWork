package dao.impl;

import dao.BaseDao;
import dao.ProjectDao;
import etity.Project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProjectDaoImpl extends BaseDao implements ProjectDao {
    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    @Override
    public int updateProject(String sql, Object[] param){
        int count =  executeSQL(sql, param);
        return count;
    }

    public int insertProject(Project project){
        String sql = "INSERT INTO project (projectID, name, type, time, timeLength) " +
                "VALUES (?, ?, ?, ?, ?)";
        String[] params = new String [5];
        params[0] = project.getProjectID()+"";
        params[1] = project.getName();
        params[2]= project.getType();

        params[3] = project.getDate()+ " " + (project.getTime() + "");
        params[4] = project.getTimeLength()+"";
        int count = executeSQL(sql, params);
        return count;
    }

    @Override
    public List<Project> getProject(String sql, String[] param){
        List<Project> projectList = new ArrayList<>();
        try{
            conn = getConn();
            pstmt = conn.prepareStatement(sql);
            if (param != null){
                for(int i=0; i<param.length; i++){
                    pstmt.setString(i+1, param[i]);
                }
            }
            rs = pstmt.executeQuery();
            Project project = null;
            while (rs.next()) {
                project = new Project();
                project.setProjectID(rs.getInt(1));
                project.setName(rs.getString(2));
                project.setType(rs.getString(3));
                project.setRecord(rs.getString(4));
                project.setTime(rs.getTime(5));
                project.setDate(rs.getDate(5));
                project.setTimeLength(rs.getInt(6));
                projectList.add(project);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
            closeAll(conn, pstmt, rs);
        }
        return projectList;
    }
}
