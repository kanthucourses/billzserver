package com.billing.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.billing.dto.ServiceMasterFilter;
import com.billing.exception.AppServiceException;
import com.billing.model.ServiceMaster;
import com.billing.repository.ServiceMasterRepository;
import com.billing.service.ServiceMasterService;
import com.billing.util.AppConstants;

@Service
@Transactional
public class ServiceMasterServiceImpl implements ServiceMasterService{

	@Autowired
	private ServiceMasterRepository serviceMasterRepository;

	public ServiceMaster saveServiceMaster(ServiceMaster serviceMaster) throws AppServiceException {
		ServiceMaster serviceMasterObj = null;
		ServiceMaster serviceMasterDbObj = null;
		try {
			if(serviceMaster != null) {
				if(serviceMaster.getServiceID() == null || serviceMaster.getServiceID().isEmpty() 
						|| serviceMaster.getServiceName() == null || serviceMaster.getServiceName().isEmpty() ) {
					throw new AppServiceException(
							AppConstants.INPUT_ERR_CODE,
							"please enter manditory fields");
				}
				serviceMasterDbObj = serviceMasterRepository.findByServiceIDCaseInsensitive(serviceMaster.getServiceID(),serviceMaster.getOrganizationInfo().getOrganizationIDName());
				if(serviceMasterDbObj != null) {
					throw new AppServiceException(
							AppConstants.DUPLICATECODE,
							"serviceID should not be duplicate");
				}
				serviceMasterDbObj = serviceMasterRepository.findByServiceNameCaseInsensitive(serviceMaster.getServiceName(),serviceMaster.getOrganizationInfo().getOrganizationIDName());
				if(serviceMasterDbObj != null) {
					throw new AppServiceException(
							AppConstants.DUPLICATECODE,
							"serviceName should not be duplicate");
				}
				serviceMasterObj = serviceMasterRepository.save(serviceMaster);
			}
		}
		catch (Exception ae) {
			throw new AppServiceException(
					"",
					ae.getMessage(),
					ae);
		}
		return serviceMasterObj;
	}

	public ServiceMaster updateServiceMaster(ServiceMaster serviceMaster) {
		ServiceMaster serviceMasterObj = null;
		serviceMasterObj = serviceMasterRepository.save(serviceMaster);
		return serviceMasterObj;
	}

	public List<ServiceMaster> findAllServiceMasters(ServiceMasterFilter serviceMasterFilter) {
		List<ServiceMaster> serviceMasters = null;
		serviceMasters = serviceMasterRepository.findServiceMasters(serviceMasterFilter);
		return serviceMasters;
	}

	public ServiceMaster deleteServiceMasterById(String _id) {
		ServiceMaster serviceMasterObj = null;
		Optional<ServiceMaster> serviceMaster = serviceMasterRepository.findById(_id);
		if(serviceMaster.isPresent()) {
			serviceMasterRepository.deleteById(_id);
			serviceMasterObj = serviceMaster.get();
		}
		return serviceMasterObj;
	}
}
