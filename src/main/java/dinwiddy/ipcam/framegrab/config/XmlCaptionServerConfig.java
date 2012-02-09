package dinwiddy.ipcam.framegrab.config;

import java.util.List;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

/**
 * 
 * XML configuration for the caption server. 
 * 
 * @author Jordan Dinwiddy
 *
 */
@Root
public class XmlCaptionServerConfig implements ICaptionServerConfig {

	@Element
	private float defaultFrequencySeconds;
	
	@Element
	private String defaultCaptionText;
	
	@Element
	private String outputDirectory;
	
	@ElementList(type=XmlCameraConfig.class)
	private List<ICameraConfig> cameraConfigs;
	
	public float getDefaultFrequencySeconds() {
		return defaultFrequencySeconds;
	}
	public void setDefaultFrequencySeconds(float defaultFrequencySeconds) {
		this.defaultFrequencySeconds = defaultFrequencySeconds;
	}
	public String getDefaultCaptionText() {
		return defaultCaptionText;
	}
	public void setDefaultCaptionText(String defaultCaptionText) {
		this.defaultCaptionText = defaultCaptionText;
	}
	
	public String getOutputDirectory() {
		return outputDirectory;
	}
	public void setOutputDirectory(String outputDirectory) {
		this.outputDirectory = outputDirectory;
	}
	public List<ICameraConfig> getCameraConfigs() {
		return cameraConfigs;
	}
	public void setCameraConfigs(List<ICameraConfig> cameraConfigs) {
		this.cameraConfigs = cameraConfigs;
	}
}
