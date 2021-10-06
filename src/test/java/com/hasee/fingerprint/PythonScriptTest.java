package com.hasee.fingerprint;

import org.junit.jupiter.api.Test;

import java.io.IOException;

public class PythonScriptTest
{
	@Test
	public void testPythonScript() throws IOException
	{
		String fetching = "python " + "c:\\Fetch.py";
		String[] commandToExecute = new String[]{"cmd.exe", "/c", fetching};
		Runtime.getRuntime().exec(commandToExecute);
	}
}
