package com.billing.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.billing.model.ProductMaster;

public interface ProductMasterRepository extends MongoRepository<ProductMaster, String> {

}
