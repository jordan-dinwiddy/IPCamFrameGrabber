package dinwiddy.ipcam.framegrab.config;

public class ConfigException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ConfigException(String reason)
	{
		super(reason);
	}
	
	public ConfigException(String reason, Exception e)
	{
		super(reason, e);
	}

}
