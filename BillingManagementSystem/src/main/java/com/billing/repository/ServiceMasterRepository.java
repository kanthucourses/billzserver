package com.billing.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.billing.dao.ServiceMasterDao;
import com.billing.model.ServiceMaster;

public interface ServiceMasterRepository extends MongoRepository<ServiceMaster, String>,ServiceMasterDao {

	public ServiceMaster findByServiceID(String serviceID);

	@Query("{'serviceID': { $regex: '^?0$', $options: 'i' },'organizationInfo.organizationIDName' : { $regex: '^?1$', $options: 'i' }}")
	public ServiceMaster findByServiceIDCaseInsensitive(String serviceID,String organizationIDName);

    @Query("{'serviceName': { $regex: '^?0$', $options: 'i' },'organizationInfo.organizationIDName' : { $regex: '^?1$', $options: 'i' }}")
    public ServiceMaster findByServiceNameCaseInsensitive(String serviceName,String organizationIDName);
    
}
