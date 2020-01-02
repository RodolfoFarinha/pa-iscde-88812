package pt.iscte.pidesco.search.extensibility;

import java.io.File;

/**
 * Allows to do a refactor with a desired String in all lines of file
 */
public interface SearchRefactor {

	/**
	 * Read and update file to do refactor with a desired String
	 * @param file (non-null) file to refactor
	 * @param oldString (non-null) oldString to refactor
	 * @param newString (non-null) newString to refactor
	 */
	void readUpadteFile(File file, String oldString, String newString);
}
