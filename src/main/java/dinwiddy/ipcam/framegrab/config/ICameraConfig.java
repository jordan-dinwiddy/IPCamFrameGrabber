package dinwiddy.ipcam.framegrab.config;

public interface ICameraConfig {

	public String getHost();
	
	public String getRequestUrl();
	
	public String getCaptionText();
	
	public float getFrequencySeconds();
}
