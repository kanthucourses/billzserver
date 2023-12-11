package com.billing.service;

import java.util.List;

import com.billing.model.TaxMaster;

public interface TaxMasterService {
	public TaxMaster saveTaxMaster(TaxMaster taxMaster);

	public TaxMaster updateTaxMaster(TaxMaster taxMaster);

	public List<TaxMaster> findAllTaxMasters();

	public TaxMaster deleteTaxMasterById(String _id);
	
}
