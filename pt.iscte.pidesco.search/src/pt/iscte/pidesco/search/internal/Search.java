package pt.iscte.pidesco.search.internal;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import pt.iscte.pidesco.javaeditor.service.JavaEditorServices;
import pt.iscte.pidesco.projectbrowser.service.ProjectBrowserServices;
import pt.iscte.pidesco.search.service.SearchServices;
import pt.iscte.pidesco.projectbrowser.model.ClassElement;
import pt.iscte.pidesco.projectbrowser.model.PackageElement;
import pt.iscte.pidesco.projectbrowser.model.PackageElement.Visitor;

public class Search implements SearchServices{
	
	private BundleContext context = Activator.getContext();
	
	private ServiceReference<ProjectBrowserServices> serviceReference = context.getServiceReference(ProjectBrowserServices.class);
	private ProjectBrowserServices projectBrowserServices = context.getService(serviceReference);
	
	private PackageElement packageElement = projectBrowserServices.getRootPackage();	
	
	public void search(boolean [] conditions, String word, String packageItem) {		
		SearchVisitor searchVisitor = new SearchVisitor(conditions, word, packageItem);	
		packageElement.traverse(searchVisitor);
		
		SearchView.showTree(searchVisitor.pathClass, searchVisitor.packageClass, searchVisitor.types, searchVisitor.fileLines, searchVisitor.methods, searchVisitor.fields);
	}
	
	public void getPackage() {
		PackageVisitor packageVisitor = new PackageVisitor();	
		packageElement.traverse(packageVisitor);
		
		SearchView.addItemsCombo(packageVisitor.packageClass);
	}
	
	@Override
	public void searchAllFileEquals(String word, String packageItem) {
		Assert.isNotNull(word, "word cannot be null");
		Assert.isNotNull(packageItem, "packageItem cannot be null");
		
		boolean[] conditions = new boolean[9];
		
		conditions[0] = false; 	// caseSensitive
		conditions[1] = true;	// equals
		conditions[2] = false;	// contains
		conditions[3] = false;	// startsWith
		conditions[4] = false;	// endsWith
		conditions[5] = true;	// allFiles
		conditions[6] = false;	// types
		conditions[7] = false;	// methods
		conditions[8] = false;	// fields
		
		SearchVisitor searchVisitor = new SearchVisitor(conditions, word, packageItem);	
		packageElement.traverse(searchVisitor);
		
		SearchView.showTree(searchVisitor.pathClass, searchVisitor.packageClass, searchVisitor.types, searchVisitor.fileLines, searchVisitor.methods, searchVisitor.fields);
	}
	
	@Override
	public void searchAllFileEqualsCaseSensitive(String word, String packageItem) {
		Assert.isNotNull(word, "word cannot be null");
		Assert.isNotNull(packageItem, "packageItem cannot be null");
		
		boolean[] conditions = new boolean[9];
		
		conditions[0] = true; 	// caseSensitive
		conditions[1] = true;	// equals
		conditions[2] = false;	// contains
		conditions[3] = false;	// startsWith
		conditions[4] = false;	// endsWith
		conditions[5] = true;	// allFiles
		conditions[6] = false;	// types
		conditions[7] = false;	// methods
		conditions[8] = false;	// fields
		
		SearchVisitor searchVisitor = new SearchVisitor(conditions, word, packageItem);	
		packageElement.traverse(searchVisitor);
		
		SearchView.showTree(searchVisitor.pathClass, searchVisitor.packageClass, searchVisitor.types, searchVisitor.fileLines, searchVisitor.methods, searchVisitor.fields);
	}
	
	@Override
	public void searchAllFileContains(String word, String packageItem) {
		Assert.isNotNull(word, "word cannot be null");
		Assert.isNotNull(packageItem, "packageItem cannot be null");
		
		boolean[] conditions = new boolean[9];
		
		conditions[0] = false; 	// caseSensitive
		conditions[1] = false;	// equals
		conditions[2] = true;	// contains
		conditions[3] = false;	// startsWith
		conditions[4] = false;	// endsWith
		conditions[5] = true;	// allFiles
		conditions[6] = false;	// types
		conditions[7] = false;	// methods
		conditions[8] = false;	// fields
		
		SearchVisitor searchVisitor = new SearchVisitor(conditions, word, packageItem);	
		packageElement.traverse(searchVisitor);
		
		SearchView.showTree(searchVisitor.pathClass, searchVisitor.packageClass, searchVisitor.types, searchVisitor.fileLines, searchVisitor.methods, searchVisitor.fields);
	}
	
	@Override
	public void searchAllFileStartsWith(String word, String packageItem) {
		Assert.isNotNull(word, "word cannot be null");
		Assert.isNotNull(packageItem, "packageItem cannot be null");
		
		boolean[] conditions = new boolean[9];
		
		conditions[0] = false; 	// caseSensitive
		conditions[1] = false;	// equals
		conditions[2] = false;	// contains
		conditions[3] = true;	// startsWith
		conditions[4] = false;	// endsWith
		conditions[5] = true;	// allFiles
		conditions[6] = false;	// types
		conditions[7] = false;	// methods
		conditions[8] = false;	// fields
		
		SearchVisitor searchVisitor = new SearchVisitor(conditions, word, packageItem);	
		packageElement.traverse(searchVisitor);
		
		SearchView.showTree(searchVisitor.pathClass, searchVisitor.packageClass, searchVisitor.types, searchVisitor.fileLines, searchVisitor.methods, searchVisitor.fields);
	}
	
	@Override
	public void searchAllFileEndsWith(String word, String packageItem) {
		Assert.isNotNull(word, "word cannot be null");
		Assert.isNotNull(packageItem, "packageItem cannot be null");
		
		boolean[] conditions = new boolean[9];
		
		conditions[0] = false; 	// caseSensitive
		conditions[1] = false;	// equals
		conditions[2] = false;	// contains
		conditions[3] = false;	// startsWith
		conditions[4] = true;	// endsWith
		conditions[5] = true;	// allFiles
		conditions[6] = false;	// types
		conditions[7] = false;	// methods
		conditions[8] = false;	// fields
		
		SearchVisitor searchVisitor = new SearchVisitor(conditions, word, packageItem);	
		packageElement.traverse(searchVisitor);
		
		SearchView.showTree(searchVisitor.pathClass, searchVisitor.packageClass, searchVisitor.types, searchVisitor.fileLines, searchVisitor.methods, searchVisitor.fields);
	}
	
	@Override
	public void searchEquals(String word, String packageItem, boolean types, boolean methods, boolean fields) {
		Assert.isNotNull(word, "word cannot be null");
		Assert.isNotNull(packageItem, "packageItem cannot be null");
		
		boolean[] conditions = new boolean[9];
		
		conditions[0] = false; 		// caseSensitive
		conditions[1] = true;		// equals
		conditions[2] = false;		// contains
		conditions[3] = false;		// startsWith
		conditions[4] = false;		// endsWith
		conditions[5] = false;		// allFiles
		conditions[6] = types;		// types
		conditions[7] = methods;	// methods
		conditions[8] = fields;		// fields
		
		SearchVisitor searchVisitor = new SearchVisitor(conditions, word, packageItem);	
		packageElement.traverse(searchVisitor);
		
		SearchView.showTree(searchVisitor.pathClass, searchVisitor.packageClass, searchVisitor.types, searchVisitor.fileLines, searchVisitor.methods, searchVisitor.fields);
	}
	
	@Override
	public void searchEqualsCaseSensitive(String word, String packageItem, boolean types, boolean methods, boolean fields) {
		Assert.isNotNull(word, "word cannot be null");
		Assert.isNotNull(packageItem, "packageItem cannot be null");
		
		boolean[] conditions = new boolean[9];
		
		conditions[0] = true; 		// caseSensitive
		conditions[1] = true;		// equals
		conditions[2] = false;		// contains
		conditions[3] = false;		// startsWith
		conditions[4] = false;		// endsWith
		conditions[5] = false;		// allFiles
		conditions[6] = types;		// types
		conditions[7] = methods;	// methods
		conditions[8] = fields;		// fields
		
		SearchVisitor searchVisitor = new SearchVisitor(conditions, word, packageItem);	
		packageElement.traverse(searchVisitor);
		
		SearchView.showTree(searchVisitor.pathClass, searchVisitor.packageClass, searchVisitor.types, searchVisitor.fileLines, searchVisitor.methods, searchVisitor.fields);
	}
	
	@Override
	public void searchContains(String word, String packageItem, boolean types, boolean methods, boolean fields) {
		Assert.isNotNull(word, "word cannot be null");
		Assert.isNotNull(packageItem, "packageItem cannot be null");
		
		boolean[] conditions = new boolean[9];
		
		conditions[0] = false; 		// caseSensitive
		conditions[1] = false;		// equals
		conditions[2] = true;		// contains
		conditions[3] = false;		// startsWith
		conditions[4] = false;		// endsWith
		conditions[5] = false;		// allFiles
		conditions[6] = types;		// types
		conditions[7] = methods;	// methods
		conditions[8] = fields;		// fields
		
		SearchVisitor searchVisitor = new SearchVisitor(conditions, word, packageItem);	
		packageElement.traverse(searchVisitor);
		
		SearchView.showTree(searchVisitor.pathClass, searchVisitor.packageClass, searchVisitor.types, searchVisitor.fileLines, searchVisitor.methods, searchVisitor.fields);
	}
	
	@Override
	public void searchStartsWith(String word, String packageItem, boolean types, boolean methods, boolean fields) {
		Assert.isNotNull(word, "word cannot be null");
		Assert.isNotNull(packageItem, "packageItem cannot be null");
		
		boolean[] conditions = new boolean[9];
		
		conditions[0] = false; 		// caseSensitive
		conditions[1] = false;		// equals
		conditions[2] = false;		// contains
		conditions[3] = true;		// startsWith
		conditions[4] = false;		// endsWith
		conditions[5] = false;		// allFiles
		conditions[6] = types;		// types
		conditions[7] = methods;	// methods
		conditions[8] = fields;		// fields
		
		SearchVisitor searchVisitor = new SearchVisitor(conditions, word, packageItem);	
		packageElement.traverse(searchVisitor);
		
		SearchView.showTree(searchVisitor.pathClass, searchVisitor.packageClass, searchVisitor.types, searchVisitor.fileLines, searchVisitor.methods, searchVisitor.fields);
	}
	
	@Override
	public void searchEndsWith(String word, String packageItem, boolean types, boolean methods, boolean fields) {
		Assert.isNotNull(word, "word cannot be null");
		Assert.isNotNull(packageItem, "packageItem cannot be null");
		
		boolean[] conditions = new boolean[9];
		
		conditions[0] = false; 		// caseSensitive
		conditions[1] = false;		// equals
		conditions[2] = false;		// contains
		conditions[3] = false;		// startsWith
		conditions[4] = true;		// endsWith
		conditions[5] = false;		// allFiles
		conditions[6] = types;		// types
		conditions[7] = methods;	// methods
		conditions[8] = fields;		// fields
		
		SearchVisitor searchVisitor = new SearchVisitor(conditions, word, packageItem);	
		packageElement.traverse(searchVisitor);
		
		SearchView.showTree(searchVisitor.pathClass, searchVisitor.packageClass, searchVisitor.types, searchVisitor.fileLines, searchVisitor.methods, searchVisitor.fields);
	}
}
