package com.billing.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.billing.dao.TaxMasterDao;
import com.billing.model.TaxMaster;

public interface TaxMasterRepository extends MongoRepository<TaxMaster, String>,TaxMasterDao {

	//@Query("{'taxName': { $regex: ?0, $options: 'i' }}")
    @Query("{'taxName' : { $regex: '^?0$', $options: 'i' }, 'taxPercentage' : { $regex: '^?1$', $options: 'i' },"
    		+ "'state' : { $regex: '^?2$', $options: 'i' },'country' : { $regex: '^?3$', $options: 'i' },'organizationInfo.organizationIDName' : { $regex: '^?4$', $options: 'i' }}")
	public TaxMaster findTaxMasterCaseInsensitive(String taxName, Double taxPercentage,String state,String country,String organizationIDName);

    
}
