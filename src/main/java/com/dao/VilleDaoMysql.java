package com.dao;

import java.sql.*;
import java.util.ArrayList;

public class VilleDaoMysql implements VilleDao{
    private final DaoFactory daoFactory;
    private static final String ERROR_MESSAGE = "Impossible de communiquer avec la base de données";

    public VilleDaoMysql(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    @Override
    public ArrayList<String> getVille(String codePostal) throws DaoException, SQLException {
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
            } catch (SQLException ignored) {
            }
            throw new DaoException(ERROR_MESSAGE);
        }
        finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        return villes;
    }

    @Override
    public void saveVille(String codeCommune, String nomCommune, String codePostal, String libelle,String ligne5,
                          String latitude, String longitude) throws DaoException, SQLException {
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
            } catch (SQLException ignored) {
            }
            throw new DaoException(ERROR_MESSAGE);
        }
        finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public void changeVille(String codeCommune, String columnToUpdate, String value) throws DaoException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            String query = "UPDATE ville_france SET "+columnToUpdate+" = ? WHERE Code_commune_INSEE="+codeCommune;
            connection = this.daoFactory.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,value);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException ignored) {
            }
            throw new DaoException(ERROR_MESSAGE);
        }
        finally {
            if (preparedStatement != null){
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public void deleteVille(String codeCommune) throws DaoException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM ville_france WHERE Code_commune_INSEE=?");
            preparedStatement.setString(1,codeCommune);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException ignored) {
            }
            throw new DaoException(ERROR_MESSAGE);
        }
        finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public String[] getLocation(String nomVille) throws DaoException, SQLException {
        String[] location = new String[2];
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("SELECT Latitude, Longitude FROM ville_france WHERE Nom_commune=? LIMIT 1");
            preparedStatement.setString(1,nomVille);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                location[0] = resultSet.getString("Latitude");
                location[1] = resultSet.getString("Longitude");
            }
        } catch (SQLException e) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException ignored) {
            }
            throw new DaoException("Impossible de communiquer avec la base de données");
        }
        finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }


        }

        return location;
    }
}
