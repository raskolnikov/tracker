package com.m2yazilim.tracker.model;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;


@XStreamAlias("info")
public class Info implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String gsm_no;
	private String is_single_invoice_sub;
	private String single_invoice_no;
	private String is_single_invoice;
	
	private String invoice_no;
	private String invoice_date;
	private String invoice_period;
	private String due_date;
	private String invoice_amount;
	private String due_amount;
	private String value_vat_excluded;
	private String invoice_status;
	private String payment_date;
	private String payment_source;
	
	

	public Info() {
		
	}

	public String getGsm_no() {
		return gsm_no;
	}



	public void setGsm_no(String gsmNo) {
		gsm_no = gsmNo;
	}



	public String getIs_single_invoice_sub() {
		return is_single_invoice_sub;
	}



	public void setIs_single_invoice_sub(String isSingleInvoiceSub) {
		is_single_invoice_sub = isSingleInvoiceSub;
	}



	public String getSingle_invoice_no() {
		return single_invoice_no;
	}



	public void setSingle_invoice_no(String singleInvoiceNo) {
		single_invoice_no = singleInvoiceNo;
	}



	public String getIs_single_invoice() {
		return is_single_invoice;
	}



	public void setIs_single_invoice(String isSingleInvoice) {
		is_single_invoice = isSingleInvoice;
	}

	public String getInvoice_no() {
		return invoice_no;
	}

	public void setInvoice_no(String invoiceNo) {
		invoice_no = invoiceNo;
	}

	public String getInvoice_period() {
		return invoice_period;
	}

	public void setInvoice_period(String invoicePeriod) {
		invoice_period = invoicePeriod;
	}

	public String getValue_vat_excluded() {
		return value_vat_excluded;
	}

	public void setValue_vat_excluded(String valueVatExcluded) {
		value_vat_excluded = valueVatExcluded;
	}

	public String getInvoice_status() {
		return invoice_status;
	}

	public void setInvoice_status(String invoiceStatus) {
		invoice_status = invoiceStatus;
	}

	public String getPayment_date() {
		return payment_date;
	}

	public void setPayment_date(String paymentDate) {
		payment_date = paymentDate;
	}

	public String getPayment_source() {
		return payment_source;
	}

	public void setPayment_source(String paymentSource) {
		payment_source = paymentSource;
	}

	public String getInvoice_date() {
		return invoice_date;
	}

	public void setInvoice_date(String invoiceDate) {
		invoice_date = invoiceDate;
	}

	public String getDue_date() {
		return due_date;
	}

	public void setDue_date(String dueDate) {
		due_date = dueDate;
	}

	public String getInvoice_amount() {
		return invoice_amount;
	}

	public void setInvoice_amount(String invoiceAmount) {
		invoice_amount = invoiceAmount;
	}

	public String getDue_amount() {
		return due_amount;
	}

	public void setDue_amount(String dueAmount) {
		due_amount = dueAmount;
	}
	
}
