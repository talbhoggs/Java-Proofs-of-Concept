package com.aol.webmail.xmlconfigtests;

import com.aol.webmail.xmlconfig.*;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.*;
import org.junit.runners.*;
import org.junit.internal.runners.*;
import org.springframework.test.*;
import org.springframework.test.annotation.*;
import org.springframework.test.context.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

import org.xml.sax.SAXException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml" })
public class TestXmlConfig {

	private static Logger logger;
	@Value("${enc.pwd}")
	private String testme;

	@Autowired
	private WmCfg wmCfg;

	// @Autowired
	// private static ApplicationContext ctx;

	public TestXmlConfig() {

	}

	@BeforeClass
	public static void setUpClass() {
		logger = LoggerFactory
				.getLogger(com.aol.webmail.xmlconfigtests.TestXmlConfig.class);
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {

	}

	@After
	public void tearDown() {
	}

	// TODO add test methods here.
	// The methods must be annotated with annotation @Test. For example:
	//
	@Test
	public void testXMLSyntax() {
		
		boolean thrownSAXException = false;

		try {
			ReadXMLFiles.initConfig();
		} catch (SAXException e) {
			thrownSAXException = true;
		}
		
		if(!thrownSAXException) logger.info("*** No XML syntax error has been detected. ***");

		assertFalse(thrownSAXException);

	}
	
	@Test
	public void testStrings_EN_US() {

		WmCfgCriteria crit = new WmCfgCriteria("en-us", "aol");
		ConfigurationGetters config = new ConfigurationGetters(crit);
		ConfigurationGetters userStrings = config;
		String result1 = userStrings.getString("MobileProductName", null);
		System.out.println("Result 1: " + result1);
		String result2 = userStrings.getString("ManillaPoweredBy", null);
		System.out.println("Result 2: " + result2);
		if (result1.equals("AOL Mobile")
				&& result2.equals("powered by Manilla")) {
			logger.info("testStrings_EN_US passed.");
			assertTrue(true);
		} else {
			logger.error("testString_EN_US failed.\nExpected: AOL Mobile, Acutal: "
					+ result1
					+ "\nExpected: powered by Manilla, Actual: "
					+ result2);
			assertTrue(false);

		}

	}

	@Test
	public void testStrings_EN_US_with_brand() { // WmCfg.initConfig();
		WmCfgCriteria crit = new WmCfgCriteria("en-us", "icqmail_com");
		ConfigurationGetters config = new ConfigurationGetters(crit);
		ConfigurationGetters userStrings = config;
		String result1 = userStrings.getString("MobileProductName", null); //
		System.out.println("Result 1: " + result1);
		String result2 = userStrings.getString("LinkedScreenNamesText2", null); //
		System.out.println("Result 2: " + result2);
		if (result1.equals("ICQ Mobile")
				&& result2.equals("Link your ICQ number or AIM screen name")) {
			logger.info("testStrings_EN_US_with_brand passed.");
			assertTrue(true);
		} else {
			logger.error("testString_EN_US_with_brand failed.\nExpected: ICQ Mobile, Acutal: "
					+ result1
					+ "\nExpected: Link your ICQ number or AIM screen name, Actual: "
					+ result2);
			assertTrue(false);
		}

	}

	@Test
	public void testStrings_FR_FR() { // WmCfg.initConfig();
		WmCfgCriteria crit = new WmCfgCriteria("fr-fr", "aol");
		ConfigurationGetters config = new ConfigurationGetters(crit);
		ConfigurationGetters userStrings = config;
		String result1 = userStrings.getString("Error", null);
		// System.out.println("Result 1: "+ result1);
		String result2 = userStrings.getString("ComposeButton", null);
		// System.out.println("Result 2: " + result2);
		if (result1.equals("Erreur") && result2.equals("Ecrire")) {
			logger.info("testStrings_FR_FR passed.");
			assertTrue(true);
		} else {
			logger.error("testString_FR_FR failed.\nExpected: Erreur, Actual: "
					+ result1 + "\nExpected: Ecrire, Actual: " + result2);
			assertTrue(false);
		}

	}

	@Test
	public void additionalStringTests() {

		WmCfgCriteria crit = new WmCfgCriteria("en-us", "aol");
		ConfigurationGetters config = new ConfigurationGetters(crit);
		ConfigurationGetters userStrings = config;

		ArrayList<Boolean> results = new ArrayList<Boolean>();
		Boolean result = null;

		result = TestsHelper.testStr(userStrings, "MobileProductName",
				"AOL Mobile");
		results.add(result);
		result = TestsHelper.testStr(userStrings, "WlcmDlg.StartBtn",
				"GET STARTED");
		results.add(result);
		result = TestsHelper.testStr(userStrings, "TodayOnAOL", "Today on AOL");
		results.add(result);

		crit.updateCriteria("en-us", "aim");
		result = TestsHelper.testStr(userStrings, "TodayOnAOL", "Today on AOL");
		results.add(result);

		crit.updateCriteria("en-us", "wmconnect_com");
		result = TestsHelper.testStr(userStrings, "TodayOnAOL",
				"Today's Features");
		results.add(result);
		crit.updateCriteria("de-de", "aol");
		result = TestsHelper
				.testStr(userStrings, "TodayOnAOL", "Heute bei AOL");
		results.add(result);
		result = TestsHelper.testStr(userStrings, "MobileProductName",
				"AOL Mobil");
		results.add(result);
		crit.updateCriteria("de-de", "aim");
		result = TestsHelper
				.testStr(userStrings, "TodayOnAOL", "Heute bei AOL");
		results.add(result);
		result = TestsHelper.testStr(userStrings, "WlcmDlg.StartBtn",
				"JETZT STARTEN");
		results.add(result);

		if (results.contains(false)) {
			logger.error("additionalStringTest failed.");
			assertTrue(false);
		} else {
			logger.info("additionalStringTests passed.");
			assertTrue(true);
		}

	}

	@Test
	public void testConfig_String_Return_Value() {

		WmCfgCriteria crit = new WmCfgCriteria("en-us", "aol");
		ConfigurationGetters config = new ConfigurationGetters(crit);
		ConfigurationGetters userStrings = config;

		ArrayList<Boolean> results = new ArrayList<Boolean>();
		Boolean result = null;

		result = TestsHelper.testStrVal(config, "WebApp", "NOEXIST", "value",
				"NOEXIST", "NOEXIST");
		results.add(result);

		result = TestsHelper.testStrVal(config, "DateStrings", "CalDateFormat",
				"value", "TestDefault", "MM/dd/yyyy");
		results.add(result);

		result = TestsHelper.testStrVal(config, "WebApp",
				"DefaultDoolittleURL", "value", "TestDefault",
				"http://doolittle.aol.com");
		results.add(result);
		result = TestsHelper.testStrVal(config, "WebApp", "ManillaHost",
				"value", "TestDefault", "app.manilla.com");
		results.add(result);

		result = TestsHelper.testStrVal(config, "Logging", "LogPath", "value",
				"defpath", "d:\\WebSuite\\Logs\\mailblocks.log");
		results.add(result);

		result = TestsHelper.testStrVal(config, "SMTPMailSender",
				"ConnectionLogFile", "value", "defpath",
				"d:\\WebSuite\\Logs\\MailSender.clog");
		results.add(result);
		result = TestsHelper.testStrVal(config, "WebApp", "dojo", "xdomain",
				"default", "true");
		results.add(result);
		result = TestsHelper
				.testStrVal(config, "IDP", "IDPTokenValidationUrl", "value",
						"TestDefault",
						" https://login.aol.com/idp/api/validate?oauth_consumer_key=mail.aol.com");
		results.add(result);
		result = TestsHelper.testStrVal(config, "WebAppStatic",
				"FixDomainlessAddresses", "value", "TestDefault",
				"aol.com,aim.com");
		results.add(result);
		result = TestsHelper.testStrVal(config, "AOLCalendar",
				"CalendarServer", "value", "TestDefault",
				"calendar.wcap.aol.com");
		results.add(result);
		result = TestsHelper.testStrVal(config, "WebApp", "SupportSiteURL",
				"value", "TestDefault",
				"http://help.aol.com/help/product/aol_webmail/");
		results.add(result);

		crit.updateCriteria("en-us", "kol");
		result = TestsHelper.testStrVal(config, "WebApp", "SupportSiteURL",
				"value", "TestDefault",
				"http://help.aol.com/help/product/aol_webmail/");
		results.add(result);

		crit.updateCriteria("en-us", "wmconnect_com");
		result = TestsHelper.testStrVal(config, "WebApp", "SupportSiteURL",
				"value", "TestDefault",
				"http://www.wmconnect.com/membercenter/");
		results.add(result);

		crit.updateCriteria("de-de", "aol");
		result = TestsHelper.testStrVal(config, "DateStrings", "CalDateFormat",
				"value", "TestDefault", "dd/MM/yyyy");
		results.add(result);

		crit.updateCriteria("de-de", "aim");
		result = TestsHelper.testStrVal(config, "WebApp", "SupportSiteURL",
				"value", "TestDefault",
				"http://hilfe.aol.de/template/DEHilfe05");
		results.add(result);

		if (results.contains(false)) {
			logger.error("testConfig_String_Return_Value failed.");
			assertTrue(false);
		} else {
			logger.info("testConfig_String_Return_Value passed.");
			assertTrue(true);
		}

	}

	@Test
	public void testConfig_Int_Return_Value() {

		ArrayList<Boolean> results = new ArrayList<Boolean>();
		Boolean result = null;

		WmCfgCriteria crit = new WmCfgCriteria("en-us", "aol");
		ConfigurationGetters config = new ConfigurationGetters(crit);
		result = TestsHelper.testIntVal(config, "Proxy",
				"MapQuestProxyClientId", "value", -1, 38030);
		results.add(result);

		if (results.contains(false)) {
			logger.error("testConfig_Int_Return_Value failed.");
			assertTrue(false);
		} else {
			logger.info("testConfig_Int_Return_Value passed.");
			assertTrue(true);
		}
	}

	@Test
	public void testConfig_Boolean_Return_Value() {

		WmCfgCriteria crit = new WmCfgCriteria("en-us", "aol");
		ConfigurationGetters config = new ConfigurationGetters(crit);

		ArrayList<Boolean> results = new ArrayList<Boolean>();
		Boolean result = null;

		result = TestsHelper.testBoolVal(config, "WebApp", "dojo", "custom",
				false, true);
		results.add(result);
		result = TestsHelper.testBoolVal(config, "AOLUserPreferences",
				"UseMailControl", "value", true, true);
		results.add(result);
		result = TestsHelper.testBoolVal(config, "AOLUserPreferences",
				"DefaultSpellCheckBeforeSend", "value", true, false);
		results.add(result);
		result = TestsHelper.testBoolVal(config, "Presence", "ShowMobileIcon",
				"value", false, true);
		results.add(result);
		result = TestsHelper.testBoolVal(config, "WebApp",
				"AccessibleTodayContentAvailable", "value", false, true);
		results.add(result);

		if (results.contains(false)) {
			logger.error("testConfig_Boolean_Return_Value failed");
			assertTrue(false);
		} else {
			logger.info("testConfig_Boolean_Return_Value passed");
			assertTrue(true);
		}
	}

	@Test
	public void testConfig_Array_Result() {

		WmCfgCriteria crit = new WmCfgCriteria("en-us", "aol");
		ConfigurationGetters config = new ConfigurationGetters(crit);

		ArrayList<Boolean> results = new ArrayList<Boolean>();
		Boolean result = null;

		String[] expVals = { "MyAccount", "MyHome", "", "Settings", "Themes",
				"", "Feedback", "Help", "MailBlogLink", "ResetBubblePins",
				"ErrorConsole", "", "Basic", "Accessible", "Toolbar", "",
				"TOS", "PrivacyPolicy", "AboutAds" };

		result = TestsHelper.testStrArrVals(config, "WebApp", "MegaMenuLinks",
				"id", "", expVals);
		results.add(result);

		String[] expVals1 = {
				"https://account.aol.com?locale={0}_{1}",
				"http://www.aol.com/?molhp=txtlnkusaolp00000406&ncid=whl-aolcom",
				"",
				"",
				"",
				"",
				"",
				"http://help.aol.com/help/product/aol_mail/",
				"http://mailblog.aol.com",
				"",
				"",
				"",
				"",
				"",
				"http://toolbar.aol.com/mail/download.html?ncid=txtlnkusdown00000003",
				"", "http://legal.aol.com/TOS", "http://privacy.aol.com",
				"http://adinfo.aol.com/about-our-ads" };

		result = TestsHelper.testStrArrVals(config, "WebApp", "MegaMenuLinks",
				"url", "", expVals1);
		results.add(result);

		if (results.contains(false)) {
			logger.error("testConfig_Array_Result failed.");
			assertTrue(false);
		} else {
			logger.info("testConfig_Array_Result passed.");
			assertTrue(true);
		}

	}

	@Test
	public void testAppliesTo() {

		// <AppliesTo Locale="en-gb" BaseUIVariant="AOL">
		// <Setting name="SendConfPageIsAdMagic"
		// value="100017948|100022704|1200x800,800x400"/>
		// </AppliesTo>
		
		WmCfgCriteria crit = new WmCfgCriteria("en-gb", "AOL");
		ConfigurationGetters config = new ConfigurationGetters(crit);
		ConfigurationGetters userStrings = config;

		String expectVal = "100017948|100022704|1200x800,800x400";
		Boolean testResult = TestsHelper.testStrVal(config, "WebApp",
				"SendConfPageIsAdMagic", "value", null, expectVal);
		if (testResult) {
			logger.info("testAppliesTo passed.");
			assertTrue(true);
		} else {
			logger.error("testAppliesTo failed.");
			assertTrue(false);
		}
	}
	
}
