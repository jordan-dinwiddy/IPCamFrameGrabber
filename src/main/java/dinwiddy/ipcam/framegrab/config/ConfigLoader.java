package dinwiddy.ipcam.framegrab.config;

import java.io.FileNotFoundException;

public interface ConfigLoader {

	public ICaptionServerConfig getConfig() throws ConfigException, FileNotFoundException;
	
}
