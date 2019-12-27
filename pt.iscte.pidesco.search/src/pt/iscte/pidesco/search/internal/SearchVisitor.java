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

	private boolean [] conditions;
	private String word, packageItem;
	
	List<String> pathClass = new ArrayList<String>();
	List<String> packageClass = new ArrayList<String>();
	List<String> types = new ArrayList<String>();
	List<String> fileLines = new ArrayList<String>();
	List<String> methods = new ArrayList<String>();
	List<String> fields = new ArrayList<String>();
	
	public SearchVisitor(boolean[] conditions, String word, String packageItem) {
		this.conditions = conditions;
		this.word = word;
		this.packageItem = packageItem;
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
		
		SearchAtsVisitor searchAtsVisitor = new SearchAtsVisitor(this, classElement, conditions, word, packageItem);
		searchAtsVisitor.readFile(file);
		
		javaEditorServices.parseFile(file, searchAtsVisitor);
	}
}
