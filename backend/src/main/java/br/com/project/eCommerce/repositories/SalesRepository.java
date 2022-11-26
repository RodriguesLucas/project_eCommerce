package br.com.project.eCommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.project.eCommerce.entities.SalesEntity;

public interface SalesRepository extends JpaRepository<SalesEntity, Long> {
	
	@Query(value = "select sum(sales.sum_sales) from sales", nativeQuery = true)
	Double sum();

}
