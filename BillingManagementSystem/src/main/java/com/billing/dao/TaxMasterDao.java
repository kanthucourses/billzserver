package com.billing.dao;

import java.util.List;

import com.billing.dto.TaxMasterFilter;
import com.billing.exception.AppDaoException;
import com.billing.model.TaxMaster;

public interface TaxMasterDao {
	public List<TaxMaster> findTaxMasters(TaxMasterFilter taxMasterFilter) throws AppDaoException;
}
