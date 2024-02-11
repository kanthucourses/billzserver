package com.billing.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.billing.exception.AppDaoException;
import com.billing.exception.AppServiceException;
import com.billing.model.Organization;
import com.billing.repository.OrganizationRepository;
import com.billing.service.OrganizationService;
import com.billing.util.AppConstants;

@Service
@Transactional
public class OrganizationServiceImpl implements OrganizationService{
	private static Logger logger = LogManager.getLogger(OrganizationServiceImpl.class);

	//@Autowired
	//private FileStoreService fileStoreService;

	@Autowired
	private OrganizationRepository organizationRepository;
	

	public Organization saveOrganization(Organization organization) throws AppServiceException {
		Organization organizationObj = null;
		Organization organizationDbObj = null;
		try {
			if(organization != null) {
				if(organization.getOrganizationID() == null || organization.getOrganizationID().isEmpty() 
						|| organization.getOrganizationName() == null || organization.getOrganizationName().isEmpty() ) {
					throw new AppServiceException(
							AppConstants.INPUT_ERR_CODE,
							"organizationID and organizationName are maditory");
				}
				organizationDbObj = organizationRepository.findByOrganizationIDCaseInsensitive(organization.getOrganizationID());
				if(organizationDbObj != null) {
					throw new AppServiceException(
							AppConstants.DUPLICATECODE,
							"organizationID should not be duplicate");
				}
				organizationDbObj = organizationRepository.findByOrganizationNameCaseInsensitive(organization.getOrganizationName());
				if(organizationDbObj != null) {
					throw new AppServiceException(
							AppConstants.DUPLICATECODE,
							"organizationName should not be duplicate");
				}
				organizationObj = organizationRepository.save(organization);
			}
		}
		catch (Exception ae) {
			throw new AppServiceException(
					"",
					ae.getMessage(),
					ae);
		}
		return organizationObj;
	}

	public Organization updateOrganization(Organization organization) {
		Organization organizationObj = null;
		organizationObj = organizationRepository.save(organization);
		return organizationObj;
	}

	public List<Organization> findAllOrganizations() {
		List<Organization> organizations = null;
		organizations = organizationRepository.findAll();
		return organizations;
	}
	
	public Organization findOrganizationById(String _id) {
		Organization organization = null;
		Optional<Organization> organizationOptional = organizationRepository.findById(_id);
		if(organizationOptional.isPresent()) {
			organization = organizationOptional.get();
		}
		return organization;
	}

	public Organization deleteOrganizationById(String _id) {
		Organization organizationObj = null;
		Optional<Organization> organization = organizationRepository.findById(_id);
		if(organization.isPresent()) {
			organizationRepository.deleteById(_id);
			organizationObj = organization.get();
		}
		return organizationObj;
	}



	
	@Transactional(readOnly = true)
	public List<Organization> getChildAndSubChildOrganizationsByOrganizationId(String organizationId) {
		logger.info("start of Organization getChildAndSubChildOrganizationsByOrganizationId method >> organizationId <<"
				+ organizationId);
		List<Organization> childOrganizations = new ArrayList<>();
		Organization currentOrganization = organizationRepository.findByOrganizationID(organizationId);
		List<Organization> organisationDBList = organizationRepository.findAll();
		childOrganizations.add(currentOrganization);
		for (Organization organizationObj : organisationDBList) {
			if (organizationObj.getParentOrganizationInfo() != null
					&& organizationObj.getParentOrganizationInfo().getOrganizationID().equals(currentOrganization.getOrganizationID())) {
				if (organizationObj.getOperationalUnit().equals("Yes")) {
					childOrganizations.add(organizationObj);
				}
				getSubChildOrganizationsByOrganizationId(childOrganizations, organizationObj.getOrganizationID());
			}
		}
		logger.info(
				"end of Organization getChildAndSubChildOrganizationsByOrganizationId method >> childOrganizations <<"
						+ childOrganizations);
		return childOrganizations;
	}

	public List<Organization> getSubChildOrganizationsByOrganizationId(List<Organization> childOrganizations,
			String organizationId) {
		logger.debug("start of Organization getSubChildOrganizationsByOrganizationId method >> childOrganizations <<"
				+ childOrganizations);
		Organization organization = organizationRepository.findByOrganizationID(organizationId);
		List<Organization> organizationList = organizationRepository.findByParentOrganizationInfoOrganizationID(organizationId);
		for (Organization organizationObj : organizationList) {
			if (organizationObj.getParentOrganizationInfo() != null
					&& organizationObj.getParentOrganizationInfo().getOrganizationID().equals(organization.getOrganizationID())) {
				if (organizationObj.getOperationalUnit().equals("Yes")) {
					childOrganizations.add(organizationObj);
				}
				getSubChildOrganizationsByOrganizationId(childOrganizations, organizationObj.getOrganizationID());
			}
		}
		logger.debug("end of Organization getSubChildOrganizationsByOrganizationId method >> childOrganizations <<"
				+ childOrganizations);
		return childOrganizations;
	}


}
