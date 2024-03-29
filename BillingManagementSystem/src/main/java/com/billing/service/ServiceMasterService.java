package com.billing.service;

import java.util.List;

import com.billing.dto.ServiceMasterFilter;
import com.billing.exception.AppServiceException;
import com.billing.model.ServiceMaster;

public interface ServiceMasterService {

	public ServiceMaster saveServiceMaster(ServiceMaster serviceMaster) throws AppServiceException;

	public ServiceMaster updateServiceMaster(ServiceMaster serviceMaster);

	public List<ServiceMaster> findAllServiceMasters(ServiceMasterFilter serviceMasterFilter);

	public ServiceMaster deleteServiceMasterById(String _id);
	
	
}
