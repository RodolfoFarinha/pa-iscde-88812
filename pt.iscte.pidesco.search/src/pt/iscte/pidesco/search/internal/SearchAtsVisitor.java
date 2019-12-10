package pt.iscte.pidesco.search.internal;

import java.awt.Window.Type;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.ConstructorInvocation;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

import pt.iscte.pidesco.projectbrowser.model.ClassElement;

public class SearchAtsVisitor extends ASTVisitor {

	File file;
	
	List<String> types = new ArrayList<String>();
	List<String> constructors = new ArrayList<String>();
	List<String> methods = new ArrayList<String>();
	List<String> fields = new ArrayList<String>();

	@Override
	public boolean visit(TypeDeclaration node) {
		String name = node.getName().toString();
	    int line = sourceLine(node);
	    types.add(line + " - " + name);
		return true;
	}
	
	@Override
	public boolean visit(ConstructorDeclaration node) {
		String name = node.toString();
	    int line = sourceLine(node);
	    constructors.add(line + " - " + name);
		return true;
	}
	
	@Override
	public boolean visit(MethodDeclaration node) {
		String name = node.getName().toString();
	    int line = sourceLine(node);
	    methods.add(line + " - " + name);
		return true;
	}
	
	@Override
	public boolean visit(FieldDeclaration node) {
		
		for(Object o : node.fragments()) {
		
			VariableDeclarationFragment var = (VariableDeclarationFragment) o;				
			String name = var.getName().toString();
			int line = sourceLine(node);
			
			/*if(name.equals("PLUGIN_ID")) {
				result.add(name);
				System.out.println(name);
			}*/
			
			fields.add(line + " - " + name);
		}

		return false;
	}
	
	private static int sourceLine(ASTNode node) {
		return ((CompilationUnit) node.getRoot()).getLineNumber(node.getStartPosition());
	}
	
	@Override
    public String toString() { 
        return String.format("Tipos:" + types + "\n" + "Construtores:" + constructors + "\n" + "Metodos:" + methods + "\n" + "Campos:" + fields); 
    } 
}
