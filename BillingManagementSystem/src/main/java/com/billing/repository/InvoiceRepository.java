package com.billing.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.billing.dao.InvoiceDao;
import com.billing.model.Invoice;

public interface InvoiceRepository extends MongoRepository<Invoice, String>,InvoiceDao {

}
