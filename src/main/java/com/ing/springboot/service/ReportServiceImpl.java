package com.ing.springboot.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ing.springboot.handler.BankHandler;
import com.ing.springboot.model.BankAccountDetail;
import com.ing.springboot.model.MT940ParsedObject;
import com.ing.springboot.util.ReportUtils;
import com.ing.springboot.util.SwiftParserUtil;

@Service("reportService")
public class ReportServiceImpl implements ReportService {
	public static final Logger logger = LoggerFactory.getLogger(ReportService.class);

	@Autowired
	SwiftParserUtil swiftParserUtil;

	public List<BankAccountDetail> getAllAccountPercentShare() {
		List<BankAccountDetail> bankAccountDetailList = new ArrayList<>();

		String[] bankIds = { "b1", "b2", "b3" };
		for (String bankid : bankIds) {
			// CALL REST SERVICE TO GET MT940 MESSAGES FOR BANKS
			String mt940 = BankHandler.getDataForBank(bankid);
			// PARSE RECEIVED DATA
			MT940ParsedObject mt940Object = null;
			try {
				mt940Object = swiftParserUtil.createMT940Object(mt940);
			} catch (IOException e) {
				e.printStackTrace();
			}

			BankAccountDetail b = new BankAccountDetail();
			b.setAccountNumber(mt940Object.getAccountNumber());
			b.setBankName(bankid);
			if (mt940Object != null && mt940Object.getClosingAmount() != null)
				b.setClosingBalance(Double.parseDouble(mt940Object.getClosingAmount().replace(",", ".")));
			bankAccountDetailList.add(b);
		}
		bankAccountDetailList = ReportUtils.calculatePercentShare(bankAccountDetailList);
		return bankAccountDetailList;
	}

	@Override
	public BankAccountDetail bankAccountTrancationDetails(String bankId) {
		// TODO Auto-generated method stub
		return null;
	}

}