package com.form;

import com.dao.DaoException;
import com.dao.DaoFactory;
import com.dao.VilleDao;
import com.dao.VilleDaoMysql;

public class VilleSaver {
    private String[] fullValues;
    private DaoFactory daoFactory;
    private String[] values;

    public VilleSaver(String request) throws FormException {
        fullValues = request.split("&");
        if (fullValues.length != 7) {
            throw new FormException("La requête ne contient pas tous les éléments nécessaires");
        }
        daoFactory = DaoFactory.getInstance();
        setValues();
    }

    private void setValues() {
        values = new String[7];
        for (int i=0; i< fullValues.length; i++) {
            values[i] = fullValues[i].split("=")[1];
        }
    }

    public void saveVille() throws DaoException {
        VilleDao villeDao = daoFactory.getVilleDao();
        villeDao.saveVille(values[0],values[1],values[2],values[3],values[4],values[5],values[6]);
    }

}
