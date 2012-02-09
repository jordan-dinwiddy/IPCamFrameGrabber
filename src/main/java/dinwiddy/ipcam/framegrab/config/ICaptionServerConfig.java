package dinwiddy.ipcam.framegrab.config;

import java.util.List;

public interface ICaptionServerConfig {

	public float getDefaultFrequencySeconds();
	
	public String getDefaultCaptionText();
	
	public String getOutputDirectory();
	
	public List<ICameraConfig> getCameraConfigs();
}
