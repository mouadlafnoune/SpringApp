package com.mouad2code.mvc.web;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import com.mouad2code.mvc.dao.ProduitRepository;
import com.mouad2code.mvc.entities.Produit;

@Controller
public class ProduitController {
	
	
	@Autowired
	private ProduitRepository produitRepository;
	
	@RequestMapping(value="/user/index")
	public String index(Model model, 
			@RequestParam(name="page", defaultValue = "0")int p,
			@RequestParam(name="size", defaultValue = "5")int s,
			@RequestParam(name="motCle", defaultValue = "")String mc) {
		PageRequest pageable = PageRequest.of(p, s);
		Page<Produit> pageProduits = produitRepository.chercher("%"+mc+"%", pageable);
		model.addAttribute("listProduits",pageProduits.getContent());
		
		int[] pages = new int[pageProduits.getTotalPages()];
		model.addAttribute("pages", pages);
		model.addAttribute("size", s);
        model.addAttribute("pageCourante",p);
        model.addAttribute("motCle", mc);
		return "produits";
	}
	
	@RequestMapping(value = "/admin/delete", method = RequestMethod.GET)
	public String delete(Long id, String motCle, int page, int size) {
		produitRepository.deleteById(id);
		return "redirect:/user/index?page="+page+"&size="+size+"&motCle="+motCle;
	}
	
	@RequestMapping(value = "/admin/form", method = RequestMethod.GET)
	public String formProduit(Model model) {
		model.addAttribute("produit", new Produit());
		return "FormProduit";
	}
	
	@RequestMapping(value = "/admin/edit", method = RequestMethod.GET)
	public String edit(Model model,Long id) {
		Produit p = produitRepository.getOne(id);
		model.addAttribute("produit", p);
		return "EditProduit";
	}
	
	
	@RequestMapping(value = "/admin/save", method = RequestMethod.POST)
	public String save(Model model, @Valid Produit produit, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors())
			return "FormProduit";
		
		produitRepository.save(produit);
		return "Confirmation";
	}
	
	@RequestMapping(value = "/")
	public String home(){
		return "redirect:/user/index";
	}
	
	@RequestMapping(value = "/403")
	public String accessDneied(){
		return "403";
	}
	
	@RequestMapping(value = "/login")
	public String login(){
		return "login";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
