package dinwiddy.ipcam.framegrab;


import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import dinwiddy.ipcam.framegrab.config.Config;
import dinwiddy.ipcam.framegrab.config.ConfigException;
import dinwiddy.ipcam.framegrab.config.ConfigLoader;
import dinwiddy.ipcam.framegrab.config.ICameraConfig;
import dinwiddy.ipcam.framegrab.config.XmlConfigLoader;

public class App 
{
	private static final String CONFIG_XML_LOCATION = "config.xml";
	private static final long FRAME_LOOP_SLEEP = 1;
	
	private List<FrameGrabber> frameGrabbers;
	
	public App() {
	}
	
	/**
	 * Kicks everything off. Loads the config, initialises the application and then loops over 
	 * all cameras, grabbing and saving frames. 
	 */
	public void run()
	{
		try
		{
			loadAndValidateConfig();
			
			initialiseFrameGrabbers();
		
			enterGrabbing();
		}
		catch(ConfigException ce)
		{
			System.out.flush();
			ce.printStackTrace();
		}
		catch(Exception e)
		{
			System.out.flush();
			e.printStackTrace();
		}
	}

	/**
	 * Enter a continuous loop that iterates over each of our frame grabber objects and 
	 * retrieves and saves a frame if the grabber is ready (i.e if the necessary amount of time
	 * has elapsed).
	 */
	private void enterGrabbing() 
    {
		while(true)
		{
			for(FrameGrabber fg : frameGrabbers)
			{
				if(fg.readyToCapture())
					fg.captureAndSave();
			}
			
			try 
			{
				Thread.sleep(FRAME_LOOP_SLEEP);
			} 
			catch (InterruptedException e) { }
		}
	}

	/**
	 * Creates FrameGrabber objects for each camera defined in the global config. 
	 */
	private void initialiseFrameGrabbers() 
	{
		frameGrabbers = new ArrayList<FrameGrabber>();
		
		List<ICameraConfig> configs = Config.getConfig().getCameraConfigs();
		
		for(ICameraConfig camConfig : configs)
		{
			System.out.printf("Initing FrameGrabber for cam \"%s\"...\n", camConfig.getName());
			frameGrabbers.add(new FrameGrabber(camConfig));
		}
	}

	/**
	 * Loads and validates the config. 
	 * 
	 * @throws ConfigException if the config file was unable to load or if it is missing 
	 * data. 
	 */
	private void loadAndValidateConfig() throws ConfigException
	{
		// Attempts to load the config from file or whatever source is used by underlying 
		// ConfigLoader. Throws exception if it fails. 
		ConfigLoader cl = new XmlConfigLoader(CONFIG_XML_LOCATION);
		Config.setLoader(cl);
		
		try
		{
			Config.load();	// May throw file not found
						
			if(Config.getConfig().getCameraConfigs().size() < 1)
				throw new ConfigException("Server config must define at least one camera");
			
		} 
		catch (FileNotFoundException e)
		{
			throw new ConfigException("Unable to open config file");
		}
	}

	/**
	 * Entry point. 
	 * @param args
	 * @throws Exception
	 */
	public static void main( String[] args )
    {
    	new App().run();
    }
}
