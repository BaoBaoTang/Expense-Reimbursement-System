package dao;

import config.ConfigurationFile;
import java.sql.*;

/**
 * @author Zimi Li
 */
public class Command {
    private String url = String.format("jdbc:postgresql://%s:%d/%s",
                                       ConfigurationFile.DATABASE_URL,
                                       ConfigurationFile.DATABASE_PORT,
                                       ConfigurationFile.DATABASE_NAME);
    private String username = ConfigurationFile.DATABASE_USERNAME;
    private String password = ConfigurationFile.DATABASE_PASSWORD;

    public Command() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            ConfigurationFile.SQL_LOGGER.error(e, e.fillInStackTrace());
        }
    }

    protected ResultSet select(String sql, Object ...args) {
        ResultSet ret = null;
        try (Connection con = DriverManager.getConnection(url, username, password)) {
            PreparedStatement ps = con.prepareStatement(sql);
            for (int i=0; i<args.length; i++)
                ps.setObject(i+1, args[i]);
            ret = ps.executeQuery();
        } catch (SQLException e) {
            ConfigurationFile.SQL_LOGGER.error(e, e.fillInStackTrace());
        }
        return ret;
    }

    protected Boolean update(String sql, Object ...args) {
        try (Connection con = DriverManager.getConnection(url, username, password)) {
            PreparedStatement ps = con.prepareStatement(sql);
            for (int i=0; i<args.length; i++)
                ps.setObject(i+1, args[i]);
            return ps.executeUpdate() != 0;
        } catch (SQLException e) {
            ConfigurationFile.SQL_LOGGER.error(e, e.fillInStackTrace());
        }
        return false;
    }
}
