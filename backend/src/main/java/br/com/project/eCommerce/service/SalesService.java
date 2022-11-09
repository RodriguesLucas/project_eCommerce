package br.com.project.eCommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.project.eCommerce.dtos.PurchaseDTO;
import br.com.project.eCommerce.dtos.SalesDTO;
import br.com.project.eCommerce.entities.ProductEntity;
import br.com.project.eCommerce.entities.SalesEntity;
import br.com.project.eCommerce.repositories.ProductRepository;
import br.com.project.eCommerce.repositories.SalesRepository;
import br.com.project.eCommerce.repositories.UserRepository;

@Service
public class SalesService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SalesRepository salesRepository;

	public PurchaseDTO purchase(SalesDTO salesDTO) {
		
		SalesEntity salesEntity = new SalesEntity();
		ProductEntity findAByName = productRepository.findAByName(salesDTO.getProductName());
		salesEntity.setProductId(findAByName);
		salesEntity.setUserId(userRepository.findByName(salesDTO.getUserName()).get());
		salesEntity.setSumSales(findAByName.getPrice());

		adjustingStock(findAByName);

		salesRepository.save(salesEntity);
		return new PurchaseDTO(true);
	}

	private void adjustingStock(ProductEntity findAByName) {
		findAByName.setStock(findAByName.getStock() - 1);
		productRepository.save(findAByName);
	}

}
