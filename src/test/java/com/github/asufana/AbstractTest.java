package com.github.asufana;

import java.sql.*;

import org.h2.tools.*;
import org.junit.*;

public abstract class AbstractTest {
    
    protected static Server db;
    protected static Connection connection = null;
    
    @BeforeClass
    public static void BeforeClass() throws SQLException {
        db = Server.createWebServer(new String[] {"-webAllowOthers"});
        System.out.println("Database start.");
        connection = createConnection(T.params.get("db.url"),
                                      T.params.get("db.user"),
                                      T.params.get("db.pass"));
        System.out.println("Connect.");
        System.out.println();
    }
    
    private static Connection createConnection(final String url,
                                               final String user,
                                               final String pass) {
        try {
            return DriverManager.getConnection(url, user, pass);
        }
        catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    @AfterClass
    public static void AfterClass() throws SQLException {
        System.out.println();
        if (connection.isClosed() != false) {
            connection.close();
        }
        System.out.println("Disconnect.");
        db.stop();
        System.out.println("Database stop.");
    }
}
