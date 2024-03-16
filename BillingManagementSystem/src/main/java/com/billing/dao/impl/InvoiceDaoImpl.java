package com.billing.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.billing.dao.InvoiceDao;
import com.billing.dto.InvoiceFilter;
import com.billing.dto.QuantityByServiceIDName;
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
	    private MongoOperations mongoOperations;
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
	
	/*
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
	*/
	
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
	
	public List<Invoice> findInvoicesByFilter(InvoiceFilter invoiceFilter) {
		logger.debug(">>findInvoicesByFilter..invoiceFilter" + invoiceFilter);
		List<Invoice> invoiceList = new ArrayList<Invoice>();
		Query searchQuery = new Query();
		try {
			if(invoiceFilter != null) {
				if (invoiceFilter.getInvoiceDateFrom() != null && invoiceFilter.getInvoiceDateTo() != null) {
					searchQuery.addCriteria(Criteria.where("invoiceDate").gte(invoiceFilter.getInvoiceDateFrom()).lte(invoiceFilter.getInvoiceDateTo()));
				}
				if (invoiceFilter.getInvoiceID() != null) {
					searchQuery.addCriteria(Criteria.where("invoiceID").is(invoiceFilter.getInvoiceID()));
				}
				if (invoiceFilter.getAgeFrom() != null && invoiceFilter.getAgeTo() != null) {
					searchQuery.addCriteria(Criteria.where("age").gte(invoiceFilter.getAgeFrom()).lte(invoiceFilter.getAgeTo()));
				}
				if (invoiceFilter.getOrganizationIDName() != null && !invoiceFilter.getOrganizationIDName().isEmpty()) {
					searchQuery.addCriteria(Criteria.where("organizationInfo.organizationIDName").is(invoiceFilter.getOrganizationIDName()));
				}
			}
			invoiceList = mongoTemplate.find(searchQuery, Invoice.class,RequestConstants.Collections.INVOICE);	

		} catch (Exception e) {
			logger.error("Error occured\t" + e.getMessage(), e);
		}
		logger.debug(">>findInvoicesByFilter>invoiceList"+invoiceList);
		return invoiceList;
	}
	
	/*
	public List<QuantityByServiceIDName> getServiceIDNamesAndQuantities() {
		List<QuantityByServiceIDName> quantityByServiceIDNameList = null;
		try {
			GroupOperation groupOperation = Aggregation.group("invoiceLines.serviceIDName")
					.sum("invoiceLines.quantity").as("quantity")
					 .first("invoiceLines.serviceIDName").as("serviceIDName");
			TypedAggregation<Invoice> aggregation = Aggregation.newAggregation(
				    Invoice.class,
				    Aggregation.unwind("invoiceLines"),
				    groupOperation
				);
			quantityByServiceIDNameList = mongoTemplate.aggregate(aggregation, RequestConstants.Collections.INVOICE, QuantityByServiceIDName.class).getMappedResults();
		} catch (Exception e) {
			logger.error("Error occured\t" + e.getMessage(), e);
		}
		return quantityByServiceIDNameList;
	}
	*/
	
	public List<QuantityByServiceIDName> getServiceIDNamesAndQuantities(InvoiceFilter invoiceFilter) {
	    List<QuantityByServiceIDName> quantityByServiceIDNameList = null;
	    try {
	        Criteria matchCriteria = new Criteria();

	        if (invoiceFilter.getOrganizationIDName() != null && !invoiceFilter.getOrganizationIDName().isEmpty()) {
	            matchCriteria = matchCriteria.and("organizationInfo.organizationIDName").is(invoiceFilter.getOrganizationIDName());
	        }

	        if (invoiceFilter.getInvoiceDateFrom() != null && invoiceFilter.getInvoiceDateTo() != null) {
	            Criteria dateCriteria = Criteria.where("invoiceDate")
	                .gte(invoiceFilter.getInvoiceDateFrom())
	                .lte(invoiceFilter.getInvoiceDateTo());

	            matchCriteria = matchCriteria.andOperator(dateCriteria);
	        }

	        MatchOperation matchOperation = Aggregation.match(matchCriteria);

	        GroupOperation groupOperation = Aggregation.group("invoiceLines.serviceIDName")
	                .sum("invoiceLines.quantity").as("quantity")
	                .sum("invoiceLines.netAmount").as("netAmount");

	        ProjectionOperation projectOperation = Aggregation.project()
	                .and("_id").as("serviceIDName")
	                .and("quantity").as("quantity")
	                .and("netAmount").as("netAmount");

	        TypedAggregation<Invoice> aggregation;
	        if (matchOperation != null) {
	            aggregation = Aggregation.newAggregation(
	                    Invoice.class,
	                    Aggregation.unwind("invoiceLines"),
	                    matchOperation,
	                    groupOperation,
	                    projectOperation
	            );
	        } else {
	            aggregation = Aggregation.newAggregation(
	                    Invoice.class,
	                    Aggregation.unwind("invoiceLines"),
	                    groupOperation,
	                    projectOperation
	            );
	        }

	        quantityByServiceIDNameList = mongoTemplate.aggregate(aggregation, RequestConstants.Collections.INVOICE, QuantityByServiceIDName.class).getMappedResults();
	    } catch (Exception e) {
	        logger.error("Error occurred: " + e.getMessage(), e);
	    }
	    return quantityByServiceIDNameList;
	}

	/*
	 public Page<Invoice> findInvoicesWithPagination(InvoiceFilter invoiceFilter,Pageable pageable) {
	        // Match operation to filter by organizationIDName
	        Criteria matchCriteria = Criteria.where("organizationInfo.organizationIDName").is(invoiceFilter.getOrganizationIDName());

	        // Aggregation pipeline to match and unwind invoiceLines
	        AggregationOperation matchOperation = Aggregation.match(matchCriteria);
	        AggregationOperation unwindOperation = Aggregation.unwind("invoiceLines");

	        // Aggregation pipeline to apply pagination after unwinding invoiceLines
	        AggregationOperation skipOperation = Aggregation.skip((long) pageable.getPageNumber() * pageable.getPageSize());
	        AggregationOperation limitOperation = Aggregation.limit(pageable.getPageSize());

	        // Grouping back by _id to collect the unwinded invoiceLines
	        AggregationOperation groupOperation = Aggregation.group("_id")
	                .first("invoiceID").as("invoiceID")
	                .first("customerDetails").as("customerDetails")
	                .first("invoiceDate").as("invoiceDate")
	                .first("lastUpdatedDateTime").as("lastUpdatedDateTime")
	                .first("organizationInfo").as("organizationInfo")
	                .first("totalGrossAmount").as("totalGrossAmount")
	                .first("totalTaxAmount").as("totalTaxAmount")
	                .first("totalNetAmount").as("totalNetAmount")
	                .first("totalDiscountAmount").as("totalDiscountAmount")
	                .first("taxDetailsList").as("taxDetailsList")
	                .first("invoiceStatus").as("invoiceStatus")
	                .first("paymentStatus").as("paymentStatus")
	                .push("invoiceLines").as("invoiceLines");

	        // Applying the aggregation pipeline
	        Aggregation aggregation = Aggregation.newAggregation(
	                matchOperation,
	                unwindOperation,
	                skipOperation,
	                limitOperation,
	                groupOperation
	        );

	        AggregationResults<Invoice> aggregationResults = mongoTemplate.aggregate(aggregation, "invoice", Invoice.class);

	        List<Invoice> invoices = aggregationResults.getMappedResults();
	        return new PageImpl<>(invoices, pageable, invoices.size());
	    }
	 */
	 
	 public Page<Invoice> findInvoicesWithPagination(InvoiceFilter invoiceFilter, Pageable pageable) {
	        // Construct the aggregation pipeline
	        TypedAggregation<Invoice> aggregation = Aggregation.newAggregation(Invoice.class,
	                Aggregation.match(org.springframework.data.mongodb.core.query.Criteria.where("organizationInfo.organizationIDName").is(invoiceFilter.getOrganizationIDName())),
	                Aggregation.unwind("invoiceLines"),
	                Aggregation.skip((long) pageable.getPageNumber() * pageable.getPageSize()),
	                Aggregation.limit(pageable.getPageSize()),
	                Aggregation.group("_id")
	                        .first("invoiceID").as("invoiceID")
	                        .first("customerDetails").as("customerDetails")
	                        .first("invoiceDate").as("invoiceDate")
	                        .first("lastUpdatedDateTime").as("lastUpdatedDateTime")
	                        .first("organizationInfo").as("organizationInfo")
	                        .first("totalGrossAmount").as("totalGrossAmount")
	                        .first("totalTaxAmount").as("totalTaxAmount")
	                        .first("totalNetAmount").as("totalNetAmount")
	                        .first("totalDiscountAmount").as("totalDiscountAmount")
	                        .first("taxDetailsList").as("taxDetailsList")
	                        .first("invoiceStatus").as("invoiceStatus")
	                        .first("paymentStatus").as("paymentStatus")
	                        .push("invoiceLines").as("invoiceLines")
	        );

	        // Execute the aggregation pipeline
	        List<Invoice> invoices = mongoTemplate.aggregate(aggregation, Invoice.class).getMappedResults();

	        // Count total documents matching the query criteria
	        //long total = mongoTemplate.count(new Query(), Invoice.class);
	        long total = invoices.size();

	        // Create a Page object
	        return new PageImpl<>(invoices, pageable, total);
	    }
	 
}

