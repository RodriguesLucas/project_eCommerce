package br.com.project.eCommerce.dtos;

import java.math.BigDecimal;

import br.com.project.eCommerce.entities.ProductEntity;

public class ProductDTO {

	private String name;

	private Long stock;

	private String launchYear;

	private String platforms;

	private BigDecimal price;

	private String image;

	public ProductDTO(ProductEntity productEntity) {
		this.name = productEntity.getName();
		this.price = productEntity.getPrice();
		this.stock = productEntity.getStock();
		this.image = productEntity.getImage();
		this.platforms = productEntity.getPlatforms();
		this.launchYear = productEntity.getLaunchYear();
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

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getLaunchYear() {
		return launchYear;
	}

	public void setLaunchYear(String launchYear) {
		this.launchYear = launchYear;
	}

	public String getPlatforms() {
		return platforms;
	}

	public void setPlatforms(String platforms) {
		this.platforms = platforms;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
