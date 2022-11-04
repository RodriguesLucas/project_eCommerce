package br.com.project.eCommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.project.eCommerce.entities.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long>{

}
