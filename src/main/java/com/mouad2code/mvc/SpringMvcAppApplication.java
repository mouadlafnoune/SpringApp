package com.mouad2code.mvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.mouad2code.mvc.dao.ProduitRepository;
import com.mouad2code.mvc.entities.Produit;

@SpringBootApplication
public class SpringMvcAppApplication {

	public static void main(String[] args) {
		ApplicationContext ctx= SpringApplication.run(SpringMvcAppApplication.class, args);
	    ProduitRepository produitRepository=ctx.getBean(ProduitRepository.class);
	    produitRepository.save(new Produit("LX 4352", 670, 90));
	    produitRepository.save(new Produit("Ord 1245", 390, 10));
	    produitRepository.save(new Produit("Imp Epson", 570, 80));
	    produitRepository.save(new Produit("Imp HP", 770, 20));
	    
	    produitRepository.findAll().forEach(p->System.out.println(p.getDesignation()));	
	    
	}

}
