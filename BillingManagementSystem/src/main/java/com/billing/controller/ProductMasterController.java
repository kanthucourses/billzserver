package com.billing.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.billing.model.ProductMaster;
import com.billing.service.ProductMasterService;
import com.billing.util.AppConstants;
import com.billing.util.ServiceControllerUtils;
import com.billing.util.ServiceResponse;

@CrossOrigin(origins = "*",allowedHeaders = "*")
@RestController
@RequestMapping("/product/services")
public class ProductMasterController {
	private static Logger logger = LogManager.getLogger(ProductMasterController.class);

	@Autowired
	private ServiceControllerUtils scutils;

	@Autowired
	private ProductMasterService productMasterService;

	@PostMapping("/saveProductMaster")
	ResponseEntity<?> saveProductMaster(@RequestBody ProductMaster productMaster) {
		logger.debug(">>saveProductMaster");
		ResponseEntity<?> resp = null;
		ServiceResponse restResponse = new ServiceResponse();
		try {
			ProductMaster productMasterObj = productMasterService.saveProductMaster(productMaster);
			if (productMasterObj != null) {
				restResponse.addDataObject("productMaster", productMasterObj);
				restResponse = scutils.prepareMobileResponseSuccessStatus(restResponse, "productMaster is saved");
				resp = new ResponseEntity<ServiceResponse>(restResponse, HttpStatus.OK);
			} else {
				restResponse = scutils.prepareMobileResponseInvalidData(restResponse, "productMaster is not saved");
				resp = new ResponseEntity<ServiceResponse>(restResponse, HttpStatus.OK);
			}
		} catch (Exception e) {
			restResponse = scutils.prepareMobileResponseErrorStatus(restResponse, AppConstants.ERRORCODE,
					e.getMessage());
			resp = new ResponseEntity<ServiceResponse>(restResponse, HttpStatus.INTERNAL_SERVER_ERROR);
			logger.debug(">>Exceptions are: " + restResponse);
			e.printStackTrace();
		}
		return resp;
	}

	@PutMapping("/updateProductMaster")
	ResponseEntity<?> updateProductMaster(@RequestBody ProductMaster productMaster) {
		logger.debug(">>updateProductMaster");
		ResponseEntity<?> resp = null;
		ServiceResponse restResponse = new ServiceResponse();
		try {
			ProductMaster productMasterObj = productMasterService.updateProductMaster(productMaster);
			if (productMasterObj != null) {
				restResponse.addDataObject("productMaster", productMasterObj);
				restResponse = scutils.prepareMobileResponseSuccessStatus(restResponse, "productMaster is updated");
				resp = new ResponseEntity<ServiceResponse>(restResponse, HttpStatus.OK);
			} else {
				restResponse = scutils.prepareMobileResponseInvalidData(restResponse, "productMaster is not updated");
				resp = new ResponseEntity<ServiceResponse>(restResponse, HttpStatus.OK);
			}
		} catch (Exception e) {
			restResponse = scutils.prepareMobileResponseErrorStatus(restResponse, AppConstants.ERRORCODE,
					e.getMessage());
			resp = new ResponseEntity<ServiceResponse>(restResponse, HttpStatus.INTERNAL_SERVER_ERROR);
			logger.debug(">>Exceptions are: " + restResponse);
			e.printStackTrace();
		}
		return resp;
	}

	@PostMapping("/findAllProductMasters")
	ResponseEntity<?> findAllProductMasters() {
		logger.debug(">>findAllProductMasters");
		ResponseEntity<?> resp = null;
		ServiceResponse restResponse = new ServiceResponse();
		try {
			List<ProductMaster> productMasters = productMasterService.findAllProductMasters();
			if (productMasters != null) {
				restResponse.addDataObject("productMasters", productMasters);
				restResponse = scutils.prepareMobileResponseSuccessStatus(restResponse, "fetched productMaster details successfully");
				resp = new ResponseEntity<ServiceResponse>(restResponse, HttpStatus.OK);
			} else {
				restResponse = scutils.prepareMobileResponseInvalidData(restResponse, "productMaster details not found");
				resp = new ResponseEntity<ServiceResponse>(restResponse, HttpStatus.OK);
			}
		} catch (Exception e) {
			restResponse = scutils.prepareMobileResponseErrorStatus(restResponse, AppConstants.ERRORCODE,
					e.getMessage());
			resp = new ResponseEntity<ServiceResponse>(restResponse, HttpStatus.INTERNAL_SERVER_ERROR);
			logger.debug(">>Exceptions are: " + restResponse);
			e.printStackTrace();
		}
		return resp;
	}

	@DeleteMapping("/deleteProductMasterById")
	ResponseEntity<?> deleteProductMasterById(@RequestParam(required =true, value = "_id") String _id) {
		logger.debug(">>deleteProductMasterById");
		ResponseEntity<?> resp = null;
		ServiceResponse restResponse = new ServiceResponse();
		try {
			ProductMaster productMaster = productMasterService.deleteProductMasterById(_id);
			if (productMaster != null) {
				restResponse.addDataObject("productMaster", productMaster);
				restResponse = scutils.prepareMobileResponseSuccessStatus(restResponse, "deleted productMaster successfully");
				resp = new ResponseEntity<ServiceResponse>(restResponse, HttpStatus.OK);
			} else {
				restResponse = scutils.prepareMobileResponseInvalidData(restResponse, "failed to delete productMaster");
				resp = new ResponseEntity<ServiceResponse>(restResponse, HttpStatus.OK);
			}
		} catch (Exception e) {
			restResponse = scutils.prepareMobileResponseErrorStatus(restResponse, AppConstants.ERRORCODE,
					e.getMessage());
			resp = new ResponseEntity<ServiceResponse>(restResponse, HttpStatus.INTERNAL_SERVER_ERROR);
			logger.debug(">>Exceptions are: " + restResponse);
			e.printStackTrace();
		}
		return resp;
	}

	@PostMapping("/findProductMasters")
	ResponseEntity<?> findProductMasters() {
		logger.debug(">>findProductMasters");
		ResponseEntity<?> resp = null;
		ServiceResponse restResponse = new ServiceResponse();
		try {
			List<ProductMaster> productMasters = productMasterService.findAllProductMasters();
			if (productMasters != null) {
				restResponse.addDataObject("productMasters", productMasters);
				restResponse = scutils.prepareMobileResponseSuccessStatus(restResponse, "fetched productMaster details successfully");
				resp = new ResponseEntity<ServiceResponse>(restResponse, HttpStatus.OK);
			} else {
				restResponse = scutils.prepareMobileResponseInvalidData(restResponse, "productMaster details not found");
				resp = new ResponseEntity<ServiceResponse>(restResponse, HttpStatus.OK);
			}
		} catch (Exception e) {
			restResponse = scutils.prepareMobileResponseErrorStatus(restResponse, AppConstants.ERRORCODE,
					e.getMessage());
			resp = new ResponseEntity<ServiceResponse>(restResponse, HttpStatus.INTERNAL_SERVER_ERROR);
			logger.debug(">>Exceptions are: " + restResponse);
			e.printStackTrace();
		}
		return resp;
	}



}
