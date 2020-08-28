package dao;

import etity.Project;

import java.util.List;

public interface ProjectDao {
    public int updateProject(String sql, Object[] param);

    public List<Project> getProject(String sql, String[] param);
}
