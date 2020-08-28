package dao;


import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class BaseDao {
    public static String DRIVER;
    public static String URL;
    public static String DBNAME;
    public static String DBPASS;

    Connection conn = null;

    static{
        init();
    }

    public static void init(){
        Properties params=new Properties();
        String configFile = "database.properties";
        InputStream is = BaseDao.class.getClassLoader().getResourceAsStream(configFile);
        try{
            params.load(is);
        }catch (IOException e){
            e.printStackTrace();
        }
        DRIVER = params.getProperty("driver");
        URL = params.getProperty("url");
        DBNAME = params.getProperty("user");
        DBPASS = params.getProperty("password");
    }

    /**
     *
     * @return 数据连接
     * @throws ClassNotFoundException
     * @throws SQLClientInfoException
     */
    public Connection getConn()throws ClassNotFoundException, SQLClientInfoException{
        Connection conn = null;

        try{
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, DBNAME, DBPASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }

    public void closeAll(Connection conn, PreparedStatement pstmt, ResultSet rs){
        if (rs != null){
            try{
                rs.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        if (pstmt != null){
            try{
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (conn != null){
            try{
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public int executeSQL(String preparedSql, Object[] param){
        Connection conn = null;
        PreparedStatement pstmt = null;
        int num = 0;

        try{
            conn = getConn();
            pstmt = conn.prepareStatement(preparedSql);
            if(param != null){
                for (int i=0; i<param.length; i++){
                    pstmt.setObject(i+1, param[i]);
                }
            }
            num = pstmt.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally{
            this.closeAll(conn, pstmt, null);
        }
        return num;
    }
}
