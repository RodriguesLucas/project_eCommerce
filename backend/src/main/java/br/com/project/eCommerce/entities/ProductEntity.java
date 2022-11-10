package br.com.project.eCommerce.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.project.eCommerce.dtos.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "product")
@Data
@AllArgsConstructor
public class ProductEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private Long stock;

	private String launchYear;

	private String platforms;

	private Double price;

	@Column(name = "image_url")
	private String image;

	public ProductEntity() {

	}

	public ProductEntity(ProductDTO productDTO) {
		this.image = productDTO.getImage();
		this.launchYear = productDTO.getLaunchYear();
		this.name = productDTO.getName();
		this.price = productDTO.getPrice();
		this.stock = productDTO.getStock();
		this.platforms = productDTO.getPlatforms();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getStock() {
		return stock;
	}

	public void setStock(Long stock) {
		this.stock = stock;
	}

	public String getLaunchYear() {
		return launchYear;
	}

	public void setLaunchYear(String releaseDate) {
		this.launchYear = releaseDate;
	}

	public String getPlatforms() {
		return platforms;
	}

	public void setPlatforms(String platforms) {
		this.platforms = platforms;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
