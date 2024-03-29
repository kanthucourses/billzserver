package com.billing.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.billing.dto.InvoiceFilter;
import com.billing.dto.QuantityByServiceIDName;
import com.billing.model.Invoice;

public interface InvoiceDao {
	public List<Invoice> findInvoices(InvoiceFilter invoiceFilter);
	public Invoice findLatestInvoice(String organizationIDName);
	public Invoice deleteByInvoiceId(String _id,String lineID,String organizationIDName);
	public Page<Invoice> findInvoicesWithPagination(InvoiceFilter invoiceFilter,Pageable pageable);
	public List<Invoice> findInvoicesByFilter(InvoiceFilter invoiceFilter);
	public List<QuantityByServiceIDName> getServiceIDNamesAndQuantities(InvoiceFilter invoiceFilter);
}
