package com.billing.service;

import java.util.List;

import com.billing.model.Invoice;

public interface InvoiceService {
	public Invoice saveInvoice(Invoice invoice);

	public Invoice updateInvoice(Invoice invoice);

	public List<Invoice> findAllInvoices();

	public Invoice findInvoiceById(String _id);
	
	public Invoice deleteInvoiceById(String _id);
	
}
