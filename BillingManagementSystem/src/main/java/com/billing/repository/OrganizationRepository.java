package com.billing.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.billing.model.Organization;

public interface OrganizationRepository extends MongoRepository<Organization, String> {

	public Organization findByOrganizationID(String organizationID);
	public List<Organization> findByParentOrganizationInfoOrganizationID(String organizationID);
	
	@Query("{'organizationID': { $regex: '^?0$', $options: 'i' }}")
	public Organization findByOrganizationIDCaseInsensitive(String organizationID);

    @Query("{'organizationName': { $regex: '^?0$', $options: 'i' }}")
    public Organization findByOrganizationNameCaseInsensitive(String organizationName);

}
