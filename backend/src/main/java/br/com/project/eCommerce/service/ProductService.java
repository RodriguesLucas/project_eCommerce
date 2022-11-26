package br.com.project.eCommerce.service;

import java.util.List;
import java.util.Map;
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

	public ProductDTO createProduct(Map<String, String> body ) {
		ProductDTO productDTO = new ProductDTO();
		productDTO.setImage(null);
		productDTO.setLaunchYear(body.get("launchYear"));
		productDTO.setName(body.get("name"));
		productDTO.setStock(Long.valueOf(body.get("stock")));
		productDTO.setPrice(Double.valueOf(body.get("price")));
		productDTO.setPlatforms(body.get("platforms"));

		ProductEntity entity = new ProductEntity(productDTO);
		
		productRepository.save(entity);
		
		return new ProductDTO(entity);
	}

}
