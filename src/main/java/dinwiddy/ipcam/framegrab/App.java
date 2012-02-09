package dinwiddy.ipcam.framegrab;


import dinwiddy.ipcam.framegrab.config.ConfigLoader;
import dinwiddy.ipcam.framegrab.config.ICaptionServerConfig;
import dinwiddy.ipcam.framegrab.config.XmlConfigLoader;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
      System.out.println( "Hello World!" );
        
      ConfigLoader configLoader = new XmlConfigLoader("example.xml");
      ICaptionServerConfig config = configLoader.getConfig();
    	
      System.out.println(String.format("Done - I've loaded %d camera configs.", config.getCameraConfigs().size()));
      
      
    	
    	/*XmlCaptionServerConfig c = new XmlCaptionServerConfig();
    	c.setDefaultFrequencySeconds(30);
    	c.setDefaultCaptionText("Default caption text");
    	c.setOutputDirectory("C:\\Temp");
    	
    	XmlCameraConfig c1 = new XmlCameraConfig();
    	c1.setCaptionText("Caption Text");
    	c1.setFrequencySeconds(10);
    	c1.setHost("host");
    	c1.setRequestUrl("RequestURL");
    	
    	List<ICameraConfig> list = new ArrayList<ICameraConfig>();
    	list.add(c1);
    	
    	c.setCameraConfigs(list);
    	
    	Serializer s = new Persister();
    	File result = new File("example.xml");
    	s.write(c,  result);
    	*/
    	
    }
}
