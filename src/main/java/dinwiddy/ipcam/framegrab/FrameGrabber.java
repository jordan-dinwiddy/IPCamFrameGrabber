package dinwiddy.ipcam.framegrab;

import java.text.SimpleDateFormat;
import java.util.Date;

import dinwiddy.ipcam.framegrab.config.ICameraConfig;

public class FrameGrabber {

	private ICameraConfig config;
	private long nextCaptureTime = System.currentTimeMillis();
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
		return (System.currentTimeMillis() >= nextCaptureTime);
	}

	public void captureAndSave() {
		System.out.printf("%s: %s: snap!\n", sdf.format(new Date()), config.getName());
		
		nextCaptureTime += config.getFrequencySeconds()*1000;
	}
}
