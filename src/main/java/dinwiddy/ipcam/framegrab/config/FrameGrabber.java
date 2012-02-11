package dinwiddy.ipcam.framegrab.config;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FrameGrabber {

	private ICameraConfig config;
	private long lastCaptureTime = 0;
	private SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy @ HH:mm:ss.S z");
	
	public FrameGrabber(ICameraConfig config)
	{
		this.config = config;
	}
	
	public void capture()
	{
		// Captures image for the camera associated with this instance of FrameGrabber and
		// saves in the appropriate directory. 
	}

	public boolean readyToCapture() {
		return ((System.currentTimeMillis()) - lastCaptureTime) > config.getFrequencySeconds()*1000;
	}

	public void captureAndSave() {
		System.out.printf("%s: %s: snap!\n", sdf.format(new Date()), config.getName());
		lastCaptureTime = System.currentTimeMillis();
	}
}
