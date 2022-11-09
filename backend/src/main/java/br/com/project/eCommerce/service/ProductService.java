package br.com.project.eCommerce.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.project.eCommerce.dtos.ProductDTO;
import br.com.project.eCommerce.entities.ProductEntity;
import br.com.project.eCommerce.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public List<ProductDTO> findAll() {
		List<ProductEntity> findAll = productRepository.findAll();
		return findAll.stream().map(v -> new ProductDTO(v)).collect(Collectors.toList());
	}

	public ProductDTO findByName(String name) {
		ProductEntity findByName = productRepository.findAByName(name);
		return new ProductDTO(findByName);
	}

}
