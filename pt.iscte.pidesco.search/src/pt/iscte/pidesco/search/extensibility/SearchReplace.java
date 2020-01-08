package pt.iscte.pidesco.search.extensibility;

import java.io.File;

/**
 * Allows to do a replace with a desired String in all lines of file
 */
public interface SearchReplace {

	/**
	 * Send the word search
	 * @param desired string to search
	 */
	void search(String searchString);
	
	/**
	 * Get file to replace with a desired String
	 * @return file to replace method
	 */
	File getFileReplace();
	
	/**
	 * Get string to replace in file
	 * @return string to replace method
	 */
	String getNewString();
}
