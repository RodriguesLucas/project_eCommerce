package br.com.project.eCommerce.service;

import java.util.Map;

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

	public PurchaseDTO purchase(Map<String, String> body) {
		SalesDTO salesDTO = new SalesDTO();
		salesDTO.setProductName(body.get("productName"));
		salesDTO.setUserName(body.get("userName"));
		
		SalesEntity salesEntity = new SalesEntity();
		ProductEntity findAByName = productRepository.findAByName(salesDTO.getProductName());
		if (findAByName.getStock() <= 0) {
			return new PurchaseDTO(false);
		}
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

	public PurchaseDTO sum() {
		PurchaseDTO dto = new PurchaseDTO();
		dto.setSucess(true);
		dto.setSum(salesRepository.sum());
		return dto;
	}

}
