package dev.reid.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
    public static Connection createConnection()
    {
        try {
            Connection conn = DriverManager.getConnection(System.getenv("AZURE_SQL_DB"));

            System.out.println(conn);
            return conn;
        } catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }

    }
}
