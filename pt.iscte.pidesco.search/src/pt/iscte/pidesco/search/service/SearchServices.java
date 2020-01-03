package pt.iscte.pidesco.search.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import pt.iscte.pidesco.projectbrowser.model.ClassElement;
import pt.iscte.pidesco.projectbrowser.model.PackageElement;
import pt.iscte.pidesco.search.internal.SearchView;

/**
 * Services offered by the Search component.
 */
public interface SearchServices {
	
	/**
	 * Search if equals the word in all lines from all classes.
	 * @param packageElement (non-null) packageElement to search
	 * @param word (non-null) word to search
	 * @param packageItem (non-null) package to search the word select 
	 * if packageItem is empty, app searchs in all classes
	 */
	void searchAllFileEquals(PackageElement packageElement, String word, String packageItem);
	
	/**
	 * Search if equals and case sensitive the word in all lines from all classes.
	 * @param packageElement (non-null) packageElement to search
	 * @param word (non-null) word to search
	 * @param packageItem (non-null) package to search the word select 
	 * if packageItem is empty, app searchs in all classes
	 */
	void searchAllFileEqualsCaseSensitive(PackageElement packageElement, String word, String packageItem);
	
	/**
	 * Search if contains the word in all lines from all classes.
	 * @param packageElement (non-null) packageElement to search
	 * @param word (non-null) word to search
	 * @param packageItem (non-null) package to search the word select 
	 * if packageItem is empty, app searchs in all classes
	 */
	void searchAllFileContains(PackageElement packageElement, String word, String packageItem);
	
	/**
	 * Search if starts with the word in all lines from all classes.
	 * @param packageElement (non-null) packageElement to search
	 * @param word (non-null) word to search
	 * @param packageItem (non-null) package to search the word select 
	 * if packageItem is empty, app searchs in all classes
	 */
	void searchAllFileStartsWith(PackageElement packageElement, String word, String packageItem);
	
	/**
	 * Search if ends with the word in all lines from all classes.
	 * @param packageElement (non-null) packageElement to search
	 * @param word (non-null) word to search
	 * @param packageItem (non-null) package to search the word select 
	 * if packageItem is empty, app searchs in all classes
	 */
	void searchAllFileEndsWith(PackageElement packageElement, String word, String packageItem);

	/**
	 * Search if equals the word in all lines from all classes.
	 * @param packageElement (non-null) packageElement to search
	 * @param word (non-null) word to search
	 * @param packageItem (non-null) package to search the word select (if packageItem is empty, app searchs in all classes)
	 * @param types if true just search the word in types declarations
	 * @param methods if true just search the word in methods declarations
	 * @param fields if true just search the word in fields declarations
	 */
	void searchEquals(PackageElement packageElement, String word, String packageItem, boolean types, boolean methods, boolean fields);
	
	/**
	 * Search if equals and case sensitive the word in all lines from all classes.
	 * @param packageElement (non-null) packageElement to search
	 * @param word (non-null) word to search
	 * @param packageItem (non-null) package to search the word select (if packageItem is empty, app searchs in all classes)
	 * @param types if true just search the word in types declarations
	 * @param methods if true just search the word in methods declarations
	 * @param fields if true just search the word in fields declarations
	 */
	void searchEqualsCaseSensitive(PackageElement packageElement, String word, String packageItem, boolean types, boolean methods, boolean fields);
	
	/**
	 * Search if contains the word in all lines from all classes.
	 * @param packageElement (non-null) packageElement to search
	 * @param word (non-null) word to search
	 * @param packageItem (non-null) package to search the word select (if packageItem is empty, app searchs in all classes)
	 * @param types if true just search the word in types declarations
	 * @param methods if true just search the word in methods declarations
	 * @param fields if true just search the word in fields declarations
	 */
	void searchContains(PackageElement packageElement, String word, String packageItem, boolean types, boolean methods, boolean fields);
	
	/**
	 * Search if starts with the word in all lines from all classes.
	 * @param packageElement (non-null) packageElement to search
	 * @param word (non-null) word to search
	 * @param packageItem (non-null) package to search the word select (if packageItem is empty, app searchs in all classes)
	 * @param types if true just search the word in types declarations
	 * @param methods if true just search the word in methods declarations
	 * @param fields if true just search the word in fields declarations
	 */
	void searchStartsWith(PackageElement packageElement, String word, String packageItem, boolean types, boolean methods, boolean fields);
	
	/**
	 * Search if ends with the word in all lines from all classes.
	 * @param packageElement (non-null) packageElement to search
	 * @param word (non-null) word to search
	 * @param packageItem (non-null) package to search the word select (if packageItem is empty, app searchs in all classes)
	 * @param types if true just search the word in types declarations
	 * @param methods if true just search the word in methods declarations
	 * @param fields if true just search the word in fields declarations
	 */
	void searchEndsWith(PackageElement packageElement, String word, String packageItem, boolean types, boolean methods, boolean fields);
	
	/**
	 * Search to get lines number of methods and fields from class.
	 * @param classElement (non-null) classElement to get lines
	 * @return HashMap of all methods and fields from class where first string is lineNumber "Line: XX" and second string is method/field name
	 */
	HashMap<String, String> getClassLines (ClassElement classElement);
	
	/**
	 * Search to get lines number of all methods.
	 * @return List of strings with all fields in formart "class::Line XX - methoddName" where '::' and ' - ' use as delimiter
	 */
	List<String> getMethodLines();
	
	/**
	 * Search to get lines number of all fields.
	 * @return List of strings with all fields in formart "class::Line XX - fieldName" where '::' and ' - ' use as delimiter
	 */
	List<String> getFieldsLines();
}
