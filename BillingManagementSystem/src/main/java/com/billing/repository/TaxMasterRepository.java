package com.billing.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.billing.dao.TaxMasterDao;
import com.billing.model.TaxMaster;

public interface TaxMasterRepository extends MongoRepository<TaxMaster, String>,TaxMasterDao {

}
