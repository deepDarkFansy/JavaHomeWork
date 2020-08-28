package dao.impl;

import dao.BaseDao;
import dao.PlayerDao;
import etity.Player;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerDaoImpl extends BaseDao implements PlayerDao {
    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    @Override
    public int updatePlayer(String sql, Object[] param){
        int count =  executeSQL(sql, param);
        return count;
    }

    @Override
    public List<Player> getPlayer(String sql, String[] param){
        List<Player> playerList = new ArrayList<>();
        try{
            conn = getConn();
            pstmt = conn.prepareStatement(sql);
            if (param != null){
                for(int i=0; i<param.length; i++){
                    pstmt.setString(i+1, param[i]);
                }
            }
            rs = pstmt.executeQuery();
            Player player = null;
            while (rs.next()) {
                player = new Player();
                player.setPlayerID(rs.getInt(1));
                player.setName(rs.getString(2));
                player.setSex(rs.getInt(3));
                player.setAge(rs.getInt(4));
                playerList.add(player);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
            closeAll(conn, pstmt, rs);
        }
        return playerList;
    }
}
