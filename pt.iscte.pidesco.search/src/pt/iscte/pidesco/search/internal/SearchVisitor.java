package pt.iscte.pidesco.search.internal;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import pt.iscte.pidesco.javaeditor.service.JavaEditorServices;
import pt.iscte.pidesco.projectbrowser.model.ClassElement;
import pt.iscte.pidesco.projectbrowser.model.PackageElement;
import pt.iscte.pidesco.projectbrowser.model.PackageElement.Visitor;

public class SearchVisitor implements Visitor {

	private List<String> packageClass = new ArrayList<String>();
	
	public List<String> getPackageClass() {
		return packageClass;
	}

	@Override
	public boolean visitPackage(PackageElement packageElement) {
		return true;
	}
	
	@Override
	public void visitClass(ClassElement classElement) {
		
		BundleContext context = Activator.getContext();
		ServiceReference<JavaEditorServices> serviceReference = context.getServiceReference(JavaEditorServices.class);
		JavaEditorServices javaEditorServices = context.getService(serviceReference);
		
		File file = classElement.getFile();
		
		SearchAtsVisitor searchAtsVisitor = new SearchAtsVisitor();
		searchAtsVisitor.readFile(file);
		
		javaEditorServices.parseFile(file, searchAtsVisitor);
		
		System.out.println(searchAtsVisitor.toString());	
		
		packageClass.add(classElement.getClass().getPackage().getName() + ".." + classElement.getName());
	}
}
