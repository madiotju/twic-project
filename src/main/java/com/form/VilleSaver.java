package com.form;

import com.dao.DaoException;
import com.dao.DaoFactory;
import com.dao.VilleDao;

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
            if (fullValues[i].split("=").length == 2){
                values[i] = fullValues[i].split("=")[1];
            } else {
                values[i] = "";
            }

        }
    }

    public void saveVille() throws DaoException {
        VilleDao villeDao = daoFactory.getVilleDao();
        villeDao.saveVille(values[0],values[1],values[2],values[3],values[4],values[5],values[6]);
    }

}
