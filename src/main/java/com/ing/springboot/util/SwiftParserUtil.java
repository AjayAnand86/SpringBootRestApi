/*
 * Copyright (c) http://www.prowidesoftware.com/, 2013. All rights reserved.
 */
package com.ing.springboot.util;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ing.springboot.model.MT940ParsedObject;
import com.ing.springboot.model.TransactionDetails;
import com.prowidesoftware.swift.io.parser.SwiftParser;
import com.prowidesoftware.swift.model.SwiftMessage;
import com.prowidesoftware.swift.model.field.Field20;
import com.prowidesoftware.swift.model.field.Field25;
import com.prowidesoftware.swift.model.field.Field60F;
import com.prowidesoftware.swift.model.field.Field61;
import com.prowidesoftware.swift.model.field.Field62F;
import com.prowidesoftware.swift.model.mt.mt9xx.MT940;

/**
 * Example of parsing an MT 940
 *
 * @author www.prowidesoftware.com
 */
@Component
public class SwiftParserUtil {
	
	@Autowired
	MT940ParsedObject mt940ParsedObject = new MT940ParsedObject();

/*public static void main(String[] args) throws IOException {
	
		
        
        // * The message we'll parse as a plain string for simplicity
         
        String mtString  = "{1:F01AAAABB99BSMK3513951576}{2:O9400934081223BBBBAA33XXXX03592332770812230834N}{4:\n:20:0112230000000890\n:25:SAKG800030155USD\n:28C:255/1\n:60F:C011223USD175768,92\n:61:0112201223CD110,92SALANONREF//08 IL053309/GB/2542049/SHS/312,\n:62F:C011021USD175879,84\n:20:NONREF\n:25:4001400010\n:28C:58/1\n:60F:C140327EUR6308,75\n:61:1403270327C3519,76FORX50RS201403240008//2014032100037666ABC DO BRASIL LTDA\n:86:INVOICE NR. 6000012801 \nORDPRTY : ABC DO BRASIL LTDA RUA LIBERO BADARO,293-SAO \nPAULO BRAZIL }";
        // 61 and 86 will repeat for n transactions
		createMT940Object(mtString);
		}*/

public MT940ParsedObject createMT940Object(String mtString) throws IOException {
	
	/*
	 * Create an instance of the SWIFT parser
	 */
	SwiftParser parser = new SwiftParser();
	/*
	 * feed the parser with the MT String we want to parse
	 */
	parser.setReader(new StringReader(mtString));
	/*
	 * Actually parse the file and create a java object model from the
	 * message
	 */
	SwiftMessage msg = parser.message();
	/*
	 * msg contains java object from parsed message. Printout several parts
	 * of the message's content.
	 */

	MT940 mt = new MT940(msg);
	System.out.println("Sender: " + mt.getSender());
	mt940ParsedObject.setSender(mt.getSender());
	System.out.println("Receiver: " + mt.getReceiver());
	mt940ParsedObject.setReceiver(mt.getReceiver());
	System.out.println("Reference: " + mt.getField20().getValue());
	Field20 f = mt.getField20();
	System.out.println("Field 20 Reference: " + f.getReference());
	List<Field61> field61 = mt.getField61();
	Field60F field60f = mt.getField60F();
	String openingAmount = field60f.getAmount();
	String currency = field60f.getCurrency();
	System.out.println("Opening Balance : " + openingAmount);
	mt940ParsedObject.setOpeningAmount(openingAmount);
	System.out.println("Currency : " + currency);
	mt940ParsedObject.setCurrency(currency);
	Field25 field25 = mt.getField25();
	System.out.println(mt.getField25());
	mt940ParsedObject.setAccountNumber(field25.getAccount());
	Field62F field62f = mt.getField62F();
	String closingAmount = field62f.getAmount();
	String closingCurrency = field62f.getCurrency();
	System.out.println("Closing Balance : " + closingAmount);
	System.out.println("Closing Currency : " + closingCurrency);
	mt940ParsedObject.setClosingAmount(closingAmount);
	List<TransactionDetails> details = mt940ParsedObject.getTransactionDetails();
	for(Field61 f61 : field61){
		TransactionDetails transactionDetails = new TransactionDetails();
		System.out.println("Transaction Amount : " + f61.getAmount());
		transactionDetails.setTransactionAmount(f61.getAmount());
		System.out.println("Transaction Type : " + f61.getTransactionType());
		transactionDetails.setTransactionType(f61.getTransactionType());
		System.out.println("Component : " + f61);
		details.add(transactionDetails);
	}
	return mt940ParsedObject;
}
}