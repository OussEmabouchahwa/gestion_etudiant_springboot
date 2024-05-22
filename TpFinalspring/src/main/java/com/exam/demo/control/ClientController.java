package com.exam.demo.control;

import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.exam.demo.dal.ClientDAL;
import com.exam.demo.dal.CommandeDAL;
import com.exam.demo.dal.PromoteurDAL;
import com.exam.demo.model.Client;
import com.exam.demo.model.Commande;
import com.exam.demo.model.Promoteur;
import com.exam.demo.service.DatabaseService;

import jakarta.transaction.Transactional;
import lombok.Data;


//annotation de class
@Data
@Controller

public class ClientController {
	public static final String ajout = "ajouter";
	public static final String edition = "modifier";	

	@Autowired
	private ClientDAL clientRepository;	
	@Autowired
	private CommandeDAL commandeRepository;
	@Autowired
	private PromoteurDAL promoteurRepository;
	
    @Autowired
    private DatabaseService databaseService;
    
    
	@GetMapping("/")
	public String home(Model model)//données dynamique de la page model mojou fl packages mt3pring
	{		
	    List<Map<String, Object>> listClient = null;
	    listClient = databaseService.getAllData();
	    model.addAttribute("clients", listClient);
	    return "home";
	}
	
	@GetMapping("/createClient")
	public String createClient(Model model) {
		Promoteur c = new Promoteur();
		model.addAttribute("client", c);
		model.addAttribute("mode", ajout);//
		return "formClient";
	}	
	
	@GetMapping("/updateClient/{id}")
	public String updateClient(@PathVariable("id") final Long id, Model model) {
		if(!databaseService.getifpromo(id)) {
			Promoteur c = new Promoteur();
			c=promoteurRepository.getReferenceById(id);
			model.addAttribute("client", c);
		}
		else {
			Client c = new Client();
			c=clientRepository.getReferenceById(id);
			Promoteur c1 = new Promoteur();
			c1=databaseService.convertClientToPromoteur(c);
			model.addAttribute("client", c1);
		}
		model.addAttribute("mode", edition);
		return "formClient";
		
	}	
	@Transactional
	@GetMapping("/deleteClient/{id}")
	public ModelAndView deleteClient(@PathVariable("id") final Long id) {
		
		commandeRepository.deleteAllByClientId(id);
		clientRepository.deleteById(id);
		return new ModelAndView("redirect:/");		
	}	
	@GetMapping("/commandeClient/{id}")
	public String commandeClient(@PathVariable("id") final Long id, Model model) {
		
		Client c = clientRepository.getReferenceById(id);		
		model.addAttribute("client", c);
		List<Commande> lc =commandeRepository.findAllByClientId(id);
		model.addAttribute("listc", lc);
		return "commande";		
	}	
	
	@PostMapping("/ajouterClient")
	public ModelAndView ajouterClient(Model model, @Validated @ModelAttribute Promoteur client, BindingResult bindingResult) //valeur de retour ModelAndView pet etre changer par string
	{
		if (bindingResult.hasErrors())
		{
			model.addAttribute("client", client);
			model.addAttribute("mode", ajout);
			System.out.println(bindingResult);
	        return new ModelAndView("formClient");
		}

		if(client.getCodeAccord().compareTo("")==0)
		{
			Client toclient=new Client();
			toclient.setNom(client.getNom());
			toclient.setAdresse(client.getAdresse());
			clientRepository.save(toclient);
			return new ModelAndView("redirect:/");	
		}
		else {
			promoteurRepository.save(client);

			return new ModelAndView("redirect:/");
		}
	
	}	
	@Transactional
	@PostMapping("/modifierClient")
	public ModelAndView modifierClient(Model model, @Validated @ModelAttribute Promoteur client, BindingResult bindingResult) {
	    if (bindingResult.hasErrors()) {
	        model.addAttribute("client", client);
	        model.addAttribute("mode", edition);
	        return new ModelAndView("formClient");
	    }
	    if(client.getCodeAccord()==null)		
	    {
			clientRepository.updateClient(client.getNom(), client.getAdresse(), client.getId());	
		}
		else {
			promoteurRepository.updatePromoteur(client.getNom(), client.getAdresse(), client.getCategorie(), client.getCodeAccord(), client.getId());
		}
		
		return new ModelAndView("redirect:/");	
		
	    
	}

}
