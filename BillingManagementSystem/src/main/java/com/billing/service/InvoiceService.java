package com.billing.service;

import java.util.List;

import com.billing.dto.InvoiceFilter;
import com.billing.dto.InvoicePaginationResponse;
import com.billing.model.Invoice;

public interface InvoiceService {
	public Invoice saveInvoice(Invoice invoice);

	public Invoice updateInvoice(Invoice invoice);

	public List<Invoice> findAllInvoices(InvoiceFilter invoiceFilter);

	public Invoice findInvoiceById(String _id);
	
	public Invoice deleteInvoiceById(String _id,Long invoiceLineID,String organizationIDName);
	
	public InvoicePaginationResponse findAllInvoicesWithPagination(InvoiceFilter invoiceFilter);
	
}
