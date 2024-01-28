package com.billing.controller;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
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

import com.billing.dto.ServiceMasterFilter;
import com.billing.model.ServiceMaster;
import com.billing.service.ServiceMasterService;
import com.billing.util.AppConstants;
import com.billing.util.ServiceControllerUtils;
import com.billing.util.ServiceResponse;

@CrossOrigin(origins = "*",allowedHeaders = "*")
@RestController
@RequestMapping("/service/services")
public class ServiceMasterController {

	private static Logger logger = LogManager.getLogger(ServiceMasterController.class);

	@Autowired
	private ServiceControllerUtils scutils;

	@Autowired
	private ServiceMasterService serviceMasterService;

	@PostMapping("/saveServiceMaster")
	ResponseEntity<?> saveServiceMaster(@RequestBody ServiceMaster serviceMaster) {
		logger.debug(">>saveServiceMaster");
		ResponseEntity<?> resp = null;
		ServiceResponse restResponse = new ServiceResponse();
		try {
			ServiceMaster serviceMasterObj = serviceMasterService.saveServiceMaster(serviceMaster);
			if (serviceMasterObj != null) {
				restResponse.addDataObject("serviceMaster", serviceMasterObj);
				restResponse = scutils.prepareMobileResponseSuccessStatus(restResponse, "serviceMaster is saved");
				resp = new ResponseEntity<ServiceResponse>(restResponse, HttpStatus.OK);
			} else {
				restResponse = scutils.prepareMobileResponseInvalidData(restResponse, "serviceMaster is not saved");
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

	@PutMapping("/updateServiceMaster")
	ResponseEntity<?> updateServiceMaster(@RequestBody ServiceMaster serviceMaster) {
		logger.debug(">>updateServiceMaster");
		ResponseEntity<?> resp = null;
		ServiceResponse restResponse = new ServiceResponse();
		try {
			ServiceMaster serviceMasterObj = serviceMasterService.updateServiceMaster(serviceMaster);
			if (serviceMasterObj != null) {
				restResponse.addDataObject("serviceMaster", serviceMasterObj);
				restResponse = scutils.prepareMobileResponseSuccessStatus(restResponse, "serviceMaster is updated");
				resp = new ResponseEntity<ServiceResponse>(restResponse, HttpStatus.OK);
			} else {
				restResponse = scutils.prepareMobileResponseInvalidData(restResponse, "serviceMaster is not updated");
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

	@PostMapping("/findAllServiceMasters")
	ResponseEntity<?> findAllServiceMasters(@RequestBody ServiceMasterFilter serviceMasterFilter) {
		logger.debug(">>findAllServiceMasters");
		ResponseEntity<?> resp = null;
		ServiceResponse restResponse = new ServiceResponse();
		try {
			List<ServiceMaster> serviceMasters = serviceMasterService.findAllServiceMasters(serviceMasterFilter);
			if (serviceMasters != null) {
				restResponse.addDataObject("serviceMasters", serviceMasters);
				restResponse = scutils.prepareMobileResponseSuccessStatus(restResponse, "fetched serviceMaster details successfully");
				resp = new ResponseEntity<ServiceResponse>(restResponse, HttpStatus.OK);
			} else {
				restResponse = scutils.prepareMobileResponseInvalidData(restResponse, "serviceMaster details not found");
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

	@DeleteMapping("/deleteServiceMasterById")
	ResponseEntity<?> deleteServiceMasterById(@RequestParam(required =true, value = "_id") String _id) {
		logger.debug(">>deleteServiceMasterById");
		ResponseEntity<?> resp = null;
		ServiceResponse restResponse = new ServiceResponse();
		try {
			ServiceMaster serviceMaster = serviceMasterService.deleteServiceMasterById(_id);
			if (serviceMaster != null) {
				restResponse.addDataObject("serviceMaster", serviceMaster);
				restResponse = scutils.prepareMobileResponseSuccessStatus(restResponse, "deleted serviceMaster successfully");
				resp = new ResponseEntity<ServiceResponse>(restResponse, HttpStatus.OK);
			} else {
				restResponse = scutils.prepareMobileResponseInvalidData(restResponse, "failed to delete serviceMaster");
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

	@PostMapping("/findServiceMasters")
	ResponseEntity<?> findServiceMasters(@RequestBody ServiceMasterFilter serviceMasterFilter) {
		logger.debug(">>findServiceMasters");
		ResponseEntity<?> resp = null;
		ServiceResponse restResponse = new ServiceResponse();
		try {
			List<ServiceMaster> serviceMasters = serviceMasterService.findAllServiceMasters(serviceMasterFilter);
			if (serviceMasters != null) {
				for(ServiceMaster service : serviceMasters) {
					MathContext mathContext = new MathContext(3, RoundingMode.HALF_UP);

					BigDecimal area = service.getLength().multiply(service.getHeight());
					BigDecimal checkNum = service.getCheckNum();
					System.out.println("area>"+area);
					System.out.println("checkNum>"+checkNum);
					BigDecimal wtOfOneProduct = service.getWeightofOneProduct();
					BigDecimal wtOfOneProductD = service.getDweightofOneProduct();
					BigDecimal quantity = service.getQuantity();
					BigDecimal dquantity = service.getDquantity();

					System.out.println("wtOfOneProduct>"+wtOfOneProduct);
					System.out.println("quantity>"+quantity); 
					BigDecimal wt = wtOfOneProduct.multiply(quantity,mathContext);
					BigDecimal wtD = wtOfOneProduct.multiply(dquantity,mathContext);
					System.out.println("wt>"+wt);
					System.out.println("wtD>"+wtD);

				}
				
				restResponse.addDataObject("serviceMasters", serviceMasters);
				restResponse = scutils.prepareMobileResponseSuccessStatus(restResponse, "fetched serviceMaster details successfully");
				resp = new ResponseEntity<ServiceResponse>(restResponse, HttpStatus.OK);
			} else {
				restResponse = scutils.prepareMobileResponseInvalidData(restResponse, "serviceMaster details not found");
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
