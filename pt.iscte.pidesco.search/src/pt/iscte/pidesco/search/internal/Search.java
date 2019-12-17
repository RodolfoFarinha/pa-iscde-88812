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
	
	BundleContext context = Activator.getContext();
	private boolean [] conditions;
	
	public Search(boolean [] conditions) {
		this.conditions = conditions;
	}
	
	public void search() {

		ServiceReference<ProjectBrowserServices> serviceReference = context.getServiceReference(ProjectBrowserServices.class);
		ProjectBrowserServices projectBrowserServices = context.getService(serviceReference);
		
		PackageElement packageElement = projectBrowserServices.getRootPackage();		
		
		SearchVisitor searchVisitor = new SearchVisitor();	
		packageElement.traverse(searchVisitor);
		
		SearchView.showTree(searchVisitor.getPackageClass());
	}
}
