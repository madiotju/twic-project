package com.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface VilleDao {
    public List<String> getVille(String codePostal) throws DaoException, SQLException;
    public void saveVille(String codeCommune, String nomCommune, String codePostal, String libelle
            , String ligne5, String latitude, String longitude) throws DaoException, SQLException;
    public void changeVille(String codeCommune,String columnToUpdate, String value) throws DaoException, SQLException;
    public void deleteVille(String codeCommune) throws DaoException, SQLException;
    public String[] getLocation(String nomVille) throws DaoException, SQLException;
    public ArrayList<ArrayList<String>> getCompleteVille(String codePostal) throws DaoException, SQLException;
}
