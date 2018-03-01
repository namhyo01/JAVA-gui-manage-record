package gui;

import java.io.IOException;

public class gui
{
	public static void main(String[] args) throws IOException
	{
		FIle file = new FIle();
		file.filemake();
		list a = new list();
		a.prepareframe();
	}
}

					