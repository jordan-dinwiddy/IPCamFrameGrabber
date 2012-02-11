package dinwiddy.ipcam.framegrab.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import org.simpleframework.xml.core.Persister;

public class XmlConfigLoader implements ConfigLoader {

	private Persister serializer;			// Instance of our serialiser/de-serializer
	private String relativeFileLocation;

	/**
	 * 
	 * @param relativeFileLocation
	 */
	public XmlConfigLoader(String relativeFileLocation)
	{
		this.serializer = new Persister();
		this.relativeFileLocation = relativeFileLocation;
	}
	
	/**
	 * 
	 */
	public ICaptionServerConfig getConfig() throws ConfigException, FileNotFoundException {
		URL fileUrl = getClass().getClassLoader().getResource(relativeFileLocation);
		
		// TODO: Use more informative exception type
		if(fileUrl == null)
			throw new FileNotFoundException(String.format("Unable to open classpath relative location \"%s\"", relativeFileLocation));
		
		try
		{
			File f = new File(fileUrl.getFile());
			return serializer.read(XmlCaptionServerConfig.class, f);
		} 
		catch (Exception e)
		{
			throw new ConfigException("Unable to parse config file", e);
		}
	}
}
