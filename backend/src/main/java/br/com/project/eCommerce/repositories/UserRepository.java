package br.com.project.eCommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.project.eCommerce.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

}
