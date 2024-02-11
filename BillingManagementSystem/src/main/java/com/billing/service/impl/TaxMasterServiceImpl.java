package com.billing.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.billing.dto.TaxMasterFilter;
import com.billing.exception.AppDaoException;
import com.billing.exception.AppServiceException;
import com.billing.model.TaxMaster;
import com.billing.repository.TaxMasterRepository;
import com.billing.service.TaxMasterService;
import com.billing.util.AppConstants;

@Service
@Transactional
public class TaxMasterServiceImpl implements TaxMasterService{
	private static Logger logger = LogManager.getLogger(TaxMasterServiceImpl.class);

	@Autowired
	private TaxMasterRepository taxMasterRepository;

	public TaxMaster saveTaxMaster(TaxMaster taxMaster) throws AppServiceException {
		TaxMaster taxMasterObj = null;
		TaxMaster taxMasterDbObj = null;
		try {
			if(taxMaster != null) {
				if(taxMaster.getTaxName() == null || taxMaster.getTaxName().isEmpty() 
						|| taxMaster.getTaxPercentage() == null ||  taxMaster.getState()== null || taxMaster.getState().isEmpty() 
						||  taxMaster.getCountry()== null || taxMaster.getCountry().isEmpty()) {
					throw new AppServiceException(
							AppConstants.INPUT_ERR_CODE,
							"please enter manditory fields");
				}
				taxMasterDbObj = taxMasterRepository.findTaxMasterCaseInsensitive(taxMaster.getTaxName(), taxMaster.getTaxPercentage(), taxMaster.getState(), taxMaster.getCountry(), taxMaster.getOrganizationInfo().getOrganizationIDName());
				if(taxMasterDbObj != null) {
					throw new AppServiceException(
							AppConstants.DUPLICATECODE,
							"taxMaster should not be duplicate");
				}
				taxMasterObj = taxMasterRepository.save(taxMaster);
			}
		}
		catch (Exception ae) {
			throw new AppServiceException(
					"",
					ae.getMessage(),
					ae);
		}
		return taxMasterObj;
	}

	public TaxMaster updateTaxMaster(TaxMaster taxMaster) {
		TaxMaster taxMasterObj = null;
		taxMasterObj = taxMasterRepository.save(taxMaster);
		return taxMasterObj;
	}

	public List<TaxMaster> findAllTaxMasters(TaxMasterFilter taxMasterFilter) throws AppServiceException {
		List<TaxMaster> taxMasters = null;
		try {
		taxMasters = taxMasterRepository.findTaxMasters(taxMasterFilter);
		}
		catch (AppDaoException e) {
			logger.error("Error occured\t" + e.getMessage(),e);
			throw new AppServiceException(e.getErrorCode(), e.getMessage(), e);
		}
		catch (Exception e) {
			throw new AppServiceException(
					"",
					"",
					e);
		}
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
