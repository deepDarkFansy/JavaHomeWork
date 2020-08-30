package dao.impl;

import dao.BaseDao;
import dao.ScoreDao;
import etity.Score;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ScoreDaoImpl extends BaseDao implements ScoreDao {
    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    @Override
    public List<Score> getScore(String sql, String[] param){
        List<Score> scorelist = new ArrayList<>();
        try{
            conn = getConn();
            pstmt = conn.prepareStatement(sql);
            if (param != null){
                for(int i=0; i<param.length; i++){
                    pstmt.setString(i+1, param[i]);
                }
            }
            rs = pstmt.executeQuery();
            Score score = null;
            while(rs.next()){
                score = new Score();
                score.setProjectID(rs.getInt(1));
                score.setPlayerID(rs.getInt(2));
                score.setScore(rs.getString(3));
                score.setRankIt(rs.getInt(4));
                scorelist.add(score);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            closeAll(conn, pstmt, rs);
        }
        return scorelist;
    }

    @Override
    public int updateScore(String sql, Object[] param){
        int count =  executeSQL(sql, param);
        return count;
    }
}
