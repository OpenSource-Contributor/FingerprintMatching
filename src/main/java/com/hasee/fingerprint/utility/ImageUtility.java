package com.hasee.fingerprint.utility;

import java.util.Base64;

public class ImageUtility
{
	public static byte[] Base64ToByteArray( String base64 )
	{
		try
		{
			Base64.Decoder decoder = Base64.getDecoder();
			return decoder.decode( base64 );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return null;
		}
	}
}
