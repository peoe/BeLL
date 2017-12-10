package io;

import java.awt.datatransfer.*;
import java.awt.Toolkit;

public class ClipboardCopier {

	/**
	 * Copies the String to your clipboard.
	 * 
	 * @param s
	 *            the String to be copied
	 */
	public static void copyToClipboard(String s) {
		StringSelection stringSelection = new StringSelection(s);
		Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
		clpbrd.setContents(stringSelection, null);
	}

}
