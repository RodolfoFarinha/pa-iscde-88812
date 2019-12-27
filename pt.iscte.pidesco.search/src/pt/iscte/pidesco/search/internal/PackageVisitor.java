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

public class PackageVisitor implements Visitor {

	List<String> packageClass = new ArrayList<String>();
	
	@Override
	public boolean visitPackage(PackageElement packageElement) {
		return true;
	}
	
	@Override
	public void visitClass(ClassElement classElement) {
		
		String packageItem = classElement.getFile().getPath().substring(classElement.getFile().getPath().lastIndexOf("pt"), classElement.getFile().getPath().lastIndexOf("\\")).replace("\\", ".");	
		
		if(!packageClass.contains(packageItem))
			packageClass.add(packageItem);		
				
	}
}
