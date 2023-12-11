package com.billing.service;

import java.util.List;

import com.billing.model.ServiceMaster;

public interface ServiceMasterService {

	public ServiceMaster saveServiceMaster(ServiceMaster serviceMaster);

	public ServiceMaster updateServiceMaster(ServiceMaster serviceMaster);

	public List<ServiceMaster> findAllServiceMasters();

	public ServiceMaster deleteServiceMasterById(String _id);
	
	
}
