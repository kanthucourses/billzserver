package com.billing.service;

import java.util.List;

import com.billing.dto.TaxMasterFilter;
import com.billing.exception.AppServiceException;
import com.billing.model.TaxMaster;

public interface TaxMasterService {
	public TaxMaster saveTaxMaster(TaxMaster taxMaster) throws AppServiceException;

	public TaxMaster updateTaxMaster(TaxMaster taxMaster);

	public List<TaxMaster> findAllTaxMasters(TaxMasterFilter taxMasterFilter) throws AppServiceException;

	public TaxMaster deleteTaxMasterById(String _id);
	
}
