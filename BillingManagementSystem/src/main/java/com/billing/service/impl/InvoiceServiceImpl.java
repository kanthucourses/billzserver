package com.billing.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.billing.dto.InvoiceFilter;
import com.billing.dto.InvoicePaginationResponse;
import com.billing.model.Invoice;
import com.billing.model.InvoiceLine;
import com.billing.repository.InvoiceRepository;
import com.billing.service.InvoiceService;

@Service
@Transactional
public class InvoiceServiceImpl implements InvoiceService{

	@Autowired
	private InvoiceRepository invoiceRepository;
	
	public String gen() {
		return RandomStringUtils.randomNumeric(8);
	}

	public Invoice saveInvoice(Invoice invoice) {
		final String desc = "DESC";
		final String asc = "ASC";

		AtomicLong nummberGeneratorForInvoice = new AtomicLong(1001);
		AtomicLong nummberGeneratorForInvoiceLine = new AtomicLong(101);

		Invoice invoiceObj = null;
		//long value = 101;
		String organizationIDName = null;

		if(invoice.getOrganizationInfo() != null) {
			organizationIDName = invoice.getOrganizationInfo().getOrganizationIDName();
		}

		if(invoice.getInvoiceStatus() != null && invoice.getInvoiceStatus().equals("Confirmed")) {
			if(invoice.getInvoiceLines() != null && invoice.getInvoiceLines().size()>0) {
				for(int i=0; i<invoice.getInvoiceLines().size();i++) {
					InvoiceLine invoiceLine = invoice.getInvoiceLines().get(i);
					if(i == 0) {
						if(invoiceLine.getInvoiceLineID() == null) {
							long invoiceLineID = nummberGeneratorForInvoiceLine.getAndIncrement();
							invoiceLine.setInvoiceLineID(invoiceLineID);
						}
					}
					else {
						this.sortInvoiceLines(invoice.getInvoiceLines(),desc);
						System.out.println("invLines>"+invoice.getInvoiceLines());
						InvoiceLine invoiceLineObj = invoice.getInvoiceLines().get(0);
						if(invoiceLine.getInvoiceLineID() == null) {
							AtomicLong oldDBValue = new AtomicLong(invoiceLineObj.getInvoiceLineID());
							long invoiceLineID = oldDBValue.incrementAndGet();
							invoiceLine.setInvoiceLineID(invoiceLineID);
						}
					}
				}
				this.sortInvoiceLines(invoice.getInvoiceLines(),asc);
			}
		}
		else {
			if(invoice.getInvoiceLines() != null && invoice.getInvoiceLines().size()>0) {
				for(int i=0; i<invoice.getInvoiceLines().size();i++) {
					InvoiceLine invoiceLine = invoice.getInvoiceLines().get(i);
					if(invoiceLine.getLineID() == null || invoiceLine.getLineID().isEmpty()) {
						invoiceLine.setLineID(gen());
					}
				}
			}
		}

		invoice.setCreatedDateTime(LocalDateTime.now());
		invoice.setInvoiceStatus("Created");
		if(invoice.getInvoiceID() == null) {
			Invoice invoiceDbObj = invoiceRepository.findLatestInvoice(organizationIDName);
			if(invoiceDbObj != null) {
				AtomicLong oldDBValue = new AtomicLong(invoiceDbObj.getInvoiceID());
				long nextInvoiceID = oldDBValue.incrementAndGet();
				invoice.setInvoiceID(nextInvoiceID);
			}else{
				long invoiceID = nummberGeneratorForInvoice.getAndIncrement();
				invoice.setInvoiceID(invoiceID);
			}
		}

		invoiceObj = invoiceRepository.save(invoice);
		return invoiceObj;
	}

	public void sortInvoiceLines(List<InvoiceLine> invoiceLines,String sortOrder) {
		Collections.sort(invoiceLines, new Comparator<InvoiceLine>() {
			@Override
			public int compare(InvoiceLine line1, InvoiceLine line2) {
				int value = 0;
				Long id1 = line1.getInvoiceLineID();
				Long id2 = line2.getInvoiceLineID();
				 if (id1 == null && id2 == null) {
	                    return 0; // Both are null, consider them equal
	                } else if (id1 != null && id2 == null) {
	                    return -1; // id1 is non-null, consider it less than null id2
	                } else if (id1 == null && id2 != null) {
	                    return 1; // id2 is non-null, consider it greater than null id1
	                }
				 if(sortOrder != null) {
					 if(sortOrder.equals("ASC")) {
						 value = id1.compareTo(id2);
					 }
					 else if(sortOrder.equals("DESC")){
						 value = id2.compareTo(id1);
					 }
				 }
				return value;
			}
		});
	}

	public Invoice updateInvoice(Invoice invoice) {
		AtomicLong nummberGeneratorForInvoiceLine = new AtomicLong(101);
		final String desc = "DESC";
		final String asc = "ASC";
		Invoice invoiceObj = null;
		Invoice invoiceDbObj = this.findInvoiceById(invoice.get_id());
		List<InvoiceLine> existedInvoiceLines = null;
		if(invoiceDbObj != null) {
			existedInvoiceLines = invoiceDbObj.getInvoiceLines();
		}
		invoice.setLastUpdatedDateTime(LocalDateTime.now());

		List<InvoiceLine> mergedInvoiceLines = this.mergeInvoiceLines(existedInvoiceLines, invoice.getInvoiceLines());
		invoice.setInvoiceLines(mergedInvoiceLines);
		if(invoice.getInvoiceStatus() != null && invoice.getInvoiceStatus().equals("Confirmed")) {
			if(invoice.getInvoiceLines() != null && invoice.getInvoiceLines().size()>0) {
				for(int i=0; i<invoice.getInvoiceLines().size();i++) {
					InvoiceLine invoiceLine = invoice.getInvoiceLines().get(i);
					if(i == 0) {
						if(invoiceLine.getInvoiceLineID() == null) {
							long invoiceLineID = nummberGeneratorForInvoiceLine.getAndIncrement();
							invoiceLine.setInvoiceLineID(invoiceLineID);
						}
					}
					else {
						this.sortInvoiceLines(invoice.getInvoiceLines(),desc);
						System.out.println("invLines>"+invoice.getInvoiceLines());
						InvoiceLine invoiceLineObj = invoice.getInvoiceLines().get(0);
						if(invoiceLine.getInvoiceLineID() == null) {
							AtomicLong oldDBValue = new AtomicLong(invoiceLineObj.getInvoiceLineID());
							long invoiceLineID = oldDBValue.incrementAndGet();
							invoiceLine.setInvoiceLineID(invoiceLineID);
						}
					}
				}
				this.sortInvoiceLines(invoice.getInvoiceLines(),asc);
			}
		}
		invoiceObj = invoiceRepository.save(invoice);
		return invoiceObj;
	}

	private List<InvoiceLine> mergeInvoiceLines(List<InvoiceLine> existingLines, List<InvoiceLine> updatedLines) {
		final String desc = "DESC";
		final String asc = "ASC";
		List<InvoiceLine> mergedLines = null;
		if(existingLines != null && existingLines.size()>0 &&
				updatedLines != null && updatedLines.size()>0) {
			mergedLines = new ArrayList<>(existingLines);
			for (InvoiceLine updatedLine : updatedLines) {
				boolean lineUpdated = false;
				for (int i = 0; i < mergedLines.size(); i++) {
					InvoiceLine mergedLine = mergedLines.get(i);
					if (mergedLine.getLineID() != null && updatedLine.getLineID() != null && 
							mergedLine.getLineID().equals(updatedLine.getLineID())) {
						mergedLines.set(i, updatedLine);
						lineUpdated = true;
						break;
					}
				}
				if (!lineUpdated) {
					if(updatedLine.getLineID() == null || updatedLine.getLineID().isEmpty()) {
						updatedLine.setLineID(gen());
					}
					/*
					if(updatedLine.getLineID() == null) {
						//updatedLine.setCreatedDateTime(LocalDateTime.now());
						this.sortInvoiceLines(mergedLines,desc);
						InvoiceLine invoiceLineObj = mergedLines.get(0);
						AtomicLong oldDBValue = new AtomicLong(invoiceLineObj.getInvoiceLineID());
						long invoiceLineID = oldDBValue.incrementAndGet();
						updatedLine.setInvoiceLineID(invoiceLineID);
					}
					*/
					mergedLines.add(updatedLine);
				}
			}
		}
		return mergedLines;
	}

	/*
	private List<WareHouseTransferLine> mergeWareHouseTransferLines(List<WareHouseTransferLine> existingLines, List<WareHouseTransferLine> updatedLines) {
		List<WareHouseTransferLine> mergedLines = null;
		if(existingLines != null && existingLines.size()>0 &&
				updatedLines != null && updatedLines.size()>0) {
			mergedLines = new ArrayList<>(existingLines);
			for (WareHouseTransferLine updatedLine : updatedLines) {
				boolean lineUpdated = false;
				for (int i = 0; i < mergedLines.size(); i++) {
					WareHouseTransferLine mergedLine = mergedLines.get(i);
					if (mergedLine.get_id() != null && updatedLine.get_id() != null && 
							mergedLine.get_id().equals(updatedLine.get_id())) {
						if(mergedLine.getShipmentUnit() != null && updatedLine.getShipmentUnit() != null &&
								!mergedLine.getShipmentUnit().equals(updatedLine.getShipmentUnit())) {
							updatedLine.set_id(null);
							if(updatedLine.get_id() == null || updatedLine.get_id() == "") {
								updatedLine.set_id(gen());
							}
							updatedLine.setLastUpdatedDate(new Date());
							mergedLines.add(updatedLine);
							lineUpdated = true;
							break;
						}
						else if(mergedLine.getBrandName() != null && updatedLine.getBrandName() != null &&
								!mergedLine.getBrandName().equals(updatedLine.getBrandName())) {
									updatedLine.set_id(null);
									if(updatedLine.get_id() == null || updatedLine.get_id() == "") {
										updatedLine.set_id(gen());
									}
									updatedLine.setLastUpdatedDate(new Date());
									mergedLines.add(updatedLine);
									lineUpdated = true;
									break;
						}
						else if(((mergedLine.getBrandName() == null || mergedLine.getBrandName().isEmpty()) && updatedLine.getBrandName() != null) ||
								(mergedLine.getBrandName() != null && (updatedLine.getBrandName() == null || updatedLine.getBrandName().isEmpty()))) {
									updatedLine.set_id(null);
									if(updatedLine.get_id() == null || updatedLine.get_id() == "") {
										updatedLine.set_id(gen());
									}
									updatedLine.setLastUpdatedDate(new Date());
									mergedLines.add(updatedLine);
									lineUpdated = true;
									break;
						}
						else {
							mergedLines.set(i, updatedLine);
							lineUpdated = true;
							break;
						}
					}
				}
				if (!lineUpdated) {
					if(updatedLine.get_id() == null || updatedLine.get_id() == "") {
						updatedLine.setCreatedDate(new Date());
						updatedLine.set_id(gen());
					}
					mergedLines.add(updatedLine);
				}
			}
		}
		return mergedLines;
	}

	 */

	public List<Invoice> findAllInvoices(InvoiceFilter invoiceFilter) {
		List<Invoice> invoices = null;
		invoices = invoiceRepository.findInvoices(invoiceFilter);
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

	public Invoice deleteInvoiceById(String _id,String lineID,String organizationIDName) {
		Invoice invoiceObj = null;
		 invoiceObj = invoiceRepository.deleteByInvoiceId(_id, lineID, organizationIDName);
		return invoiceObj;
	}
	
	public InvoicePaginationResponse findAllInvoicesWithPagination(InvoiceFilter invoiceFilter) {
		List<Invoice> invoices = null;
		Page<Invoice> invoicePageList = null;
		InvoicePaginationResponse invoicePaginationResponse = new InvoicePaginationResponse();
		int page = invoiceFilter.getPage();
		int pageSize = invoiceFilter.getPageSize();
		Pageable pageable = of(page, pageSize);
		invoicePageList = invoiceRepository.findInvoicesWithPagination(invoiceFilter, pageable);
		if(invoicePageList != null) {
			invoices = invoicePageList.getContent();
			invoicePaginationResponse.setInvoices(invoices);
			invoicePaginationResponse.setTotalPages(invoicePageList.getTotalPages());
			invoicePaginationResponse.setTotalElements(invoicePageList.getTotalElements());
			invoicePaginationResponse.setNumber(addOneNumber(invoicePageList.getNumber()));
			invoicePaginationResponse.setNumberOfElements(invoicePageList.getNumberOfElements());
		}
	
		return invoicePaginationResponse;
	}
	
	public static Pageable of(int page, int size) {
		Pageable pageable = PageRequest.of(page-1, size);
		return pageable;
		// return new OneBasedPageRequest(page, size);
	}
	public static int addOneNumber(int input) {
		return input+1;
	}

}
