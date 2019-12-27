package pt.iscte.pidesco.search.internal;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import pt.iscte.pidesco.javaeditor.service.JavaEditorServices;
import pt.iscte.pidesco.projectbrowser.service.ProjectBrowserServices;
import pt.iscte.pidesco.projectbrowser.model.ClassElement;
import pt.iscte.pidesco.projectbrowser.model.PackageElement;
import pt.iscte.pidesco.projectbrowser.model.PackageElement.Visitor;

public class Search {
	
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
}
