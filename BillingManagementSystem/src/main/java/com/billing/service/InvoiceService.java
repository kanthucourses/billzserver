package com.billing.service;

import java.util.List;

import com.billing.dto.InvoiceFilter;
import com.billing.dto.InvoicePaginationResponse;
import com.billing.dto.InvoiceRevenueResponse;
import com.billing.dto.QuantityByServiceIDName;
import com.billing.model.Invoice;

public interface InvoiceService {
	public Invoice saveInvoice(Invoice invoice);

	public Invoice updateInvoice(Invoice invoice);

	public List<Invoice> findAllInvoices(InvoiceFilter invoiceFilter);

	public Invoice findInvoiceById(String _id);
	
	public Invoice deleteInvoiceById(String _id,String lineID,String organizationIDName);
	
	public InvoicePaginationResponse findAllInvoicesWithPagination(InvoiceFilter invoiceFilter);
	
	public List<Invoice> findInvoicesByFilter(InvoiceFilter invoiceFilter);
	
	public List<InvoiceRevenueResponse> findInvoicesRevenueByFilter(InvoiceFilter invoiceFilter);

	public List<QuantityByServiceIDName> findServicesDataByFilter(InvoiceFilter invoiceFilter);
	
}
