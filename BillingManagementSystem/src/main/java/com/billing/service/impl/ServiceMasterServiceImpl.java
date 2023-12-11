package com.billing.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.billing.model.ServiceMaster;
import com.billing.repository.ServiceMasterRepository;
import com.billing.service.ServiceMasterService;

@Service
@Transactional
public class ServiceMasterServiceImpl implements ServiceMasterService{

	@Autowired
	private ServiceMasterRepository serviceMasterRepository;

	public ServiceMaster saveServiceMaster(ServiceMaster serviceMaster) {
		ServiceMaster serviceMasterObj = null;
		serviceMasterObj = serviceMasterRepository.save(serviceMaster);
		return serviceMasterObj;
	}

	public ServiceMaster updateServiceMaster(ServiceMaster serviceMaster) {
		ServiceMaster serviceMasterObj = null;
		serviceMasterObj = serviceMasterRepository.save(serviceMaster);
		return serviceMasterObj;
	}

	public List<ServiceMaster> findAllServiceMasters() {
		List<ServiceMaster> serviceMasters = null;
		serviceMasters = serviceMasterRepository.findAll();
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
