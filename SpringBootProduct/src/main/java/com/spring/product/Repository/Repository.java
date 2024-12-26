package com.spring.product.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.product.entity.ProductEntity;
 
@org.springframework.stereotype.Repository
public interface Repository extends JpaRepository<ProductEntity,Long> {

	

}
