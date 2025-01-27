package com.spring.product.controller;

import java.util.HashMap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.product.entity.ProductEntity;
import com.spring.product.model.ProductModel;
import com.spring.product.service.Service;

import jakarta.validation.Valid;




@Controller
public class ProductController
{
	
	@Autowired
	Service service;

	
     @GetMapping("/text")
     public String getproductform( Model model)
     {
   	  ProductModel productModel = new ProductModel();
   	  productModel.setMadein("INDIA");
   	  productModel.setQuantity(2);
   	  productModel.setDiscountrate(10.5);
   	  
   	
		model.addAttribute("productModel", productModel);
		return "addproduct";
   	  
     }
     
     
     @PostMapping("/submit")
     public String submit(@Valid ProductModel productmodel, BindingResult bindingresult, Model model)
     {
    	 HashMap<String, String> validationError = new HashMap<String,String>();
		 
    	 if(bindingresult.hasErrors())
    	 {
    		 for(FieldError fielderror : bindingresult.getFieldErrors())
    		 {
    			 validationError.put(fielderror.getField(), fielderror.getDefaultMessage());
    		 }
    		 model.addAttribute("validationError", validationError);
    		 return "addproduct";
    		
    	 }
    	 service.saveproductdetails(productmodel);
		return "redirect:/getallproducts";
     }
     
     
     
     @GetMapping("/getallproducts")
     public String getallproducts(Model model)
     {
     	List<ProductEntity> product = service.getallproducts();
     	model.addAttribute("Products", product);
     	
     	return "list-products";
     }
     
     @GetMapping("/getsearchform")
     public String getsearchform()
     {
    	 return "search";
     }
     
     @PostMapping("/searchbyid")
     public String searchbyid(@RequestParam long id, Model model)
     {
    	 ProductEntity product= service.searchbyid(id);
    	 
    	 model.addAttribute("product",product);
    	 return "search";
     }  
     
      @GetMapping("/delete/{id}")
      public String deleteprodutbyid(@PathVariable long id)
      {
	     service.deletebyid(id);
         return "redirect:/getallproducts";
      }
      
      @GetMapping("/edit/{id}")
  	public String editbyid(@PathVariable long id, Model model)
  	{
		ProductModel productModel = service.editbyid(id);
		model.addAttribute("product", productModel);
		model.addAttribute("id", id);
  		return "edit-product";
  	}
    
      
      @PostMapping("/editsaveproduct/{id}")
      public String updateproduct(@PathVariable  long id, ProductModel productModel)
      {
    	 service.updateproduct(id, productModel);
    	 return "redirect:/getallproducts";
      }
     
      
      
      
      
      
}
