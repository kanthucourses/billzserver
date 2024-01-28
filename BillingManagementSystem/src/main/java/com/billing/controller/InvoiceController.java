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

import com.billing.dto.InvoiceFilter;
import com.billing.dto.InvoicePaginationResponse;
import com.billing.model.Invoice;
import com.billing.service.InvoiceService;
import com.billing.util.AppConstants;
import com.billing.util.ServiceControllerUtils;
import com.billing.util.ServiceResponse;

@CrossOrigin(origins = "*",allowedHeaders = "*")
@RestController
@RequestMapping("/invoice/services")
public class InvoiceController {

	private static Logger logger = LogManager.getLogger(InvoiceController.class);

	@Autowired
	private ServiceControllerUtils scutils;

	@Autowired
	private InvoiceService invoiceService;

	@PostMapping("/saveInvoice")
	ResponseEntity<?> saveInvoice(@RequestBody Invoice invoice) {
		logger.debug(">>saveInvoice");
		ResponseEntity<?> resp = null;
		ServiceResponse restResponse = new ServiceResponse();
		try {
			Invoice invoiceObj = invoiceService.saveInvoice(invoice);
			if (invoiceObj != null) {
				restResponse.addDataObject("invoice", invoiceObj);
				restResponse = scutils.prepareMobileResponseSuccessStatus(restResponse, "invoice is saved");
				resp = new ResponseEntity<ServiceResponse>(restResponse, HttpStatus.OK);
			} else {
				restResponse = scutils.prepareMobileResponseInvalidData(restResponse, "invoice is not saved");
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

	@PutMapping("/updateInvoice")
	ResponseEntity<?> updateInvoice(@RequestBody Invoice invoice) {
		logger.debug(">>updateInvoice");
		ResponseEntity<?> resp = null;
		ServiceResponse restResponse = new ServiceResponse();
		try {
			Invoice invoiceObj = invoiceService.updateInvoice(invoice);
			if (invoiceObj != null) {
				restResponse.addDataObject("invoice", invoiceObj);
				restResponse = scutils.prepareMobileResponseSuccessStatus(restResponse, "invoice is updated");
				resp = new ResponseEntity<ServiceResponse>(restResponse, HttpStatus.OK);
			} else {
				restResponse = scutils.prepareMobileResponseInvalidData(restResponse, "invoice is not updated");
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

	@PostMapping("/findAllInvoices")
	ResponseEntity<?> findAllInvoices(@RequestBody InvoiceFilter invoiceFilter) {
		logger.debug(">>findAllInvoices");
		ResponseEntity<?> resp = null;
		ServiceResponse restResponse = new ServiceResponse();
		try {
			List<Invoice> invoices = invoiceService.findAllInvoices(invoiceFilter);
			if (invoices != null) {
				restResponse.addDataObject("invoices", invoices);
				restResponse = scutils.prepareMobileResponseSuccessStatus(restResponse, "fetched invoice details successfully");
				resp = new ResponseEntity<ServiceResponse>(restResponse, HttpStatus.OK);
			} else {
				restResponse = scutils.prepareMobileResponseInvalidData(restResponse, "invoice details not found");
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

	@GetMapping("/findInvoiceById")
	ResponseEntity<?> findInvoiceById(@RequestParam(required =true, value = "_id") String _id) {
		logger.debug(">>findInvoiceById");
		ResponseEntity<?> resp = null;
		ServiceResponse restResponse = new ServiceResponse();
		try {
			Invoice invoice = invoiceService.findInvoiceById(_id);
			if (invoice != null) {
				restResponse.addDataObject("invoice", invoice);
				restResponse = scutils.prepareMobileResponseSuccessStatus(restResponse, "fetched invoice successfully");
				resp = new ResponseEntity<ServiceResponse>(restResponse, HttpStatus.OK);
			} else {
				restResponse = scutils.prepareMobileResponseInvalidData(restResponse, "failed to fetch invoice");
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
	
	@DeleteMapping("/deleteInvoiceById")
	ResponseEntity<?> deleteInvoiceById(@RequestParam(required =true, value = "_id") String _id,
			@RequestParam(required =false, value = "invoiceLineID") Long invoiceLineID,
			@RequestParam(required =true, value = "organizationIDName") String organizationIDName) {
		logger.debug(">>deleteInvoiceById");
		ResponseEntity<?> resp = null;
		ServiceResponse restResponse = new ServiceResponse();
		try {
			Invoice invoiceObj = invoiceService.findInvoiceById(_id);
			if(invoiceObj == null) {
				restResponse.addDataObject("invoice", invoiceObj);
				restResponse = scutils.prepareMobileResponseSuccessStatus(restResponse, "unable to find invoice");
				resp = new ResponseEntity<ServiceResponse>(restResponse, HttpStatus.OK);
			}
			Invoice invoice = invoiceService.deleteInvoiceById(_id, invoiceLineID, organizationIDName);
			if (invoice != null) {
				restResponse.addDataObject("invoice", invoice);
				restResponse = scutils.prepareMobileResponseSuccessStatus(restResponse, "deleted invoice successfully");
				resp = new ResponseEntity<ServiceResponse>(restResponse, HttpStatus.OK);
			} else {
				restResponse = scutils.prepareMobileResponseInvalidData(restResponse, "failed to delete invoice");
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

	@PostMapping("/findAllInvoicesWithPagination")
	ResponseEntity<?> findAllInvoicesWithPagination(@RequestBody InvoiceFilter invoiceFilter) {
		logger.debug(">>findAllInvoices");
		ResponseEntity<?> resp = null;
		ServiceResponse restResponse = new ServiceResponse();
		try {
			InvoicePaginationResponse invoicePaginationResponse = invoiceService.findAllInvoicesWithPagination(invoiceFilter);
			if (invoicePaginationResponse != null) {
				restResponse.addDataObject("invoicePaginationResponse", invoicePaginationResponse);
				restResponse = scutils.prepareMobileResponseSuccessStatus(restResponse, "fetched invoice details successfully");
				resp = new ResponseEntity<ServiceResponse>(restResponse, HttpStatus.OK);
			} else {
				restResponse = scutils.prepareMobileResponseInvalidData(restResponse, "invoice details not found");
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
