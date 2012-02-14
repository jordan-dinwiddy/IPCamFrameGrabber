package dinwiddy.ipcam.framegrab.config;

import java.io.FileNotFoundException;

public class Config
{

	private static ConfigLoader configLoader;
	private static ICaptionServerConfig config;
	
	public static void setLoader(ConfigLoader cl)
	{
		configLoader = cl;
	}
	
	/**
	 * Requests that the config loader loads the config again and then saves in memory. 
	 * @throws FileNotFoundException 
	 * @throws Exception If the underlying configLoader is unable to load. 
	 */
	public static void load() throws ConfigException, FileNotFoundException
	{
		if(configLoader == null)
			throw new ConfigException("No config loader defined");
		
		config = configLoader.getConfig();
	}
	
	/**
	 * Attempts to retrieve the config from memory if it has already been loaded, otherwise loads
	 * the config from the configLoader, saves in memory and returns.
	 *  
	 * @return The config or null if no prior call to load() had been made.  
	 */
	public static ICaptionServerConfig getConfig()
	{
		return config;
	}
}
