package com.controller;

import com.dao.DaoException;
import com.dao.DaoFactory;
import com.dao.VilleDao;
import com.form.FormException;
import com.form.VilleEditer;
import com.form.VilleSaver;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
public class VilleController {
	private final DaoFactory daoFactory = DaoFactory.getInstance();


	// fonction pour récupérer le contenu de la BDD
	@GetMapping(value="/ville")
	public List<String> get(@RequestParam(required  = false, value="codePostal") String codePostal) throws DaoException, SQLException {
		System.out.println("get");
		VilleDao villeDao = daoFactory.getVilleDao();

		return villeDao.getVille(codePostal);
	}

	@GetMapping(value = "/villePosition")
	public String[] getLocation(@RequestParam(value = "nomVille") String nomVille) throws DaoException, SQLException {
		nomVille = nomVille.replace("_"," ");
		VilleDao villeDao = daoFactory.getVilleDao();
		return villeDao.getLocation(nomVille);
	}

	@PostMapping(value = "/villepost")
	@ResponseBody
	public void post(@RequestBody String request) throws DaoException, FormException, SQLException {
		request = request.replace("+"," ");
		String[] parts = request.split("&");
		for (String part : parts){
			System.out.println(part);
		}
		VilleSaver saver = new VilleSaver(request);
		saver.saveVille();
	}

	@PutMapping(value = "/villeput")
	@ResponseBody
	public void put(@RequestBody(required = false) String request) throws DaoException, SQLException {
		System.out.println("put");
		request = request.replace("+"," ");
		String[] parts = request.split("&");
		for (String part : parts){
			System.out.println(part);
		}
		VilleEditer editer = new VilleEditer(request);
		editer.changeVille();
	}

	@DeleteMapping(value = "/villedelete/{codeCommune}")
	public void delete(@PathVariable String codeCommune) throws SQLException, DaoException {
		System.out.println("delete");
		VilleDao villeDao = DaoFactory.getInstance().getVilleDao();
		villeDao.deleteVille(codeCommune);
	}

}