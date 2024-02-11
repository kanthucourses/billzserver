package com.billing.service.impl;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.billing.model.ProductMaster;
import com.billing.repository.ProductMasterRepository;
import com.billing.service.ProductMasterService;

@Service
@Transactional
public class ProductMasterServiceImpl implements ProductMasterService{

	@Autowired
	private ProductMasterRepository productMasterRepository;

	public ProductMaster saveProductMaster(ProductMaster productMaster) {
		ProductMaster productMasterObj = null;
		MathContext mathContext = new MathContext(5, RoundingMode.HALF_UP);

		BigDecimal locationVolume =  productMaster.getLocationVolume();
		BigDecimal noOfProducts =  productMaster.getNoOfProducts();
		BigDecimal volume = locationVolume.divide(noOfProducts,mathContext);
		productMaster.setVolume(volume);
		//d
		BigDecimal dlocationVolume =  productMaster.getDlocationVolume();
		BigDecimal dnoOfProducts =  productMaster.getDnoOfProducts();
		BigDecimal dvolume = dlocationVolume.divide(dnoOfProducts,mathContext);
		productMaster.setDvolume(dvolume);
		productMasterObj = productMasterRepository.save(productMaster);
		return productMasterObj;
	}

	public ProductMaster updateProductMaster(ProductMaster productMaster) {
		ProductMaster productMasterObj = null;
		productMasterObj = productMasterRepository.save(productMaster);
		return productMasterObj;
	}

	public List<ProductMaster> findAllProductMasters() {
		List<ProductMaster> productMasters = null;
		MathContext mathContext = new MathContext(2, RoundingMode.HALF_UP);
		productMasters = productMasterRepository.findAll();
		if(productMasters != null && productMasters.size()>0) {
			for(ProductMaster productMaster : productMasters) {
				BigDecimal locVolume = productMaster.getVolume().multiply(productMaster.getNoOfProducts(),mathContext);
				//.setScale(2, RoundingMode.HALF_UP);
				System.out.println("locVolume>"+locVolume);
				BigDecimal dlocVolume = productMaster.getDvolume().multiply(productMaster.getDnoOfProducts(),mathContext)
						.setScale(2, RoundingMode.HALF_UP);
				System.out.println("dlocVolume>"+dlocVolume);
				BigDecimal noOfProds = productMaster.getLocationVolume().divide(productMaster.getVolume(),mathContext);
				//.setScale(2, RoundingMode.HALF_UP);
				System.out.println("noOfProds>"+noOfProds);
				BigDecimal dnoOfProds = productMaster.getDlocationVolume().divide(productMaster.getDvolume(),mathContext)
						.setScale(2, RoundingMode.HALF_UP);
				System.out.println("dnoOfProds>"+dnoOfProds);
			}
		}
		return productMasters;
	}

	public ProductMaster deleteProductMasterById(String _id) {
		ProductMaster productMasterObj = null;
		MathContext mathContext = new MathContext(5, RoundingMode.HALF_UP);
		Optional<ProductMaster> productMaster = productMasterRepository.findById(_id);
		if(productMaster.isPresent()) {
			productMasterRepository.deleteById(_id);
			productMasterObj = productMaster.get();
		}
		return productMasterObj;
	}

}
