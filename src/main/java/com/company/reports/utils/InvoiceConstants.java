package com.company.reports.utils;

public class InvoiceConstants {
	
	public static final String INVOICES_BASE_URL = "/invoices";
    public static final String ENDPOINT_GENERATE = "/generate";
    public static final String ENDPOINT_DOWNLOAD = "/download";
    public static final String ROOT_ENDPOINT 	 = "/";
    
    
    public static final String DISPLAY_FORMAT_DIRECTORY_PDF = "/pdf/";
    public static final String DISPLAY_FORMAT_DIRECTORY_XLSX = "/xlsx/";
    public static final String DISPLAY_FORMAT_DIRECTORY_DOCX = "/docx/";
    public static final String DISPLAY_FORMAT_DIRECTORY_CSV = "/csv/";
    public static final String DISPLAY_FORMAT_DIRECTORY_HTML = "/html/";
    
    public static final String SUCCESS_MSG_DOWNLOAD_INVOICE ="Your report is ready to download!";
	public static final String ERROR_MSG_DOWNLOAD_INVOICE = "Oops! We're having trouble processing your request. It seems like we're unable to read the invoice data internally. Please try again later or contact our support team for assistance.";
	public static final String ERROR_MSG_DOCUMENT_NOT_FOUND = "File not found, Could you check your documentId and try again?";
	public static final String ERROR_MSG_DOCUMENT_GENERATION ="Error generating invoice";
	public static final String ERROR_MSG_INVALID_DISPLAY_FORMAT = "Invalid display format. Supported formats are PDF, HTML, XLS, XLSX, and CSV.";
	public static final String HEADER_CONTENT_DISPOSITION_INVOICE ="attachment; filename=\"invoice.pdf\"";

}
