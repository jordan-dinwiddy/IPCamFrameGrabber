package dinwiddy.ipcam.framegrab;


import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import dinwiddy.ipcam.framegrab.config.Config;
import dinwiddy.ipcam.framegrab.config.ConfigException;
import dinwiddy.ipcam.framegrab.config.ConfigLoader;
import dinwiddy.ipcam.framegrab.config.FrameGrabber;
import dinwiddy.ipcam.framegrab.config.ICameraConfig;
import dinwiddy.ipcam.framegrab.config.ICaptionServerConfig;
import dinwiddy.ipcam.framegrab.config.XmlConfigLoader;

/**
 * Hello world!
 *
 */
public class App 
{
	private static final String CONFIG_XML_LOCATION = "config.xml";
	private List<FrameGrabber> frameGrabbers;
	
	
	public App() {
		
	}
	
	public void run()
	{
		try
		{
			loadAndValidateConfig();
			
			initialiseFrameGrabbers();
		
			// Loop over frame grabbers and capture frames. 
			enterFrameGrabLoop();
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

	private void enterFrameGrabLoop() 
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
				Thread.sleep(10);
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
		
		for(ICameraConfig camConfig : Config.getConfig().getCameraConfigs())
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
		Config.initialise(cl);
		
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

	public static void main( String[] args ) throws Exception
    {
    	new App().run();

    }
}
