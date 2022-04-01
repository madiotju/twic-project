package com.dao;

import java.sql.*;
import java.util.ArrayList;

public class VilleDaoMysql implements VilleDao{
    private DaoFactory daoFactory;

    public VilleDaoMysql(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    @Override
    public ArrayList<String> getVille(String codePostal) throws DaoException {
        ArrayList<String> villes = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = daoFactory.getConnection();
            statement = connection.createStatement();
            if (codePostal == null){
                String query = "SELECT Nom_commune FROM ville_france";
                resultSet = statement.executeQuery(query);
            } else {
                String query = "SELECT Nom_commune FROM ville_france WHERE Code_postal="+codePostal;
                resultSet = statement.executeQuery(query);

            }
            while (resultSet.next()) {
                villes.add(resultSet.getString("Nom_commune"));
            }
        } catch (SQLException e) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException e2) {
            }
            throw new DaoException("Impossible de communiquer avec la base de données");
        }
        finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new DaoException("Impossible de communiquer avec la base de données");
            }
        }

        return villes;
    }

    @Override
    public void saveVille(String codeCommune, String nomCommune, String codePostal, String libelle,String ligne5,
                          String latitude, String longitude) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO ville_france(Code_commune_INSEE,Nom_commune," +
                    "Code_postal,Libelle_acheminement,Ligne_5,Latitude,Longitude)" +
                    " VALUES(?, ?, ?, ?, '', ?, ?);");
            preparedStatement.setString(1,codeCommune);
            preparedStatement.setString(2,nomCommune);
            preparedStatement.setString(3,codePostal);
            preparedStatement.setString(4,libelle);
            preparedStatement.setString(5,latitude);
            preparedStatement.setString(6,longitude);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException e2) {
            }
            throw new DaoException("Impossible de communiquer avec la base de données");
        }
        finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new DaoException("Impossible de communiquer avec la base de données");
            }
        }
    }
}
