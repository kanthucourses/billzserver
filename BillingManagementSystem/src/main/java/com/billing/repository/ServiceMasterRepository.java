package com.billing.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.billing.dao.ServiceMasterDao;
import com.billing.model.ServiceMaster;

public interface ServiceMasterRepository extends MongoRepository<ServiceMaster, String>,ServiceMasterDao {

	public ServiceMaster findByServiceID(String serviceID);
	
}
