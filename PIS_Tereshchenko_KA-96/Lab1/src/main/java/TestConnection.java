import connection.ConnectionPool;
import dao.DaoFactory;
import dao.inte.UserDao;


import java.sql.Connection;
import java.sql.SQLException;

public class TestConnection {
    public static void main(String[] args) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();


        Connection conn1 = pool.getConnection();
        UserDao userDao = DaoFactory.getUserDao(conn1);
        userDao.delete("username");
        System.out.println(userDao.get("max"));

    }
}
