package com.org.telugucineandtvoutdoorunittechniciansunion.init;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.org.telugucineandtvoutdoorunittechniciansunion.pojo.NumberGeneration;
import com.org.telugucineandtvoutdoorunittechniciansunion.pojo.NumberGenerationPK;



@Repository
public class IdGenerator {
	@Autowired
	private DataAccess dataAccess;

	@Transactional
	public String get(String domainType, String colName) throws Exception {
		String unique = null;
		try {
			unique = getRugged(domainType, colName);
		} catch (Exception e) {
			ApplicationUtilities.error(this.getClass(),e.getMessage(),e);
		}

		return unique;
	}

	@Transactional
	public String getRugged(String domainType, String colName) throws Exception {
		String unique = null;

		unique = getUniqueString(domainType, colName);
		if (unique == null) {
			createUNACTEntry(domainType, colName);
			unique = getUniqueString(domainType, colName);
		}

		incrementUniqueid(domainType, colName);
		return unique;
	}

	@Transactional
	private String getUniqueString(String domainType, String colName) throws Exception {
		String rtn = null;
		String prefix = "";
		String padSize = "";
		String number = "";

		try {
			String query = "FROM NumberGeneration WHERE  type=:type AND columnName=:colName";
			Map<String, Object> map = new HashMap<String, Object>();

			map.put("type", domainType);
			map.put("colName", colName);

			List<NumberGeneration> list = this.dataAccess.queryWithParams(query, map);

			if (!list.isEmpty() && list.size() > 0) {
				NumberGeneration dalNumberGeneration = list.get(0);
				prefix = dalNumberGeneration.getNumberGenerationPK().getSeqTxt();
				padSize = String.valueOf(dalNumberGeneration.getSeqSize().intValue());
				number = String.valueOf(dalNumberGeneration.getNumberGenerationPK().getStrtUniqueSeq());
			} else {

				return (new Exception("Unique No Could not be generated")).getMessage();
			}

			prefix = (prefix == null || prefix.equals("null")) ? "" : prefix;
			if (prefix.contains("<<-") && prefix.contains("->>")) {
				prefix = prefix.replace("<<-", "");
				prefix = prefix.replace("->>", "");
			}

			int currentLength = prefix.length() + number.length();
			int totalLength = 0;
			if (padSize != null && !padSize.equals("null") && !padSize.equals("0") && !padSize.isEmpty()) {
				totalLength = Integer.parseInt(padSize);
				if (currentLength == totalLength && endOfSequence(number)) {
					updateEndOfSequence(domainType);
					rtn = padString(prefix, number, padSize, "0");
				} else {
					rtn = padString(prefix, number, padSize, "0");
				}
			} else {
				rtn = String.valueOf(String.valueOf(prefix)) + number;
			}

		} catch (Exception e) {
			ApplicationUtilities.error(this.getClass(),e.getMessage(),e);
		}

		return rtn;
	}

	@Transactional
	private void createUNACTEntry(String domainType, String colName) throws SQLException {
		try {
			NumberGeneration dalNumberGeneration = new NumberGeneration();
			NumberGenerationPK dalNumberGenerationId = new NumberGenerationPK();

			dalNumberGenerationId.setStrtUniqueSeq(new BigInteger("0"));
			dalNumberGeneration.setColumnName(colName);
			dalNumberGenerationId.setSeqTxt("");
			dalNumberGeneration.setNumberGenerationPK(dalNumberGenerationId);

			dalNumberGeneration.setSeqSize(new BigInteger("12"));
			dalNumberGeneration.setType(domainType);
			dalNumberGeneration.setEndUniqueSeq(new BigInteger("100000000"));
			dalNumberGeneration.setRegexpr("");
			dalNumberGeneration.setDescription("");
			this.dataAccess.save(dalNumberGeneration);
		} catch (Exception e) {
			ApplicationUtilities.error(this.getClass(),e.getMessage(),e);
		}
	}

	@Transactional
	public String padString(String pre, String suf, String padSize, String padString) {
		try {
			int finalTotal = Integer.parseInt(padSize);
			int beforePad = pre.length() + suf.length();
			for (int x = 0; x < finalTotal - beforePad; x++) {
				pre = pre.concat(padString);
			}
			return String.valueOf(String.valueOf(pre)) + suf;
		} catch (Exception ex) {
			ApplicationUtilities.error(this.getClass(),ex.getMessage(),ex);

			return String.valueOf(String.valueOf(pre)) + suf;
		}
	}

	@Transactional
	private void incrementUniqueid(String domainType, String colName) throws SQLException {
		try {
			String query = "UPDATE NumberGeneration SET numberGenerationPK.strtUniqueSeq =numberGenerationPK.strtUniqueSeq+1 WHERE  columnName =:colName AND TYPE =:domainType ";

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("colName", colName);
			map.put("domainType", domainType);

			this.dataAccess.updateQuery(query, map);
		} catch (Exception e) {
			ApplicationUtilities.error(this.getClass(),e.getMessage(),e);
		}
	}

	@Transactional
	private boolean endOfSequence(String suf) {
		while (suf.length() > 0) {
			if (!suf.endsWith("9")) {
				return false;
			}
			if (suf.length() == 1) {
				return true;
			}
			suf = suf.substring(0, suf.length() - 1);
		}
		return true;
	}

	@Transactional
	private void logError(Object message, Exception ex) {
		ApplicationUtilities.error(this.getClass(),ex.getMessage(),ex);
	}

	@Transactional
	private void updateEndOfSequence(String code) throws SQLException {
		String uniqueText = "";
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	}
}