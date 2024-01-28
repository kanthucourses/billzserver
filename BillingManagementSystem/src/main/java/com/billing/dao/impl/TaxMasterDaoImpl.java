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

import com.billing.dao.TaxMasterDao;
import com.billing.dto.TaxMasterFilter;
import com.billing.model.TaxMaster;
import com.billing.service.OrganizationService;
import com.billing.util.RequestConstants;

@Repository
public class TaxMasterDaoImpl implements TaxMasterDao{
	private static Logger logger = LogManager.getLogger(TaxMasterDaoImpl.class);
	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private OrganizationService organizationService;

	public List<TaxMaster> findTaxMasters(TaxMasterFilter taxMasterFilter) {
		logger.debug(">>findTaxMasters..taxMasterFilter" + taxMasterFilter);
		List<TaxMaster> taxMasterList = new ArrayList<TaxMaster>();
		Query searchQuery = new Query();
		try {
			if (taxMasterFilter.getTaxName() != null && !taxMasterFilter.getTaxName().isEmpty()) {
				searchQuery.addCriteria(Criteria.where("taxName").is(taxMasterFilter.getTaxName()));
			}
			if (taxMasterFilter.getOrganizationIDName() != null && !taxMasterFilter.getOrganizationIDName().isEmpty()) {
				searchQuery.addCriteria(Criteria.where("organizationInfo.organizationIDName").is(taxMasterFilter.getOrganizationIDName()));
			}
			taxMasterList = mongoTemplate.find(searchQuery, TaxMaster.class,RequestConstants.Collections.TAX_MASTER);	
		} catch (Exception e) {
			logger.error("Error occured\t" + e.getMessage(), e);
		}
		logger.debug("<<findTaxMasters>taxMasterList"+taxMasterList);
		return taxMasterList;
	}
}

