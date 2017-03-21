package io;
import java.awt.datatransfer.*;
import java.awt.Toolkit;

public class ClipboardCopier {
	
	public static void copyToClipboard(String s){
		StringSelection stringSelection = new StringSelection(s);
		Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
		clpbrd.setContents(stringSelection, null);
	}

}
