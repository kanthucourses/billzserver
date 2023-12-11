package com.billing.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.billing.model.Organization;

public interface OrganizationRepository extends MongoRepository<Organization, String> {

	public Organization findByOrganizationID(String organizationID);
	public List<Organization> findByParentOrganizationInfoOrganizationID(String organizationID);

}
