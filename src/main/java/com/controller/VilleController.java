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
		VilleDao villeDao = daoFactory.getVilleDao();
		ArrayList<String> villes = villeDao.getVille(codePostal);

		return villes;
	}

	@RequestMapping(value = "/villepost", method = RequestMethod.POST)
	@ResponseBody
	public void post(@RequestBody String request) throws DaoException {
		request.replace("+"," ");
		String[] parts = request.split("&");
		for (String part : parts){
			System.out.println(part);
		}
		VilleDao villeDao = daoFactory.getVilleDao();
		//TODO: créer méthode dans dao pour save les éléments à partir du string récupéré par la méthode post
	}

	// TODO : 
	// fonction pour enregistrer un element dans la BDD
	@RequestMapping(value = "/ville", method = RequestMethod.POST)
	@ResponseBody
	public String insert() {
		return null;
	}




	@RequestMapping(value = "/zebi",method = RequestMethod.PUT)
	public void put(){

	}

}