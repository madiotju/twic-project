package com.controller;

import com.dao.DaoException;
import com.dao.DaoFactory;
import com.dao.VilleDao;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class VilleController {
	private DaoFactory daoFactory = DaoFactory.getInstance();



	// fonction pour récupérer le contenu de la BDD
	@RequestMapping(value="/ville", method=RequestMethod.GET)
	public ArrayList get(@RequestParam(required  = false, value="codePostal") String codePostal) throws DaoException {
		System.out.println("get");
		// TODO : mon code vers la BDD
		System.out.println(codePostal);
		VilleDao villeDao = daoFactory.getVilleDao();
		ArrayList<String> villes = villeDao.getVille(codePostal);

		return villes;
	}
	
	// TODO : 
	// fonction pour enregistrer un element dans la BDD
	@RequestMapping(value = "/ville", method = RequestMethod.POST)
	@ResponseBody
	public String insert() {
		return null;
	}


	@RequestMapping(value = "/ajoutVille", method = RequestMethod.GET)
	public void post(@RequestParam(value = "codeCommune")String codeCommune,
					 @RequestParam(value = "nomCommune") String nomCommune,
					 @RequestParam(value = "codePostal") String codePostal,
					 @RequestParam(value = "libelle") String libelle,
					 @RequestParam(value = "latitude") String latitude,
					 @RequestParam(value = "longitude") String longitude) throws DaoException {
		System.out.println("post");
		VilleDao villeDao = daoFactory.getVilleDao();
		villeDao.saveVille(codeCommune,nomCommune,codePostal,libelle,latitude,longitude);
	}

	@RequestMapping(value = "/zebi",method = RequestMethod.PUT)
	public void put(){

	}

}