package br.com.project.eCommerce.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.project.eCommerce.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

	Optional<UserEntity> findByName(String name);

	Optional<UserEntity> findByNameAndPassword(String name, String password);

}
