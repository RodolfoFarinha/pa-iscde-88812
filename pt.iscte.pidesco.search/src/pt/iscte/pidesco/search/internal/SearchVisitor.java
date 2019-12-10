package pt.iscte.pidesco.search.internal;

import java.io.File;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import pt.iscte.pidesco.javaeditor.service.JavaEditorServices;
import pt.iscte.pidesco.projectbrowser.model.ClassElement;
import pt.iscte.pidesco.projectbrowser.model.PackageElement;
import pt.iscte.pidesco.projectbrowser.model.PackageElement.Visitor;

public class SearchVisitor implements Visitor {

	
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
		javaEditorServices.parseFile(file, searchAtsVisitor);
		
		System.out.println(searchAtsVisitor.toString());		
	}
}
