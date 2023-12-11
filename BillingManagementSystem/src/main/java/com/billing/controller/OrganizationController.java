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

import com.billing.model.Organization;
import com.billing.service.OrganizationService;
import com.billing.util.AppConstants;
import com.billing.util.ServiceControllerUtils;
import com.billing.util.ServiceResponse;

@CrossOrigin(origins = "*",allowedHeaders = "*")
@RestController
@RequestMapping("/organization/services")
public class OrganizationController {
	private static Logger logger = LogManager.getLogger(OrganizationController.class);

	@Autowired
	private ServiceControllerUtils scutils;

	@Autowired
	private OrganizationService organizationService;

	@PostMapping("/saveOrganization")
	ResponseEntity<?> saveOrganization(@RequestBody Organization organization) {
		logger.debug(">>saveOrganization");
		ResponseEntity<?> resp = null;
		ServiceResponse restResponse = new ServiceResponse();
		try {
			Organization organizationObj = organizationService.saveOrganization(organization);
			if (organizationObj != null) {
				restResponse.addDataObject("organization", organizationObj);
				restResponse = scutils.prepareMobileResponseSuccessStatus(restResponse, "organization is saved");
				resp = new ResponseEntity<ServiceResponse>(restResponse, HttpStatus.OK);
			} else {
				restResponse = scutils.prepareMobileResponseInvalidData(restResponse, "organization is not saved");
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

	@PutMapping("/updateOrganization")
	ResponseEntity<?> updateOrganization(@RequestBody Organization organization) {
		logger.debug(">>updateOrganization");
		ResponseEntity<?> resp = null;
		ServiceResponse restResponse = new ServiceResponse();
		try {
			Organization organizationObj = organizationService.updateOrganization(organization);
			if (organizationObj != null) {
				restResponse.addDataObject("organization", organizationObj);
				restResponse = scutils.prepareMobileResponseSuccessStatus(restResponse, "organization is updated");
				resp = new ResponseEntity<ServiceResponse>(restResponse, HttpStatus.OK);
			} else {
				restResponse = scutils.prepareMobileResponseInvalidData(restResponse, "organization is not updated");
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

	@GetMapping("/findAllOrganizations")
	ResponseEntity<?> findAllOrganizations() {
		logger.debug(">>findAllOrganizations");
		ResponseEntity<?> resp = null;
		ServiceResponse restResponse = new ServiceResponse();
		try {
			List<Organization> organizations = organizationService.findAllOrganizations();
			if (organizations != null) {
				restResponse.addDataObject("organizations", organizations);
				restResponse = scutils.prepareMobileResponseSuccessStatus(restResponse, "fetched organization details successfully");
				resp = new ResponseEntity<ServiceResponse>(restResponse, HttpStatus.OK);
			} else {
				restResponse = scutils.prepareMobileResponseInvalidData(restResponse, "organization details not found");
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

	@GetMapping("/findOrganizationById")
	ResponseEntity<?> findOrganizationById(@RequestParam(required =true, value = "_id") String _id) {
		logger.debug(">>findOrganizationById");
		ResponseEntity<?> resp = null;
		ServiceResponse restResponse = new ServiceResponse();
		try {
			Organization organization = organizationService.findOrganizationById(_id);
			if (organization != null) {
				restResponse.addDataObject("organization", organization);
				restResponse = scutils.prepareMobileResponseSuccessStatus(restResponse, "fetched organization successfully");
				resp = new ResponseEntity<ServiceResponse>(restResponse, HttpStatus.OK);
			} else {
				restResponse = scutils.prepareMobileResponseInvalidData(restResponse, "failed to fetch organization");
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
	
	@DeleteMapping("/deleteOrganizationById")
	ResponseEntity<?> deleteOrganizationById(@RequestParam(required =true, value = "_id") String _id) {
		logger.debug(">>deleteOrganizationById");
		ResponseEntity<?> resp = null;
		ServiceResponse restResponse = new ServiceResponse();
		try {
			Organization organization = organizationService.deleteOrganizationById(_id);
			if (organization != null) {
				restResponse.addDataObject("organization", organization);
				restResponse = scutils.prepareMobileResponseSuccessStatus(restResponse, "deleted organization successfully");
				resp = new ResponseEntity<ServiceResponse>(restResponse, HttpStatus.OK);
			} else {
				restResponse = scutils.prepareMobileResponseInvalidData(restResponse, "failed to delete organization");
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
