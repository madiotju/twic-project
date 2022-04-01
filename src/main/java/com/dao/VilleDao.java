package com.dao;

import java.util.ArrayList;

public interface VilleDao {
    public ArrayList<String> getVille(String codePostal) throws DaoException;
    public void saveVille(String codeCommune, String nomCommune, String codePostal, String libelle
            , String ligne5, String latitude, String longitude) throws DaoException;
}
