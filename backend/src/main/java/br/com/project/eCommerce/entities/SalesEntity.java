package br.com.project.eCommerce.entities;

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

	private Double sumSales = 0.0;

	@JoinColumn(name = "user_id", referencedColumnName = "id")
	@ManyToOne
	private UserEntity userId;

	@JoinColumn(name = "product_id", referencedColumnName = "id")
	@ManyToOne
	private ProductEntity productId;

	public SalesEntity() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getSumSales() {
		return sumSales;
	}

	public void setSumSales(Double sumSales) {
		this.sumSales += sumSales;
	}

	public UserEntity getUserId() {
		return userId;
	}

	public void setUserId(UserEntity userId) {
		this.userId = userId;
	}

	public ProductEntity getProductId() {
		return productId;
	}

	public void setProductId(ProductEntity productId) {
		this.productId = productId;
	}

}
