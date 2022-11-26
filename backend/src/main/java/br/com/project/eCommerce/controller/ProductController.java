package br.com.project.eCommerce.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@PostMapping("/create/product")
	public ProductDTO createProduct(@RequestParam Map<String, String> body ) {
		return productService.createProduct(body);
	}
	
}
