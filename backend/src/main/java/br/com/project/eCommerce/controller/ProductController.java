package br.com.project.eCommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.project.eCommerce.dtos.ProductDTO;
import br.com.project.eCommerce.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/findAll")
	public List<ProductDTO> findAll(){
		return productService.findAll();
	}
	
	@GetMapping("/findByName/{name}")
	public ProductDTO findByName(@PathVariable String name){
		return productService.findByName(name);
	}
}
