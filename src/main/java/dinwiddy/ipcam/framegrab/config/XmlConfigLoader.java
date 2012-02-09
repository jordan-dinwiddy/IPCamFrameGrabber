package dinwiddy.ipcam.framegrab.config;

import java.io.File;
import java.net.URL;
import org.simpleframework.xml.core.Persister;

public class XmlConfigLoader implements ConfigLoader {

	private Persister serializer;			// Instance of our serialiser/de-serializer
	private String relativeFileLocation;
	
	public XmlConfigLoader(String relativeFileLocation)
	{
		this.serializer = new Persister();
		this.relativeFileLocation = relativeFileLocation;
	}

	public ICaptionServerConfig getConfig() throws Exception {
		URL fileUrl = getClass().getClassLoader().getResource(relativeFileLocation);
		
		// TODO: Use more informative exception type
		if(fileUrl == null)
			throw new Exception(String.format("Unable to open classpath relative location \"%s\"", relativeFileLocation));
		
		File f = new File(fileUrl.getFile());
		
		System.out.println(f.toString());
		return serializer.read(XmlCaptionServerConfig.class, new File(fileUrl.getFile()));
	}
}
