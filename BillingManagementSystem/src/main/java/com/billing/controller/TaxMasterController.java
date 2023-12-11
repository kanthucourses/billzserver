package com.billing.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.billing.model.TaxMaster;
import com.billing.service.TaxMasterService;
import com.billing.util.AppConstants;
import com.billing.util.ServiceControllerUtils;
import com.billing.util.ServiceResponse;

@CrossOrigin(origins = "*",allowedHeaders = "*")
@RestController
@RequestMapping("tax/services")
public class TaxMasterController {
	private static Logger logger = LogManager.getLogger(TaxMasterController.class);

	@Autowired
	private ServiceControllerUtils scutils;

	@Autowired
	private TaxMasterService taxMasterService;

	@PostMapping("/saveTaxMaster")
	ResponseEntity<?> saveTaxMaster(@RequestBody TaxMaster taxMaster) {
		logger.debug(">>saveTaxMaster");
		ResponseEntity<?> resp = null;
		ServiceResponse restResponse = new ServiceResponse();
		try {
			TaxMaster taxMasterObj = taxMasterService.saveTaxMaster(taxMaster);
			if (taxMasterObj != null) {
				restResponse.addDataObject("taxMaster", taxMasterObj);
				restResponse = scutils.prepareMobileResponseSuccessStatus(restResponse, "taxMaster is saved");
				resp = new ResponseEntity<ServiceResponse>(restResponse, HttpStatus.OK);
			} else {
				restResponse = scutils.prepareMobileResponseInvalidData(restResponse, "taxMaster is not saved");
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

	@PutMapping("/updateTaxMaster")
	ResponseEntity<?> updateTaxMaster(@RequestBody TaxMaster taxMaster) {
		logger.debug(">>updateTaxMaster");
		ResponseEntity<?> resp = null;
		ServiceResponse restResponse = new ServiceResponse();
		try {
			TaxMaster taxMasterObj = taxMasterService.updateTaxMaster(taxMaster);
			if (taxMasterObj != null) {
				restResponse.addDataObject("taxMaster", taxMasterObj);
				restResponse = scutils.prepareMobileResponseSuccessStatus(restResponse, "taxMaster is updated");
				resp = new ResponseEntity<ServiceResponse>(restResponse, HttpStatus.OK);
			} else {
				restResponse = scutils.prepareMobileResponseInvalidData(restResponse, "taxMaster is not updated");
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

	@GetMapping("/findAllTaxMasters")
	ResponseEntity<?> findAllTaxMasters() {
		logger.debug(">>findAllTaxMasters");
		ResponseEntity<?> resp = null;
		ServiceResponse restResponse = new ServiceResponse();
		try {
			List<TaxMaster> taxMasters = taxMasterService.findAllTaxMasters();
			if (taxMasters != null) {
				restResponse.addDataObject("taxMasters", taxMasters);
				restResponse = scutils.prepareMobileResponseSuccessStatus(restResponse, "fetched taxMaster details successfully");
				resp = new ResponseEntity<ServiceResponse>(restResponse, HttpStatus.OK);
			} else {
				restResponse = scutils.prepareMobileResponseInvalidData(restResponse, "taxMaster details not found");
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

	@DeleteMapping("/deleteTaxMasterById")
	ResponseEntity<?> deleteTaxMasterById(@RequestParam(required =true, value = "_id") String _id) {
		logger.debug(">>deleteTaxMasterById");
		ResponseEntity<?> resp = null;
		ServiceResponse restResponse = new ServiceResponse();
		try {
			TaxMaster taxMaster = taxMasterService.deleteTaxMasterById(_id);
			if (taxMaster != null) {
				restResponse.addDataObject("taxMaster", taxMaster);
				restResponse = scutils.prepareMobileResponseSuccessStatus(restResponse, "deleted taxMaster successfully");
				resp = new ResponseEntity<ServiceResponse>(restResponse, HttpStatus.OK);
			} else {
				restResponse = scutils.prepareMobileResponseInvalidData(restResponse, "failed to delete taxMaster");
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


