package com.form;

import com.dao.DaoException;
import com.dao.DaoFactory;
import com.dao.VilleDao;

public class VilleEditer {
    private String[] fullValues;
    private final DaoFactory daoFactory;
    private String[] values;
    private String[] intitules;

    public VilleEditer(String request) {
        request = request.replace("{","");
        request = request.replace("}","");
        request = request.replace("\"","");
        fullValues = request.split(",");
        daoFactory = DaoFactory.getInstance();
        setValues();
    }

    private void setValues() {
        values = new String[fullValues.length];
        intitules = new String[fullValues.length];
        for (int i=0; i< fullValues.length; i++) {
            if (fullValues[i].split(":").length == 2){
                values[i] = fullValues[i].split(":")[1];
            } else {
                values[i] = "";
            }
            intitules[i] = fullValues[i].split(":")[0];
        }
    }

    public void changeVille() throws DaoException {
        VilleDao villeDao = daoFactory.getVilleDao();
        for (int i=1; i< fullValues.length; i++) {
            villeDao.changeVille(values[0],intitules[i],values[i]);
        }
    }
}
