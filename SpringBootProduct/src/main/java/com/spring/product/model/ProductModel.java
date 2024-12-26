package com.spring.product.model;


import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

 @Data
@NoArgsConstructor
@AllArgsConstructor

public class ProductModel 
{
	 @NotBlank(message ="product name cannot be blank")
	 private String name;
	 @NotBlank(message ="brand cannot be black")
	private String brand;
	 @NotBlank(message = "madein field cannot be black")
	private String madein;
	 @Positive(message ="price must be greater than zero")
	private double price;
	 @DecimalMax(value ="100.00", message="discount rate cannot exceed 100")
	private double discountrate;
	@Min(value = 1, message="quantity must be at least 1")
	private int quantity;
}
