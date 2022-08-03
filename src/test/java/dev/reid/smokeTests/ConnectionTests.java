package dev.reid.smokeTests;

import dev.reid.utils.ConnectionUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

public class ConnectionTests {
    @Test
    void connnection_available()
    {
        Connection connection = ConnectionUtil.createConnection();
        System.out.println(connection);
        Assertions.assertNotNull(connection);
    }
}
