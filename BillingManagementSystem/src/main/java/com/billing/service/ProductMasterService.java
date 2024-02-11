package com.billing.service;

import java.util.List;

import com.billing.model.ProductMaster;

public interface ProductMasterService {

	public ProductMaster saveProductMaster(ProductMaster productMaster);

	public ProductMaster updateProductMaster(ProductMaster productMaster);

	public List<ProductMaster> findAllProductMasters();

	public ProductMaster deleteProductMasterById(String _id);
}
