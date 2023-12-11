package com.billing.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.billing.model.TaxMaster;
import com.billing.repository.TaxMasterRepository;
import com.billing.service.TaxMasterService;

@Service
@Transactional
public class TaxMasterServiceImpl implements TaxMasterService{

	@Autowired
	private TaxMasterRepository taxMasterRepository;

	public TaxMaster saveTaxMaster(TaxMaster taxMaster) {
		TaxMaster taxMasterObj = null;
		taxMasterObj = taxMasterRepository.save(taxMaster);
		return taxMasterObj;
	}

	public TaxMaster updateTaxMaster(TaxMaster taxMaster) {
		TaxMaster taxMasterObj = null;
		taxMasterObj = taxMasterRepository.save(taxMaster);
		return taxMasterObj;
	}

	public List<TaxMaster> findAllTaxMasters() {
		List<TaxMaster> taxMasters = null;
		taxMasters = taxMasterRepository.findAll();
		return taxMasters;
	}

	public TaxMaster deleteTaxMasterById(String _id) {
		TaxMaster taxMasterObj = null;
		Optional<TaxMaster> taxMaster = taxMasterRepository.findById(_id);
		if(taxMaster.isPresent()) {
			taxMasterRepository.deleteById(_id);
			taxMasterObj = taxMaster.get();
		}
		return taxMasterObj;
	}


}
