package com.billing.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.SkipOperation;
import org.springframework.data.mongodb.core.aggregation.UnwindOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.billing.dao.InvoiceDao;
import com.billing.dto.InvoiceFilter;
import com.billing.model.Invoice;
import com.billing.model.InvoiceLine;
import com.billing.service.OrganizationService;
import com.billing.util.RequestConstants;

@Repository
public class InvoiceDaoImpl implements InvoiceDao{
	private static Logger logger = LogManager.getLogger(InvoiceDaoImpl.class);
	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private OrganizationService organizationService;

	public List<Invoice> findInvoices(InvoiceFilter invoiceFilter) {
		logger.debug(">>findInvoices..invoiceFilter" + invoiceFilter);
		List<Invoice> invoiceList = new ArrayList<Invoice>();
		Query searchQuery = new Query();
		try {
			if (invoiceFilter.getInvoiceID() != null) {
				searchQuery.addCriteria(Criteria.where("invoiceID").is(invoiceFilter.getInvoiceID()));
			}
			if (invoiceFilter.getOrganizationIDName() != null && !invoiceFilter.getOrganizationIDName().isEmpty()) {
				searchQuery.addCriteria(Criteria.where("organizationInfo.organizationIDName").is(invoiceFilter.getOrganizationIDName()));
			}
			invoiceList = mongoTemplate.find(searchQuery, Invoice.class,RequestConstants.Collections.INVOICE);	
		
		} catch (Exception e) {
			logger.error("Error occured\t" + e.getMessage(), e);
		}
		logger.debug(">>findInvoices>invoiceList"+invoiceList);
		return invoiceList;
	}
	
	public Page<Invoice> findInvoicesWithPagination(InvoiceFilter invoiceFilter,Pageable pageable) {
		logger.debug(">>findInvoicesWithPagination..invoiceFilter" + invoiceFilter);
		List<Invoice> invoiceList = new ArrayList<Invoice>();
		Query searchQuery = new Query();
		Page<Invoice> invoicePageList = null;
		try {	
			if (invoiceFilter.getInvoiceID() != null) {
				searchQuery.addCriteria(Criteria.where("invoiceID").is(invoiceFilter.getInvoiceID()));
			}
			if (invoiceFilter.getOrganizationIDName() != null && !invoiceFilter.getOrganizationIDName().isEmpty()) {
				searchQuery.addCriteria(Criteria.where("organizationInfo.organizationIDName").is(invoiceFilter.getOrganizationIDName()));
			}
			if (invoiceFilter.getServiceID() != null && !invoiceFilter.getServiceID().isEmpty()) {
				searchQuery.addCriteria(Criteria.where("invoiceLines")
						.elemMatch(Criteria.where("serviceID").is(invoiceFilter.getServiceID())));
			}
			long count = mongoTemplate.count(searchQuery, Invoice.class,RequestConstants.Collections.INVOICE);   
			invoiceList = mongoTemplate.find(searchQuery, Invoice.class,RequestConstants.Collections.INVOICE);	
			invoicePageList = new PageImpl<Invoice>(invoiceList, pageable, count);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error occured\t" + e.getMessage(), e);
		}
		logger.debug(">>findInvoicesWithPagination>invoicePageList"+invoicePageList);
		return invoicePageList;
	}
	
	/*
	public Page<Invoice> findInvoicesWithPagination(InvoiceFilter invoiceFilter,Pageable pageable) {
		logger.debug(">>findInvoicesWithPagination..invoiceFilter" + invoiceFilter);
		List<Invoice> invoiceList = new ArrayList<Invoice>();
		Query searchQuery = new Query();
		Page<Invoice> invoicePageList = null;
		try {
			
			List<AggregationOperation> aggregationOperations = new ArrayList<>();

			 MatchOperation matchOperation = Aggregation.match(Criteria.where("invoiceID").is(invoiceFilter.getInvoiceID())
	                    .and("organizationInfo.organizationIDName").is(invoiceFilter.getOrganizationIDName())
	                    .and("invoiceLines.serviceID").is(invoiceFilter.getServiceID()));
	            aggregationOperations.add(matchOperation);

	            // Unwind the invoiceLines array
	            UnwindOperation unwindOperation = Aggregation.unwind("invoiceLines");
	            aggregationOperations.add(unwindOperation);

	            // Match again to filter only the matching invoiceLines
	            MatchOperation matchInvoiceLines = Aggregation.match(Criteria.where("invoiceLines.serviceID").is(invoiceFilter.getServiceID()));
	            aggregationOperations.add(matchInvoiceLines);

	            // Skip and limit for pagination
	            aggregationOperations.add(Aggregation.skip((long) pageable.getPageNumber() * pageable.getPageSize()));
	            aggregationOperations.add(Aggregation.limit(pageable.getPageSize()));

	            Aggregation aggregation = Aggregation.newAggregation(aggregationOperations);

	            AggregationResults<Invoice> aggregationResults = mongoTemplate.aggregate(aggregation,
	                    RequestConstants.Collections.INVOICE, Invoice.class);

            invoiceList = aggregationResults.getMappedResults();
            long count = invoiceList.size(); // Update count based on the number of results
            invoicePageList = new PageImpl<Invoice>(invoiceList, pageable, count);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error occured\t" + e.getMessage(), e);
		}
		logger.debug(">>findInvoicesWithPagination>invoicePageList"+invoicePageList);
		return invoicePageList;
	}
	
	 */
	
	public Invoice findLatestInvoice(String organizationIDName){
		logger.debug(">>findLatestInvoice");
		Invoice invoice = null;
		Query searchQuery = new Query();
		try {
			searchQuery.addCriteria(Criteria.where("organizationInfo.organizationIDName").is(organizationIDName));
			searchQuery.addCriteria(Criteria.where("invoiceID").ne(null));

			searchQuery.limit(1);
			Sort sort = Sort.by("invoiceID").descending();
			searchQuery.with(sort);
			invoice = mongoTemplate.findOne(searchQuery, Invoice.class, RequestConstants.Collections.INVOICE);
		} catch (Exception e) {
			logger.error("Error occured\t" + e.getMessage(), e);
		}
		logger.debug("<< findLatestInvoice invoice="+invoice);
		return invoice;
	}
	
	public Invoice deleteByInvoiceId(String _id,String lineID,String organizationIDName){
		logger.debug(">>deleteByInvoiceId");
		Invoice invoice = null;
		Invoice invoiceUpdatedObj = null;
		Query searchQuery = new Query();
		Update update = new Update();
		try {
			searchQuery.addCriteria(Criteria.where("_id").is(_id));
			searchQuery.addCriteria(Criteria.where("organizationInfo.organizationIDName").is(organizationIDName));
			invoice = mongoTemplate.findOne(searchQuery, Invoice.class, RequestConstants.Collections.INVOICE);
			if(invoice != null) {
				if(_id != null && !_id.isEmpty() && lineID != null) {
					List<InvoiceLine> invoiceLines= invoice.getInvoiceLines();
					if(invoiceLines != null && invoiceLines.size()>0) {
						for(int i=0;i<invoiceLines.size();i++) {
							InvoiceLine invoiceLine = invoiceLines.get(i);
							if(invoiceLine != null && invoiceLine.getLineID() != null && lineID != null &&
									invoiceLine.getLineID().equals(lineID)) {
								invoiceLines.remove(invoiceLine);
							}
						}
					}
					if(invoiceLines != null && invoiceLines.size()>0) {
						update.set("invoiceLines", invoiceLines);
						invoiceUpdatedObj = mongoTemplate.findAndModify(searchQuery, update, new FindAndModifyOptions().returnNew(true),Invoice.class, RequestConstants.Collections.INVOICE);
					}
					else {
						invoiceUpdatedObj = mongoTemplate.findAndRemove(searchQuery,Invoice.class, RequestConstants.Collections.INVOICE);
					}
				}
				else {
					invoiceUpdatedObj = mongoTemplate.findAndRemove(searchQuery,Invoice.class, RequestConstants.Collections.INVOICE);
				}
			}

		} catch (Exception e) {
			logger.error("Error occured\t" + e.getMessage(), e);
		}
		logger.debug("<< deleteByInvoiceId invoice="+invoice);
		return invoiceUpdatedObj;
	}
	
}

