package br.com.project.eCommerce.entities;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "sales")
@Data
@AllArgsConstructor
public class SalesEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private BigDecimal sumSales;

	@JoinColumn(name = "user_id", referencedColumnName = "id")
	@ManyToOne
	private UserEntity userId;

	@JoinColumn(name = "product_id", referencedColumnName = "id")
	@ManyToOne
	private ProductEntity productId;

}
