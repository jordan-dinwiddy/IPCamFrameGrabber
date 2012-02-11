package dinwiddy.ipcam.framegrab.config;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import org.junit.Test;

/**
 * Tests the config loader. 
 * 
 * @author Jordan Dinwiddy
 *
 */
public class XmlConfigLoaderTest {

	private static final String TEST_CONFIG_FILE_GOOD 		= "test/good_config.xml";
	private static final String TEST_CONFIG_FILE_BAD 		= "test/bad_config.xml";
	private static final String TEST_CONFIG_FILE_NOT_FOUND 	= "test/foobar.xml";
	private static final String TEST_CONFIG_FILE_NO_CAMS 	= "test/nocams_config.xml";
	
	/**
	 * Tests that a correctly formed XML config file parses okay. 
	 * @throws Exception
	 */
	@Test
	public void testGoodParse() throws Exception
	{
	      ConfigLoader configLoader = new XmlConfigLoader(TEST_CONFIG_FILE_GOOD);
	      ICaptionServerConfig config = configLoader.getConfig();

	      assertNotNull(config);
	      assertEquals("Default caption text", config.getDefaultCaptionText());
	      assertEquals(30.2, config.getDefaultFrequencySeconds());
	      assertEquals("C:\\Temp", config.getOutputDirectory());
	      assertEquals(3, config.getCameraConfigs().size());
	      
	      assertEquals("Cam2", config.getCameraConfigs().get(1).getName());
	      assertEquals("Caption Text2", config.getCameraConfigs().get(1).getCaptionText());
	      assertEquals("host2", config.getCameraConfigs().get(1).getHost());
	      assertEquals("RequestURL2", config.getCameraConfigs().get(1).getRequestUrl());
	      assertEquals(10.2, config.getCameraConfigs().get(1).getFrequencySeconds());
	}
	
	/**
	 * Tests that a file with malformed structure/syntax does not parse. See file for details
	 * on error. 
	 * @throws Exception
	 */
	@Test(expected=Exception.class)
	public void testBadParse() throws Exception 
	{
		  ConfigLoader configLoader = new XmlConfigLoader(TEST_CONFIG_FILE_BAD);
		  configLoader.getConfig();	  
	}
	
	/**
	 * Tests a ConfigException is thrown when a non-existent file is specified. 
	 * @throws Exception
	 */
	@Test(expected=FileNotFoundException.class)
	public void testFileNotFound() throws Exception
	{
		ConfigLoader configLoader = new XmlConfigLoader(TEST_CONFIG_FILE_NOT_FOUND);
	    configLoader.getConfig(); 
	}
}
