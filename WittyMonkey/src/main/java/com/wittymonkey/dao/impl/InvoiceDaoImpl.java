package com.wittymonkey.dao.impl;

import org.springframework.stereotype.Repository;

import com.wittymonkey.dao.IInvoiceDao;
import com.wittymonkey.entity.Invoice;

@Repository(value="invoiceDao")
public class InvoiceDaoImpl extends GenericDaoImpl<Invoice> implements IInvoiceDao{

}
