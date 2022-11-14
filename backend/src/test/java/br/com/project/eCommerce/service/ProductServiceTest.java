package br.com.project.eCommerce.service;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import br.com.project.eCommerce.dtos.ProductDTO;
import br.com.project.eCommerce.entities.ProductEntity;
import br.com.project.eCommerce.repositories.ProductRepository;

@SpringBootTest(classes = {ProductService.class})
public class ProductServiceTest {
	
	@Autowired
	private ProductService productService;
	
	@MockBean
	private ProductRepository productRepository;
	
	@Test
	void tetst () {
		when(productRepository.findAByName("nome")).thenReturn(createReturn());
		ProductDTO findByName = productService.findByName("nome");
		System.out.println(findByName.getPrice());
	}

	private ProductEntity createReturn() {
		ProductEntity entity = new ProductEntity();
		entity.setPrice(22.2);
		return entity;
	}

}
