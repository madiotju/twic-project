package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoFactory {
    private final String url;
    private final String username;
    private final String password;

    DaoFactory(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public static DaoFactory getInstance() {

        return new DaoFactory(
                "jdbc:mysql://localhost:8889/twic_maven", "test", "network");
    }

    public Connection getConnection() throws SQLException {
        Connection connexion = DriverManager.getConnection(url, username, password);
        connexion.setAutoCommit(false);

        return connexion;
    }

    // Récupération des Dao
    public VilleDao getVilleDao(){
        return new VilleDaoMysql(this);
    }
}
