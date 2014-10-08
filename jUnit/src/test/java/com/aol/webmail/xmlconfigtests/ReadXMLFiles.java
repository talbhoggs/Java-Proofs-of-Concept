package com.aol.webmail.xmlconfigtests;

import com.aol.webmail.xmlconfig.ConfigurationGetters;
import com.aol.webmail.xmlconfig.SettingValue;
import com.aol.webmail.xmlconfig.SettingValueMultiAttribute;
import com.aol.webmail.xmlconfig.WmCfg;
import com.aol.webmail.xmlconfig.WmCfgCriteria;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReadXMLFiles {

    private static String machineGroupCriteria = ""; // Envirnment criteria does not change in the server lifttime
    private static final String stringsComp = "Strings";
    private static final String configRootDir = "/webmailConfig/";
    private static final String resRootDir = "/webmailResources/";
    private static int cnt = 0;
    private static Logger logger = LoggerFactory
			.getLogger(com.aol.webmail.xmlconfigtests.ReadXMLFiles.class);

    // directories in res dir to search for 
	/* The zeroeth item of each array is the locale dir, 1-N items of each array are the brands driectories that have Strings or OverrideConfig XML s*/
    private static final String[][] ResDirs = {
        {"core", "icqmail_com", "kol"},
        {"de-de", "aim", "aol", "icqmail_com", "kol"},
        {"en-ca", "aol"},
        {"en-gb", "aim", "aol", "kol", "talktalk"},
        {"en-in", "aol"},
        {"en-us", "aol", "cs_com", "icqmail_com", "kol", "wmconnect_com"},
        {"es-us", "aol"},
        {"fr-ca", "aol"},
        {"fr-fr", "aim", "aol", "kol"},
        {"ja-jp"},
        {"pt-br", "aol"}
    };

    // The config files have a lot of applies to to brand and locales no longer in use, use only these so hash maps are not bloated with unused settings
    public static final String supportedLocales = "de-de,en-ca,en-gb,en-in,en-us,es-us,fr-ca,fr-fr,ja-jp,pt-br";
    public static final String supportedBrands = "aol,aim,kol,cs_com,icqmail_com,wmconnect_com,talktalk";

    private static boolean startupComplete = false;
    private static HashMap<String, SettingValue> baseConfig = new HashMap<String, SettingValue>();
    private static HashMap<String, HashMap> criteriaIndexedConfigs = new HashMap<String, HashMap>();

    public static void initConfig() throws SAXException {
        class XmlCfgHndlr extends org.xml.sax.helpers.DefaultHandler {

        	private Logger logger = LoggerFactory
        			.getLogger(XmlCfgHndlr.class);

        	
            XmlCfgHndlr(WmCfgCriteria criteria, String rootDir, boolean isBaseConfig) {
                this.criteria = criteria;
                this.isBaseConfig = isBaseConfig;
                this.rootDir = rootDir;
            }

            WmCfgCriteria criteria = null;
            String rootDir = "";

            // Strings 
            boolean isBaseConfig = false; //

            String componentName = ""; // keep componentName name between the component begin end tag, component is now just scoping prefix to the key in criteriaIndexedConfigs and baseConfig
            boolean inSettings = false; // flag to keep track of when between Settings begin end tag
            boolean inAppliesto = false; // flag to keep track of when between AppliesTo begin end tag
            boolean skippingApplied = false; // if appliesTo is machine group and does not match current env, skip over those settings
            int settingValueIndex = -1; // for setting value arrays, keep the index to store settings as each settingValue tag is hit
            SettingValue currentSettingValue = null; // for setting value arrays, create this on first settingValue tag and store subsequent settingValue at settingValueIndex
            AttributesImpl currentSettingAttributes = null; // Use to copy the Attributes passed to begin tag (a reference that is stale atfter each new tag), for use in end tag 
            Attributes currentSettingValueAttributes = null; // For settingValue, just keep the reference from begin to end, as the have no childen

            String currentlyReading;

            @SuppressWarnings("unchecked")
            private HashMap<String, SettingValue> castMap(Object obj) {
                return (HashMap<String, SettingValue>) obj;
            }

            // When settings from the Res dir, the key is unique based dir, either locale or locale-brand
            private void addResDirSettingToCriteriaIndexedConfigs(String key) {
                HashMap<String, SettingValue> criteriaConfig = null;
                if (criteriaIndexedConfigs.containsKey(criteria.getCritKey())) {
                    criteriaConfig = castMap(criteriaIndexedConfigs.get(criteria.getCritKey()));
                } else {
                    criteriaConfig = new HashMap<String, SettingValue>();
                    criteriaIndexedConfigs.put(criteria.getCritKey(), criteriaConfig);
                }
                criteriaConfig.put(key, currentSettingValue);
            }

            // Settings in AppliesTo are stored in each of the list returned by criteria based on combination of conditions specified
            private boolean addAppliedSettingToCriteriaIndexedConfigs(String key) {
                HashMap<String, SettingValue> criteriaConfig = null;
                String critKey = null;
                ArrayList<String> appliedCriteriaKeys = criteria.getListOfAppliedCriteriaKeys();
                if (appliedCriteriaKeys == null) { // no criteria, return false to indicate its matching machine group and needs to go in base
                    return false;
                }
                for (int c = 0; c < appliedCriteriaKeys.size(); c++) {
                    critKey = appliedCriteriaKeys.get(c);
                    if (criteriaIndexedConfigs.containsKey(critKey)) {
                        criteriaConfig = castMap(criteriaIndexedConfigs.get(critKey));
                    } else {
                        criteriaConfig = new HashMap<String, SettingValue>();
                        criteriaIndexedConfigs.put(critKey, criteriaConfig);
                    }
                    criteriaConfig.put(key, currentSettingValue);
                }
                return true;
            }

            // Parser callback for each xml start tag
            @Override
            public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                if (qName.equalsIgnoreCase("includefile")) {
                    String includeFileName = attributes.getValue("", "name");

                    try {
                        XmlCfgHndlr processXmlCfgProcessor = new XmlCfgHndlr(this.criteria, this.rootDir, this.isBaseConfig);
                        //System.out.println("Trying to process: " + includeFileName);
                        String resDir = (isBaseConfig) ? "" : criteria.getResDir();
                        currentlyReading = this.rootDir + resDir + includeFileName;
                        processXmlCfgProcessor.processXMLFlie(includeFileName);
                    } catch (SAXException ex) {

                        throw new SAXException("Failed to read: " + currentlyReading);

                    } catch (IOException ex) {
                        logger.debug(ex.toString());
                    }
                } else if (qName.equalsIgnoreCase("processcriteriainclude")) {

                     //String resDir = (isBaseConfig) ? "" : criteria.getResDir();
                    //currentlyReading = this.rootDir + resDir + "OverrideConfig.xml";
                    try {
                        String resDir = (isBaseConfig) ? "" : criteria.getResDir();
                        currentlyReading = this.rootDir + resDir + "Override.xml";
                        this.processCriteriaInclude();
                    } catch (SAXException ex) {
                          throw new SAXException("Failed to read: " + currentlyReading);
                    }
                }
            }

            // Parser callback for each xml end tag
            public void endElement(String uri, String localName, String qName) throws SAXException {

            }

            // Parse XML file
            private void processXMLFlie(String filename) throws IOException, SAXException {
                InputStream xcis = null;
                try {
                    if (filename.indexOf("DeveloperConfig.xml") >= 0) { // special case to prevent needing to update when copying from .net, which had fully qualified path
                        filename = "DeveloperConfig.xml";
                    }
                    String resDir = (isBaseConfig) ? "" : criteria.getResDir();
                    //logger.info("Current file: " + this.rootDir+resDir+filename);
                    xcis = ClassLoader.class.getResourceAsStream(this.rootDir + resDir + filename);

                    String nf = ((xcis == null) ? "]]]] Not Found > " : "Processesing: ");
                    //System.out.println(nf + "  File: " + resDir + filename);

                    
                    if (xcis != null) {
                    	//logger.info("xcis for " + resDir+filename + " is NOT null.");
                        SAXParserFactory factory = SAXParserFactory.newInstance();
                        SAXParser saxParser = factory.newSAXParser();
                        saxParser.parse(xcis, this);
                    }
                    else {
                    	//logger.info("xcis for " + resDir+filename +  " is NULL");
                    }

                } catch (SAXException ex) {
                    String resDir = (isBaseConfig) ? "" : criteria.getResDir();
                    currentlyReading = this.rootDir + resDir + filename;
                    logger.error("Process XML files: " + ex);

                    //System.out.println("File: " + resDir + filename + " seems to have a syntax error.");
                    //throw new SAXException("File: " + resDir + filename + " seems to have a syntax error.");
                    throw ex;
                } catch (ParserConfigurationException | IOException ex) {
                	//logger.debug(ex.toString());
                } finally {
                    if (xcis != null) {
                        xcis.close();
                    }
                }

            }

            // Hit ProcessCriteriaInclude tag, switch to reading OverrideConfig from res dir, storing settings in criteriaIndexedConfigs map
            private void processCriteriaInclude() throws SAXException {
                try {
                    for (int i = 0; i < ResDirs.length; i++) {
                        String[] localesDirs = ResDirs[i];
                        String locale = "";
                        String brand = "";
                        for (int loc = 0; loc < localesDirs.length; loc++) {
                            // Zero index of localesDirs is the locale, other indexes, if any are the brands
                            if (loc == 0) {
                                locale = localesDirs[0];
                                brand = "";
                            } else {
                                brand = localesDirs[loc];
                            }
                            criteria.updateCriteria(locale, brand);
                            this.rootDir = resRootDir;
                            this.isBaseConfig = false;
                            this.processXMLFlie("OverrideConfig.xml");
                        }
                    }
                } catch (SAXException ex) {
                    throw ex;
                } catch (IOException ex) {
                	//logger.debug(ex.toString());
                } finally {
                    this.rootDir = configRootDir;
                    this.isBaseConfig = true;
                }
            }

            // Process strings.xml from res dirs, storing string settings in criteriaIndexedConfigs map
            private void processStrings() throws SAXException {
                try {
                    for (int i = 0; i < ResDirs.length; i++) {
                        String[] localesDirs = ResDirs[i];
                        String locale = "";
                        String brand = "";
                        for (int loc = 0; loc < localesDirs.length; loc++) {
                            // Zero index of localesDirs is the locale, other indexes, if any are the brands
                            if (loc == 0) {
                                locale = localesDirs[0];
                                brand = "";
                            } else {
                                brand = localesDirs[loc];
                            }
                            criteria.updateCriteria(locale, brand);
                            this.rootDir = resRootDir;
                            this.isBaseConfig = false;
                            this.processXMLFlie("strings.xml");
                        }
                    }
                } catch (SAXException ex) {
                    if (ex.toString().contains("Failed to read")) {
                        logger.error("Exception is: " + ex);

                    } else {

                        logger.error("Process strings of : " + currentlyReading + " Exception is: " + ex);

                    }
                    throw ex;
                } catch (IOException ex) {
                	//logger.debug(ex.toString());
                }
            }

            // Process settings in WebsuiteConfig.xml, following include chain, storing settings in baseConfig map, unless its an AppliesTo or Res Dir
            private void processConfigs() throws SAXException {
                try {
                    this.rootDir = configRootDir;
                    this.isBaseConfig = true;
                    currentlyReading = rootDir + " WebsuiteConfig.xml";
                    this.processXMLFlie("WebsuiteConfig.xml");
                } catch (SAXException ex) {

                    if (ex.toString().contains("Failed to read")) {
                        logger.error("Exception is: " + ex);

                    } else {
                    	logger.error("Process configs of : " + currentlyReading + " Exception is: " + ex);
                    }

                    throw ex;
                } catch (IOException ex) {
                	//logger.debug(ex.toString());
                }
            }

        };

        WmCfgCriteria wmCfgCriteria = new WmCfgCriteria();
        XmlCfgHndlr xmlCfgProcessor = new XmlCfgHndlr(wmCfgCriteria, "", false);

        xmlCfgProcessor.processStrings();
        xmlCfgProcessor.processConfigs();

        startupComplete = true;

    }

}
