package com.company.reports.controller;

import static com.company.reports.utils.InvoiceConstants.ENDPOINT_DOWNLOAD;
import static com.company.reports.utils.InvoiceConstants.ERROR_MSG_DOCUMENT_GENERATION;
import static com.company.reports.utils.InvoiceConstants.ERROR_MSG_DOCUMENT_NOT_FOUND;
import static com.company.reports.utils.InvoiceConstants.ERROR_MSG_DOWNLOAD_INVOICE;
import static com.company.reports.utils.InvoiceConstants.ERROR_MSG_INVALID_DISPLAY_FORMAT;
import static com.company.reports.utils.InvoiceConstants.INVOICES_BASE_URL;
import static com.company.reports.utils.InvoiceConstants.ROOT_ENDPOINT;
import static com.company.reports.utils.InvoiceConstants.SUCCESS_MSG_DOWNLOAD_INVOICE;
import static com.company.reports.utils.JasperReportConstants.DISPLAY_FORMAT_PDF;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.company.reports.exceptions.InvalidDisplayFormatException;
import com.company.reports.service.InvoiceService;
import com.company.reports.utils.InvoiceConstants;
import com.company.reports.utils.ResponseBuilder;
import com.company.reports.vo.InvoiceDataVo;

@RestController
@RequestMapping(INVOICES_BASE_URL)
public class InvoiceController {
	
	private static final Logger logger = LoggerFactory.getLogger(InvoiceController.class);

	@Autowired
    private InvoiceService invoiceService;
	
	@Autowired
	private ResponseBuilder responseBuilder;

    @PostMapping
    public ResponseEntity<?> generateInvoice(@RequestBody InvoiceDataVo invoiceData) {
    	 JSONObject responseObject = null;
        try {
            String docUuid = invoiceService.generateInvoice(invoiceData);
            responseObject= responseBuilder.buildSuccessResponse(docUuid, SUCCESS_MSG_DOWNLOAD_INVOICE);
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(responseObject.toString());
        } catch (Exception e) {
        	logger.error("Error: {}, Cause: {}", e.getMessage(), e.getCause());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ERROR_MSG_DOCUMENT_GENERATION);
        }
    }

    @GetMapping(ENDPOINT_DOWNLOAD)
    public ResponseEntity<?> downloadInvoice(@RequestParam String documentId, @RequestParam(required = false)String displayFormat) {
    	if(displayFormat== null || displayFormat.isBlank()) {
    		displayFormat = DISPLAY_FORMAT_PDF;
    	}
    	JSONObject responseObject = null;
        try {
            byte[]	fileBytes = invoiceService.getDocument(documentId, displayFormat);
            return responseBuilder.buildResponseEntity(displayFormat, fileBytes);
        }catch(InvalidDisplayFormatException e) {
        	logger.error("Error: {}, Cause: {}", e.getMessage(), e.getCause());
        	responseObject= responseBuilder.buildErrorResponse(ERROR_MSG_INVALID_DISPLAY_FORMAT);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObject.toString());
        }
        catch (FileNotFoundException e) {
			logger.error("Error: {}, Cause: {}", e.getMessage(), e.getCause());
			responseObject= responseBuilder.buildErrorResponse(ERROR_MSG_DOCUMENT_NOT_FOUND);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseObject.toString());
		} catch (IOException e) {
			logger.error("Error: {}, Cause: {}", e.getMessage(), e.getCause());
		}
        catch (Exception e) {
        	logger.error("Error: {}, Cause: {}", e.getMessage(), e.getCause());
        }
        responseObject= responseBuilder.buildErrorResponse(ERROR_MSG_DOWNLOAD_INVOICE);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseObject.toString());
    }
}
