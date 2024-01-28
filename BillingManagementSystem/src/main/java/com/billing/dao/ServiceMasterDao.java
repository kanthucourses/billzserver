package com.billing.dao;

import java.util.List;

import com.billing.dto.ServiceMasterFilter;
import com.billing.model.ServiceMaster;

public interface ServiceMasterDao {

	public List<ServiceMaster> findServiceMasters(ServiceMasterFilter serviceMasterFilter);
}
