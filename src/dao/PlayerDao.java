package dao;

import etity.Player;

import java.util.List;

public interface PlayerDao {
    public List<Player> getPlayer(String sql, String[] param);

    public int updatePlayer(String sql, Object[] param);
}
