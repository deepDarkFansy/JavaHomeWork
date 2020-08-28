package dao;

import etity.Score;

import java.util.List;

public interface ScoreDao {

    public List<Score> getScore(String sql, String[] param);

    public int updateScore(String sql, Object[] param);
}
