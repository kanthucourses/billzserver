package com.billing.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.billing.dao.ServiceMasterDao;
import com.billing.dto.ServiceMasterFilter;
import com.billing.model.ServiceMaster;
import com.billing.service.OrganizationService;
import com.billing.util.RequestConstants;

@Repository
public class ServiceMasterDaoImpl implements ServiceMasterDao{
	private static Logger logger = LogManager.getLogger(ServiceMasterDaoImpl.class);
	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private OrganizationService organizationService;

	public List<ServiceMaster> findServiceMasters(ServiceMasterFilter serviceMasterFilter) {
		logger.debug(">>findServiceMasters..serviceMasterFilter" + serviceMasterFilter);
		List<ServiceMaster> serviceMasterList = new ArrayList<ServiceMaster>();
		Query searchQuery = new Query();
		try {
			if (serviceMasterFilter.getServiceIDName() != null && !serviceMasterFilter.getServiceIDName().isEmpty()) {
				searchQuery.addCriteria(Criteria.where("serviceIDName").is(serviceMasterFilter.getServiceIDName()));
			}
			if (serviceMasterFilter.getServiceID()!= null && !serviceMasterFilter.getServiceID().isEmpty()) {
				searchQuery.addCriteria(Criteria.where("serviceID").is(serviceMasterFilter.getServiceID()));
			}
			if (serviceMasterFilter.getOrganizationIDName() != null && !serviceMasterFilter.getOrganizationIDName().isEmpty()) {
				searchQuery.addCriteria(Criteria.where("organizationInfo.organizationIDName").is(serviceMasterFilter.getOrganizationIDName()));
			}
			serviceMasterList = mongoTemplate.find(searchQuery, ServiceMaster.class,RequestConstants.Collections.SERVICE_MASTER);	
		} catch (Exception e) {
			logger.error("Error occured\t" + e.getMessage(), e);
		}
		logger.debug(">>findServiceMasters>serviceMasterList"+serviceMasterList);
		return serviceMasterList;
	}
}
