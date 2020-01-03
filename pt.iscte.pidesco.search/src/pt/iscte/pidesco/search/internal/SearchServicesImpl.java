package pt.iscte.pidesco.search.internal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import org.eclipse.core.runtime.Assert;

import pt.iscte.pidesco.projectbrowser.model.ClassElement;
import pt.iscte.pidesco.projectbrowser.model.PackageElement;
import pt.iscte.pidesco.search.service.SearchServices;

public class SearchServicesImpl implements SearchServices {
	
	@Override
	public void searchAllFileEquals(PackageElement packageElement, String word, String packageItem) {
		Assert.isNotNull(packageElement, "packageElement cannot be null");
		Assert.isNotNull(word, "word cannot be null");
		Assert.isNotNull(packageItem, "packageItem cannot be null");
		
		boolean[] conditions = new boolean[9];
		
		SearchView.searchBar.setText(word);
		SearchView.fileName.setText(packageItem);
		
		SearchView.caseSensitive.setSelection(conditions[0] = false); 	// caseSensitive
		SearchView.equals.setSelection(conditions[1] = true);			// equals
		SearchView.contains.setSelection(conditions[2] = false);		// contains
		SearchView.startsWith.setSelection(conditions[3] = false);		// startsWith
		SearchView.endsWith.setSelection(conditions[4] = false);		// endsWith
		SearchView.allFile.setSelection(conditions[5] = true);			// allFiles
		SearchView.type.setSelection(conditions[6] = false);			// types
		SearchView.method.setSelection(conditions[7] = false);			// methods
		SearchView.field.setSelection(conditions[8] = false);			// fields
		
		SearchVisitor searchVisitor = new SearchVisitor(conditions, word, packageItem);	
		packageElement.traverse(searchVisitor);
		
		SearchView.showTree(searchVisitor.pathClass, searchVisitor.packageClass, searchVisitor.types, searchVisitor.fileLines, searchVisitor.methods, searchVisitor.fields);
	}
	
	@Override
	public void searchAllFileEqualsCaseSensitive(PackageElement packageElement, String word, String packageItem) {
		Assert.isNotNull(packageElement, "packageElement cannot be null");
		Assert.isNotNull(word, "word cannot be null");
		Assert.isNotNull(packageItem, "packageItem cannot be null");
		
		boolean[] conditions = new boolean[9];
		
		SearchView.searchBar.setText(word);
		SearchView.fileName.setText(packageItem);
		
		SearchView.caseSensitive.setSelection(conditions[0] = true); 	// caseSensitive
		SearchView.equals.setSelection(conditions[1] = true);			// equals
		SearchView.contains.setSelection(conditions[2] = false);		// contains
		SearchView.startsWith.setSelection(conditions[3] = false);		// startsWith
		SearchView.endsWith.setSelection(conditions[4] = false);		// endsWith
		SearchView.allFile.setSelection(conditions[5] = true);			// allFiles
		SearchView.type.setSelection(conditions[6] = false);			// types
		SearchView.method.setSelection(conditions[7] = false);			// methods
		SearchView.field.setSelection(conditions[8] = false);			// fields
		
		SearchVisitor searchVisitor = new SearchVisitor(conditions, word, packageItem);	
		packageElement.traverse(searchVisitor);
		
		SearchView.showTree(searchVisitor.pathClass, searchVisitor.packageClass, searchVisitor.types, searchVisitor.fileLines, searchVisitor.methods, searchVisitor.fields);
	}
	
	@Override
	public void searchAllFileContains(PackageElement packageElement, String word, String packageItem) {
		Assert.isNotNull(packageElement, "packageElement cannot be null");
		Assert.isNotNull(word, "word cannot be null");
		Assert.isNotNull(packageItem, "packageItem cannot be null");
		
		boolean[] conditions = new boolean[9];
		
		SearchView.searchBar.setText(word);
		SearchView.fileName.setText(packageItem);
		
		SearchView.caseSensitive.setSelection(conditions[0] = false); 	// caseSensitive
		SearchView.equals.setSelection(conditions[1] = false);			// equals
		SearchView.contains.setSelection(conditions[2] = true);			// contains
		SearchView.startsWith.setSelection(conditions[3] = false);		// startsWith
		SearchView.endsWith.setSelection(conditions[4] = false);		// endsWith
		SearchView.allFile.setSelection(conditions[5] = true);			// allFiles
		SearchView.type.setSelection(conditions[6] = false);			// types
		SearchView.method.setSelection(conditions[7] = false);			// methods
		SearchView.field.setSelection(conditions[8] = false);			// fields
		
		SearchVisitor searchVisitor = new SearchVisitor(conditions, word, packageItem);	
		packageElement.traverse(searchVisitor);
		
		SearchView.showTree(searchVisitor.pathClass, searchVisitor.packageClass, searchVisitor.types, searchVisitor.fileLines, searchVisitor.methods, searchVisitor.fields);
	}
	
	@Override
	public void searchAllFileStartsWith(PackageElement packageElement, String word, String packageItem) {
		Assert.isNotNull(packageElement, "packageElement cannot be null");
		Assert.isNotNull(word, "word cannot be null");
		Assert.isNotNull(packageItem, "packageItem cannot be null");
		
		boolean[] conditions = new boolean[9];
		
		SearchView.searchBar.setText(word);
		SearchView.fileName.setText(packageItem);
		
		SearchView.caseSensitive.setSelection(conditions[0] = false); 	// caseSensitive
		SearchView.equals.setSelection(conditions[1] = false);			// equals
		SearchView.contains.setSelection(conditions[2] = false);		// contains
		SearchView.startsWith.setSelection(conditions[3] = true);		// startsWith
		SearchView.endsWith.setSelection(conditions[4] = false);		// endsWith
		SearchView.allFile.setSelection(conditions[5] = true);			// allFiles
		SearchView.type.setSelection(conditions[6] = false);			// types
		SearchView.method.setSelection(conditions[7] = false);			// methods
		SearchView.field.setSelection(conditions[8] = false);			// fields
		
		SearchVisitor searchVisitor = new SearchVisitor(conditions, word, packageItem);	
		packageElement.traverse(searchVisitor);
		
		SearchView.showTree(searchVisitor.pathClass, searchVisitor.packageClass, searchVisitor.types, searchVisitor.fileLines, searchVisitor.methods, searchVisitor.fields);
	}
	
	@Override
	public void searchAllFileEndsWith(PackageElement packageElement, String word, String packageItem) {
		Assert.isNotNull(packageElement, "packageElement cannot be null");
		Assert.isNotNull(word, "word cannot be null");
		Assert.isNotNull(packageItem, "packageItem cannot be null");
		
		boolean[] conditions = new boolean[9];
		
		SearchView.searchBar.setText(word);
		SearchView.fileName.setText(packageItem);
		
		SearchView.caseSensitive.setSelection(conditions[0] = false); 	// caseSensitive
		SearchView.equals.setSelection(conditions[1] = false);			// equals
		SearchView.contains.setSelection(conditions[2] = false);		// contains
		SearchView.startsWith.setSelection(conditions[3] = false);		// startsWith
		SearchView.endsWith.setSelection(conditions[4] = true);			// endsWith
		SearchView.allFile.setSelection(conditions[5] = true);			// allFiles
		SearchView.type.setSelection(conditions[6] = false);			// types
		SearchView.method.setSelection(conditions[7] = false);			// methods
		SearchView.field.setSelection(conditions[8] = false);			// fields
		
		SearchVisitor searchVisitor = new SearchVisitor(conditions, word, packageItem);	
		packageElement.traverse(searchVisitor);
		
		SearchView.showTree(searchVisitor.pathClass, searchVisitor.packageClass, searchVisitor.types, searchVisitor.fileLines, searchVisitor.methods, searchVisitor.fields);
	}
	
	@Override
	public void searchEquals(PackageElement packageElement, String word, String packageItem, boolean types, boolean methods, boolean fields) {
		Assert.isNotNull(packageElement, "packageElement cannot be null");
		Assert.isNotNull(word, "word cannot be null");
		Assert.isNotNull(packageItem, "packageItem cannot be null");
		
		boolean[] conditions = new boolean[9];
		
		SearchView.searchBar.setText(word);
		SearchView.fileName.setText(packageItem);
		
		SearchView.caseSensitive.setSelection(conditions[0] = false); 	// caseSensitive
		SearchView.equals.setSelection(conditions[1] = true);			// equals
		SearchView.contains.setSelection(conditions[2] = false);		// contains
		SearchView.startsWith.setSelection(conditions[3] = false);		// startsWith
		SearchView.endsWith.setSelection(conditions[4] = false);		// endsWith
		SearchView.allFile.setSelection(conditions[5] = false);			// allFiles
		SearchView.type.setSelection(conditions[6] = types);			// types
		SearchView.method.setSelection(conditions[7] = methods);		// methods
		SearchView.field.setSelection(conditions[8] = fields);			// fields
		
		SearchVisitor searchVisitor = new SearchVisitor(conditions, word, packageItem);	
		packageElement.traverse(searchVisitor);
		
		SearchView.showTree(searchVisitor.pathClass, searchVisitor.packageClass, searchVisitor.types, searchVisitor.fileLines, searchVisitor.methods, searchVisitor.fields);
	}
	
	@Override
	public void searchEqualsCaseSensitive(PackageElement packageElement, String word, String packageItem, boolean types, boolean methods, boolean fields) {
		Assert.isNotNull(packageElement, "packageElement cannot be null");
		Assert.isNotNull(word, "word cannot be null");
		Assert.isNotNull(packageItem, "packageItem cannot be null");
		
		boolean[] conditions = new boolean[9];
		
		SearchView.searchBar.setText(word);
		SearchView.fileName.setText(packageItem);
		
		SearchView.caseSensitive.setSelection(conditions[0] = true); 	// caseSensitive
		SearchView.equals.setSelection(conditions[1] = true);			// equals
		SearchView.contains.setSelection(conditions[2] = false);		// contains
		SearchView.startsWith.setSelection(conditions[3] = false);		// startsWith
		SearchView.endsWith.setSelection(conditions[4] = false);		// endsWith
		SearchView.allFile.setSelection(conditions[5] = false);			// allFiles
		SearchView.type.setSelection(conditions[6] = types);			// types
		SearchView.method.setSelection(conditions[7] = methods);		// methods
		SearchView.field.setSelection(conditions[8] = fields);			// fields
		
		SearchVisitor searchVisitor = new SearchVisitor(conditions, word, packageItem);	
		packageElement.traverse(searchVisitor);
		
		SearchView.showTree(searchVisitor.pathClass, searchVisitor.packageClass, searchVisitor.types, searchVisitor.fileLines, searchVisitor.methods, searchVisitor.fields);
	}
	
	@Override
	public void searchContains(PackageElement packageElement, String word, String packageItem, boolean types, boolean methods, boolean fields) {
		Assert.isNotNull(packageElement, "packageElement cannot be null");
		Assert.isNotNull(word, "word cannot be null");
		Assert.isNotNull(packageItem, "packageItem cannot be null");
		
		boolean[] conditions = new boolean[9];
		
		SearchView.searchBar.setText(word);
		SearchView.fileName.setText(packageItem);
		
		SearchView.caseSensitive.setSelection(conditions[0] = false); 	// caseSensitive
		SearchView.equals.setSelection(conditions[1] = false);			// equals
		SearchView.contains.setSelection(conditions[2] = true);			// contains
		SearchView.startsWith.setSelection(conditions[3] = false);		// startsWith
		SearchView.endsWith.setSelection(conditions[4] = false);		// endsWith
		SearchView.allFile.setSelection(conditions[5] = false);			// allFiles
		SearchView.type.setSelection(conditions[6] = types);			// types
		SearchView.method.setSelection(conditions[7] = methods);		// methods
		SearchView.field.setSelection(conditions[8] = fields);			// fields
		
		SearchVisitor searchVisitor = new SearchVisitor(conditions, word, packageItem);	
		packageElement.traverse(searchVisitor);
		
		SearchView.showTree(searchVisitor.pathClass, searchVisitor.packageClass, searchVisitor.types, searchVisitor.fileLines, searchVisitor.methods, searchVisitor.fields);
	}
	
	@Override
	public void searchStartsWith(PackageElement packageElement, String word, String packageItem, boolean types, boolean methods, boolean fields) {
		Assert.isNotNull(packageElement, "packageElement cannot be null");
		Assert.isNotNull(word, "word cannot be null");
		Assert.isNotNull(packageItem, "packageItem cannot be null");
		
		boolean[] conditions = new boolean[9];
		
		SearchView.searchBar.setText(word);
		SearchView.fileName.setText(packageItem);
		
		SearchView.caseSensitive.setSelection(conditions[0] = false); 	// caseSensitive
		SearchView.equals.setSelection(conditions[1] = false);			// equals
		SearchView.contains.setSelection(conditions[2] = false);		// contains
		SearchView.startsWith.setSelection(conditions[3] = true);		// startsWith
		SearchView.endsWith.setSelection(conditions[4] = false);		// endsWith
		SearchView.allFile.setSelection(conditions[5] = false);			// allFiles
		SearchView.type.setSelection(conditions[6] = types);			// types
		SearchView.method.setSelection(conditions[7] = methods);		// methods
		SearchView.field.setSelection(conditions[8] = fields);			// fields

		SearchVisitor searchVisitor = new SearchVisitor(conditions, word, packageItem);	
		packageElement.traverse(searchVisitor);
		
		SearchView.showTree(searchVisitor.pathClass, searchVisitor.packageClass, searchVisitor.types, searchVisitor.fileLines, searchVisitor.methods, searchVisitor.fields);
	}
	
	@Override
	public void searchEndsWith(PackageElement packageElement, String word, String packageItem, boolean types, boolean methods, boolean fields) {
		Assert.isNotNull(packageElement, "packageElement cannot be null");
		Assert.isNotNull(word, "word cannot be null");
		Assert.isNotNull(packageItem, "packageItem cannot be null");
		
		boolean[] conditions = new boolean[9];
		
		SearchView.searchBar.setText(word);
		SearchView.fileName.setText(packageItem);
		
		SearchView.caseSensitive.setSelection(conditions[0] = false); 	// caseSensitive
		SearchView.equals.setSelection(conditions[1] = false);			// equals
		SearchView.contains.setSelection(conditions[2] = false);		// contains
		SearchView.startsWith.setSelection(conditions[3] = false);		// startsWith
		SearchView.endsWith.setSelection(conditions[4] = true);			// endsWith
		SearchView.allFile.setSelection(conditions[5] = false);			// allFiles
		SearchView.type.setSelection(conditions[6] = types);			// types
		SearchView.method.setSelection(conditions[7] = methods);		// methods
		SearchView.field.setSelection(conditions[8] = fields);			// fields
		
		SearchVisitor searchVisitor = new SearchVisitor(conditions, word, packageItem);	
		packageElement.traverse(searchVisitor);
		
		SearchView.showTree(searchVisitor.pathClass, searchVisitor.packageClass, searchVisitor.types, searchVisitor.fileLines, searchVisitor.methods, searchVisitor.fields);
	}
	
	@Override
	public HashMap<String, String> getClassLines(ClassElement classElement){
		Assert.isNotNull(classElement, "classElement cannot be null");
		
		HashMap<String, String> getClassLines = new HashMap<>();
		
		boolean[] conditions = new boolean[9];
		
		conditions[0] = false;	// caseSensitive
		conditions[1] = false;	// equals
		conditions[2] = true;	// contains
		conditions[3] = false;	// startsWith
		conditions[4] = false;	// endsWith
		conditions[5] = false;	// allFiles
		conditions[6] = false;	// types
		conditions[7] = true;	// methods
		conditions[8] = true;	// fields
		
		SearchVisitor searchVisitor = new SearchVisitor(conditions, "","");
		searchVisitor.visitClass(classElement);
		
		Collections.reverse(searchVisitor.methods);
		Collections.reverse(searchVisitor.fields);
		
		for(String method :	searchVisitor.methods) {
			getClassLines.put(method.substring(method.indexOf("::") + 2, method.indexOf(" - ")), method.substring(method.indexOf(" - ") + 3));	
		}
		
		for(String field :	searchVisitor.fields) {
			getClassLines.put(field.substring(field.indexOf("::") + 2, field.indexOf(" - ")), field.substring(field.indexOf(" - ") + 3));	
		}
		
		return getClassLines;
	}
	
	@Override
	public List<String> getMethodLines(){
		return SearchView.methods;
	}
	
	@Override
	public List<String> getFieldsLines(){
		return SearchView.fields;
	}
}
