/*
 * Copyright (c) http://www.prowidesoftware.com/, 2013. All rights reserved.
 */
package com.ing.springboot.util;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import com.prowidesoftware.swift.io.parser.SwiftParser;
import com.prowidesoftware.swift.model.SwiftMessage;
import com.prowidesoftware.swift.model.field.Field20;
import com.prowidesoftware.swift.model.field.Field60F;
import com.prowidesoftware.swift.model.field.Field61;
import com.prowidesoftware.swift.model.field.Field62F;
import com.prowidesoftware.swift.model.mt.mt9xx.MT940;

/**
 * Example of parsing an MT 940
 *
 * @author www.prowidesoftware.com
 */
public class SwiftParserUtil {

public static void main(String[] args) throws IOException {
	
		
        /*
         * The message we'll parse as a plain string for simplicity
         */
        String mtString  = "{1:F01AAAABB99BSMK3513951576}"+
                "{2:O9400934081223BBBBAA33XXXX03592332770812230834N}" +
                "{4:\n"+
                ":20:0112230000000890\n"+      											//transaction reference number
                ":25:SAKG800030155USD\n"+   											//Account identification
                ":28C:255/1\n"+															//Statement number , sequence number
                ":60F:C011223USD175768,92\n"+											//opening balance credit/debit, date, amount
                ":61:0112201223CD110,92NDIVNONREF//08 IL053309/GB/2542049/SHS/312,\n"+  //details of transaction, value date
                																		//Credit/debit/fundscode/amount/
                
                ":62F:C011021USD175879,84\n"+											//closing balance details
                ":20:NONREF\n" +
                ":25:4001400010\n" +
                ":28C:58/1\n" +
                ":60F:C140327EUR6308,75\n" +
                ":61:1403270327C3519,76NTRF50RS201403240008//2014032100037666ABC DO BRASIL LTDA\n" +
                ":86:INVOICE NR. 6000012801 \n" +
                "ORDPRTY : ABC DO BRASIL LTDA RUA LIBERO BADARO,293-SAO \n" +
                "PAULO BRAZIL }";
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
		System.out.println("Receiver: " + mt.getReceiver());
		System.out.println("Reference: " + mt.getField20().getValue());
		Field20 f = mt.getField20();
		System.out.println("Field 20 Reference: " + f.getReference());
		List<Field61> field61 = mt.getField61();
		Field60F field60f = mt.getField60F();
		String openingAmount = field60f.getAmount();
		String currency = field60f.getCurrency();
		System.out.println("Opening Balance : " + openingAmount);
		System.out.println("Currency : " + currency);
		
		Field62F field62f = mt.getField62F();
		String closingAmount = field62f.getAmount();
		String closingCurrency = field62f.getCurrency();
		System.out.println("Opening Balance : " + closingAmount);
		System.out.println("Closing Currency : " + closingCurrency);
		
		for(Field61 f61 : field61){
			System.out.println("Transaction Amount : " + f61.getAmount());
			System.out.println("Component : " + f61);
		}
		}
}