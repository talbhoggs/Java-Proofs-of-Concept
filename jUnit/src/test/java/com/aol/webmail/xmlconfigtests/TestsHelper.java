package com.aol.webmail.xmlconfigtests;

import org.slf4j.LoggerFactory;

import com.aol.webmail.xmlconfig.ConfigurationGetters;
import org.slf4j.*;

public class TestsHelper {
	
	private static Logger logger = LoggerFactory
			.getLogger(com.aol.webmail.xmlconfigtests.TestsHelper.class);
	
	public static Boolean testStr(ConfigurationGetters config, String key,
			String expectVal) {
		String result = config.getString(key, "i18n:");
		result += (result.equals(expectVal)) ? "  :PASS" : "   :**FAIL";
		// System.out.println(result);
		if (result.contains("PASS")) {
			return true;
		} else {
			logger.error("Expected: " + expectVal + "Actual: " + result);
			return false;
		}
	}

	public static Boolean testStrVal(ConfigurationGetters config, String comp,
			String key, String attribute, String defaultVal, String expectVal) {
		String result = config.getValue(comp, key, attribute, defaultVal);
		// System.out.println("The actual result is: " + result);
		result += (result.equals(expectVal)) ? "  :PASS" : "   :**FAIL";
		// System.out.println(result);
		if (result.contains("PASS")) {
			return true;
		} else {
			logger.error("Expected: " + expectVal + "Actual: " + result);
			return false;
		}
	}

	public static Boolean testBoolVal(ConfigurationGetters config, String comp,
			String key, String attribute, boolean defaultVal, boolean expectVal) {
		boolean bVal = config.getValueBoolean(comp, key, attribute, defaultVal);
		String result = Boolean.toString(bVal);
		result += (bVal == expectVal) ? "  :PASS" : "   :**FAIL";
		// System.out.println(result);
		if (result.contains("PASS")) {
			return true;
		} else {
			logger.error("Key: " + key + " Expected: " + expectVal + "Actual: " + result);
			return false;
		}
	}

	public static Boolean testIntVal(ConfigurationGetters config, String comp,
			String key, String attribute, int defaultVal, int expectVal) {
		int iVal = config.getValueInt(comp, key, attribute, defaultVal);
		String result = Integer.toString(iVal);
		result += (iVal == expectVal) ? "  :PASS" : "   :**FAIL";
		// System.out.println(result);
		if (result.contains("PASS")) {
			return true;
		} else {
			logger.error("Expected: " + expectVal + "Actual: " + result);
			return false;
		}
	}

	public static Boolean testStrArrVals(ConfigurationGetters config,
			String comp, String key, String attribute, String defaultVal,
			String[] expectVals) {
		String result = null;
		String tempResult = null;
		String[] vals = config.GetValueArray(comp, key, attribute, null,
				defaultVal);
		logger.info(key+"/"+attribute+" Actual length: " + vals.length + " Expected length: " + expectVals.length);
		for (int i = 0; i < vals.length; i++) {
			// System.out.print("\""+vals[i]+"\",");
			/* */
			if (i < expectVals.length) {
				tempResult = vals[i];
				result += (tempResult.equals(expectVals[i])) ? "  :PASS"
						: "   :**FAIL";
				// System.out.println(result);
				if(!tempResult.equals(expectVals[i])) {
					logger.error("testStrArrVals - expected: " + expectVals[i] + ", actual: " + tempResult);
				}
			} else {
				result +="FAIL";
				logger.error("testStrArrVals: More Array Elem than expected");
			}

			/**/
		}

		if (result.contains("FAIL")) {
			return false;
		} else {
			return true;
		}

	}
}
