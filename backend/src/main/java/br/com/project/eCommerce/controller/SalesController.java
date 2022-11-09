package br.com.project.eCommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.project.eCommerce.dtos.PurchaseDTO;
import br.com.project.eCommerce.dtos.SalesDTO;
import br.com.project.eCommerce.service.SalesService;

@RestController
@RequestMapping("/sales")
public class SalesController {
	
	@Autowired
	private SalesService salesService;
	
	@PostMapping("/purchase")
	public PurchaseDTO purchase(@RequestBody SalesDTO salesDTO) {
		return salesService.purchase(salesDTO);
	}

}
