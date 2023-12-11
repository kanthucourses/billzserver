package com.billing.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.billing.model.Invoice;
import com.billing.model.InvoiceLine;
import com.billing.repository.InvoiceRepository;
import com.billing.service.InvoiceService;

@Service
@Transactional
public class InvoiceServiceImpl implements InvoiceService{

	@Autowired
	private InvoiceRepository invoiceRepository;

	public Invoice saveInvoice(Invoice invoice) {
		AtomicLong nummberGeneratorForInvoice = new AtomicLong(1001);
		Invoice invoiceObj = null;
		Long nextSequence = nummberGeneratorForInvoice.getAndIncrement();
		invoice.setInvoiceID(nextSequence);
		long value = 101;
		if(invoice.getInvoiceLines() != null && invoice.getInvoiceLines().size()>0) {
			for(InvoiceLine invoiceLine : invoice.getInvoiceLines()) {
				invoiceLine.setInvoiceLineID(value);
				value++;
			}
		}
		invoiceObj = invoiceRepository.save(invoice);
		return invoiceObj;
	}

	public Invoice updateInvoice(Invoice invoice) {
		Invoice invoiceObj = null;
		invoiceObj = invoiceRepository.save(invoice);
		return invoiceObj;
	}

	public List<Invoice> findAllInvoices() {
		List<Invoice> invoices = null;
		invoices = invoiceRepository.findAll();
		return invoices;
	}
	
	public Invoice findInvoiceById(String _id) {
		Invoice invoice = null;
		Optional<Invoice> invoiceOptional = invoiceRepository.findById(_id);
		if(invoiceOptional.isPresent()) {
			invoice = invoiceOptional.get();
		}
		return invoice;
	}

	public Invoice deleteInvoiceById(String _id) {
		Invoice invoiceObj = null;
		Optional<Invoice> invoice = invoiceRepository.findById(_id);
		if(invoice.isPresent()) {
			invoiceRepository.deleteById(_id);
			invoiceObj = invoice.get();
		}
		return invoiceObj;
	}

}
