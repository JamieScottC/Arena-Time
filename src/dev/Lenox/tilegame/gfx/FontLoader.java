package dev.Lenox.tilegame.gfx;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

public class FontLoader {
	public Font loadFont(String path, float size){
		try {
		    //create the font to use. Specify the size!
			return Font.createFont(Font.TRUETYPE_FONT,getClass().getResourceAsStream(path)).deriveFont(Font.PLAIN, size);
		} catch (IOException e) {
		    e.printStackTrace();
		} catch(FontFormatException e) {
		    e.printStackTrace();
		}
		return null;
	}
}
