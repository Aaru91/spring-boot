package com.spring.product.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;

import com.spring.product.Repository.Repository;
import com.spring.product.entity.ProductEntity;
import com.spring.product.model.ProductModel;



@org.springframework.stereotype.Service
public class Service {
      
	@Autowired
      Repository repository;
	
      
     
      public void saveproductdetails(ProductModel productmodel)
      {
      	double stockValue;
      	stockValue=productmodel.getPrice()*productmodel.getQuantity();
      	
      	double discountPrice;
      	discountPrice=productmodel.getPrice()*productmodel.getDiscountrate()/100;
      	
      	double offerPrice;
      	offerPrice=productmodel.getPrice()-discountPrice;
      	
      	double taxRate=30;
      	
      	double finalPrice = offerPrice+ ((taxRate/100) * offerPrice);
      	
      	ProductEntity productentity = new ProductEntity();
      	
      	productentity.setName(productmodel.getName());
      	productentity.setBrand(productmodel.getBrand());
      	productentity.setMadein(productmodel.getMadein());
      	productentity.setPrice(productmodel.getPrice());
      	productentity.setQuantity(productmodel.getQuantity());
      	productentity.setDiscountrate(productmodel.getDiscountrate());
      	
      	
      	productentity.setOfferprice(offerPrice);
      	productentity.setStockvalue(stockValue);
      	productentity.setTaxprice(taxRate);
      	productentity.setFinalprice(finalPrice);
      	
      	repository.save(productentity);
      }
      
      
      public List <ProductEntity> getallproducts()
      {
    	  List <ProductEntity> product = repository.findAll();
    	  return product;
      }


	public ProductEntity searchbyid(long id) 
	{
		
		  Optional<ProductEntity> optionaldata =repository.findById(id);
		  
		  if(optionaldata.isPresent())
		  {
			  ProductEntity product = optionaldata.get();
			  return product;
		  }
		  else
		  {
		    return null;
		  }
	}
	
	public void deletebyid (long id)
	{
		repository.deleteById(id);
	}
	
	
	
	public ProductModel editbyid(long id) {
           Optional<ProductEntity> optional = repository.findById(id);
		
		   if(optional.isPresent())
		  {
			ProductEntity productentity = optional.get();
			ProductModel productModel = new ProductModel();
			productModel.setName(productentity.getName());
			productModel.setBrand(productentity.getBrand());
			productModel.setMadein(productentity.getMadein());
			productModel.setPrice(productentity.getPrice());
			productModel.setQuantity(productentity.getQuantity());
			productModel.setDiscountrate(productentity.getDiscountrate());
			  
			return productModel;
	      }
		   else
		   {
			   return null;
		   }
	}
	



	public void updateproduct (long id, ProductModel productModel)
	{
		Optional<ProductEntity> optional = repository.findById(id);
		
		if(optional.isPresent())
		{
			ProductEntity productentity = optional.get();
			productentity.setName(productModel.getName());
			productentity.setBrand(productModel.getBrand());
			productentity.setMadein(productModel.getMadein());
			productentity.setPrice(productModel.getPrice());
			productentity.setQuantity(productModel.getQuantity());
			productentity.setDiscountrate(productModel.getDiscountrate());
			
			
			double discountprice = productModel.getDiscountrate()*productModel.getPrice()/100;
			
			productentity.setDiscountprice(discountprice);
			
			double offerprice;
			offerprice=productModel.getPrice()-discountprice;
			
			
			double taxprice;
			taxprice=0.18*offerprice;
			
			double stockvalue;
			stockvalue=productModel.getPrice()*productModel.getQuantity();
			
			double finalprice;
			finalprice=offerprice + taxprice;
			
			productentity.setOfferprice(offerprice);
			
			productentity.setTaxprice(taxprice);
			
			productentity.setStockvalue(stockvalue);
			
			productentity.setFinalprice(finalprice);
			
			repository.save(productentity);
			
		}
	}

}
