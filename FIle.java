package gui;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FIle {
	static String Filename = "java.txt";
	FIle(){
	}
	public void filemake() throws IOException
	{
		File f = new File(Filename);
		if(f.isFile())
			return;
		else
		{
			FileOutputStream file = new FileOutputStream(Filename);
			file.close();
		}
	}
}
