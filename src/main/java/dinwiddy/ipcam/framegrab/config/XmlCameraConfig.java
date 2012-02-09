package dinwiddy.ipcam.framegrab.config;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * 
 * Configuration for a specific camera instance. 
 * 
 * @author Jordan Dinwiddy
 *
 */
@Root
public class XmlCameraConfig implements ICameraConfig {

	@Element
	private String host;
	
	@Element
	private String requestUrl;
	
	@Element
	private String captionText;
	
	@Element
	private float frequencySeconds;
	
	public String getHost() {
		return host;
	}
	public String getRequestUrl() {
		return requestUrl;
	}
	public String getCaptionText() {
		return captionText;
	}
	public float getFrequencySeconds() {
		return frequencySeconds;
	}

	public void setHost(String host) {
		this.host = host;
	}
	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}
	public void setCaptionText(String captionText) {
		this.captionText = captionText;
	}
	public void setFrequencySeconds(float frequencySeconds) {
		this.frequencySeconds = frequencySeconds;
	}
}
