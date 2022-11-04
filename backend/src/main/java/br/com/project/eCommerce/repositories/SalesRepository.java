package br.com.project.eCommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.project.eCommerce.entities.SalesEntity;

public interface SalesRepository extends JpaRepository<SalesEntity, Long> {

}
