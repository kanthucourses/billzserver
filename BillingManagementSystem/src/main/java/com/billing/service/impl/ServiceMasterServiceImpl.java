package com.billing.service.impl;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.billing.dto.ServiceMasterFilter;
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
	    MathContext mathContext = new MathContext(20, RoundingMode.HALF_UP);

		BigDecimal weight = serviceMaster.getWeight();
		BigDecimal quantity = serviceMaster.getQuantity();
		BigDecimal dweight = serviceMaster.getDweight();
		BigDecimal dquantity = serviceMaster.getDquantity();

		BigDecimal wtofoneProduct = weight.divide(quantity,mathContext);
		BigDecimal wtofoneProductD = dweight.divide(dquantity,mathContext);
		System.out.println("wtofoneProduct>"+wtofoneProduct);
		System.out.println("wtofoneProductD>"+wtofoneProductD);
		serviceMaster.setWeightofOneProduct(wtofoneProduct);
		serviceMaster.setDweightofOneProduct(wtofoneProductD);
		serviceMasterObj = serviceMasterRepository.save(serviceMaster);
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
