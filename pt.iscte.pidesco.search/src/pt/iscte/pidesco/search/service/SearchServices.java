package pt.iscte.pidesco.search.service;

import java.io.File;

/**
 * Services offered by the Search component.
 */
public interface SearchServices {
	
	/**
	 * Search if equals the word in all lines from all classes.
	 * @param word (non-null) word to search
	 * @param packageItem (non-null) package to search the word select 
	 * if packageItem is empty, app searchs in all classes
	 */
	void searchAllFileEquals(String word, String packageItem);
	
	/**
	 * Search if equals and case sensitive the word in all lines from all classes.
	 * @param word (non-null) word to search
	 * @param packageItem (non-null) package to search the word select 
	 * if packageItem is empty, app searchs in all classes
	 */
	void searchAllFileEqualsCaseSensitive(String word, String packageItem);
	
	/**
	 * Search if contains the word in all lines from all classes.
	 * @param word (non-null) word to search
	 * @param packageItem (non-null) package to search the word select 
	 * if packageItem is empty, app searchs in all classes
	 */
	void searchAllFileContains(String word, String packageItem);
	
	/**
	 * Search if starts with the word in all lines from all classes.
	 * @param word (non-null) word to search
	 * @param packageItem (non-null) package to search the word select 
	 * if packageItem is empty, app searchs in all classes
	 */
	void searchAllFileStartsWith(String word, String packageItem);
	
	/**
	 * Search if ends with the word in all lines from all classes.
	 * @param word (non-null) word to search
	 * @param packageItem (non-null) package to search the word select 
	 * if packageItem is empty, app searchs in all classes
	 */
	void searchAllFileEndsWith(String word, String packageItem);

	/**
	 * Search if equals the word in all lines from all classes.
	 * @param word (non-null) word to search
	 * @param packageItem (non-null) package to search the word select (if packageItem is empty, app searchs in all classes)
	 * @param types if true just search the word in types declarations
	 * @param methods if true just search the word in methods declarations
	 * @param fields if true just search the word in fields declarations
	 */
	void searchEquals(String word, String packageItem, boolean types, boolean methods, boolean fields);
	
	/**
	 * Search if equals and case sensitive the word in all lines from all classes.
	 * @param word (non-null) word to search
	 * @param packageItem (non-null) package to search the word select (if packageItem is empty, app searchs in all classes)
	 * @param types if true just search the word in types declarations
	 * @param methods if true just search the word in methods declarations
	 * @param fields if true just search the word in fields declarations
	 */
	void searchEqualsCaseSensitive(String word, String packageItem, boolean types, boolean methods, boolean fields);
	
	/**
	 * Search if contains the word in all lines from all classes.
	 * @param word (non-null) word to search
	 * @param packageItem (non-null) package to search the word select (if packageItem is empty, app searchs in all classes)
	 * @param types if true just search the word in types declarations
	 * @param methods if true just search the word in methods declarations
	 * @param fields if true just search the word in fields declarations
	 */
	void searchContains(String word, String packageItem, boolean types, boolean methods, boolean fields);
	
	/**
	 * Search if starts with the word in all lines from all classes.
	 * @param word (non-null) word to search
	 * @param packageItem (non-null) package to search the word select (if packageItem is empty, app searchs in all classes)
	 * @param types if true just search the word in types declarations
	 * @param methods if true just search the word in methods declarations
	 * @param fields if true just search the word in fields declarations
	 */
	void searchStartsWith(String word, String packageItem, boolean types, boolean methods, boolean fields);
	
	/**
	 * Search if ends with the word in all lines from all classes.
	 * @param word (non-null) word to search
	 * @param packageItem (non-null) package to search the word select (if packageItem is empty, app searchs in all classes)
	 * @param types if true just search the word in types declarations
	 * @param methods if true just search the word in methods declarations
	 * @param fields if true just search the word in fields declarations
	 */
	void searchEndsWith(String word, String packageItem, boolean types, boolean methods, boolean fields);
}
