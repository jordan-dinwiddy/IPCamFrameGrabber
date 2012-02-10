package dinwiddy.ipcam.framegrab.config;

public interface ICameraConfig {

	public String getName();
	
	public String getHost();
	
	public String getRequestUrl();
	
	public String getCaptionText();
	
	public double getFrequencySeconds();
}
