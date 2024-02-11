package com.billing.service;

import java.util.List;

import com.billing.exception.AppServiceException;
import com.billing.model.Organization;

public interface OrganizationService {
	public Organization saveOrganization(Organization organization) throws AppServiceException;

	public Organization updateOrganization(Organization organization);

	public List<Organization> findAllOrganizations();

	public Organization findOrganizationById(String _id);
	
	public Organization deleteOrganizationById(String _id);
	
	public List<Organization> getChildAndSubChildOrganizationsByOrganizationId(String organizationId);
	
}
