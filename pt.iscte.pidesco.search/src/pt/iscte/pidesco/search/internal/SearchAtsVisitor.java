package pt.iscte.pidesco.search.internal;

import java.awt.Window.Type;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

	private boolean[] conditions;
	private String word, packageItem;
	private SearchVisitor searchVisitor;
	private ClassElement classElement;

	public SearchAtsVisitor(SearchVisitor searchvisitor, ClassElement classElement, boolean[] conditions, String word, String packageItem) {
		this.searchVisitor = searchvisitor;
		this.classElement = classElement;
		this.conditions = conditions;
		this.word = word;
		this.packageItem = packageItem;
	}

	public void readFile(File file) {
		if (conditions[5]) {
			try {
				LineNumberReader reader = new LineNumberReader(new FileReader(file));
				String line;

				while ((line = reader.readLine()) != null) {
					String lineWords = "";
					Scanner scanner = new Scanner(line);
					scanner.useDelimiter("\\.|,|\\(|\\)|\\s+|;");

					while (scanner.hasNext()) {
						
						String scannerWord = scanner.next();
						
						if(conditions[0] && conditions[1] && scannerWord.equals(word)) {
							lineWords = lineWords + scannerWord + " ";
						}
						else if(!conditions[0] && conditions[1] && scannerWord.equalsIgnoreCase(word)) {
							lineWords = lineWords + scannerWord + " ";
						}
						else if(conditions[2] && scannerWord.contains(word)) {
							lineWords = lineWords + scannerWord + " ";
						}
						else if(conditions[3] && scannerWord.startsWith(word)) {
							lineWords = lineWords + scannerWord + " ";
						}
						else if(conditions[4] && scannerWord.endsWith(word)) {			
							lineWords = lineWords + scannerWord + " ";
						}
					}
					if(lineWords.trim().length() > 0) {
						addClass();
						searchVisitor.fileLines.add(classElement.getName() + "::Line: " + reader.getLineNumber() + " - " + lineWords.trim());
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public boolean visit(TypeDeclaration node) {
		if (conditions[6]) {
			
			String name = node.getName().toString();
			int line = sourceLine(node);
			
			if(conditions[0] && conditions[1] && name.equals(word)) {
				addClass();
				searchVisitor.types.add(classElement.getName() + "::Line: " + line + " - " + name);
			}
			else if(!conditions[0] && conditions[1] && name.equalsIgnoreCase(word)) {
				addClass();
				searchVisitor.types.add(classElement.getName() + "::Line: " + line + " - " + name);
			}
			else if(conditions[2] && name.contains(word)) {
				addClass();
				searchVisitor.types.add(classElement.getName() + "::Line: " + line + " - " + name);
			}
			else if(conditions[3] && name.startsWith(word)) {
				addClass();
				searchVisitor.types.add(classElement.getName() + "::Line: " + line + " - " + name);
			}
			else if(conditions[4] && name.endsWith(word)) {
				addClass();
				searchVisitor.types.add(classElement.getName() + "::Line: " + line + " - " + name);
			}
		}
		return true;
	}

	@Override
	public boolean visit(MethodDeclaration node) {
		if (conditions[7]) {
			
			String name = node.getName().toString();
			int line = sourceLine(node);
			
			if(conditions[0] && conditions[1] && name.equals(word)) {
				addClass();
				searchVisitor.methods.add(classElement.getName() + "::Line: " + line + " - " + name);
			}
			else if(!conditions[0] && conditions[1] && name.equalsIgnoreCase(word)) {
				addClass();
				searchVisitor.methods.add(classElement.getName() + "::Line: " + line + " - " + name);
			}
			else if(conditions[2] && name.contains(word)) {
				addClass();
				searchVisitor.methods.add(classElement.getName() + "::Line: " + line + " - " + name);
			}
			else if(conditions[3] && name.startsWith(word)) {
				addClass();
				searchVisitor.methods.add(classElement.getName() + "::Line: " + line + " - " + name);
			}
			else if(conditions[4] && name.endsWith(word)) {
				addClass();
				searchVisitor.methods.add(classElement.getName() + "::Line: " + line + " - " + name);
			}
		}
		return true;
	}

	@Override
	public boolean visit(FieldDeclaration node) {
		if (conditions[8]) {
			for (Object o : node.fragments()) {
				VariableDeclarationFragment var = (VariableDeclarationFragment) o;
				String name = var.getName().toString();
				int line = sourceLine(node);

				if(conditions[0] && conditions[1] && name.equals(word)) {
					addClass();
					searchVisitor.fields.add(classElement.getName() + "::Line: " + line + " - " + name);
				}
				else if(!conditions[0] && conditions[1] && name.equalsIgnoreCase(word)) {
					addClass();
					searchVisitor.fields.add(classElement.getName() + "::Line: " + line + " - " + name);
				}
				else if(conditions[2] && name.contains(word)) {
					addClass();
					searchVisitor.fields.add(classElement.getName() + "::Line: " + line + " - " + name);
				}
				else if(conditions[3] && name.startsWith(word)) {
					addClass();
					searchVisitor.fields.add(classElement.getName() + "::Line: " + line + " - " + name);
				}
				else if(conditions[4] && name.endsWith(word)) {
					addClass();
					searchVisitor.fields.add(classElement.getName() + "::Line: " + line + " - " + name);
				}
			}
		}
		return false;
	}
	
	private void addClass() {
		
		String pathClass = classElement.getFile().getPath().substring(classElement.getFile().getPath().lastIndexOf("pt"), classElement.getFile().getPath().lastIndexOf("\\")).replace("\\", ".");		
		
		if(!packageItem.isEmpty() && !pathClass.equals(packageItem))
			return;
		else {
			if(!searchVisitor.packageClass.contains(pathClass + ".." + classElement.getName())) {		
				searchVisitor.packageClass.add(pathClass + ".." + classElement.getName());
				searchVisitor.pathClass.add(classElement.getFile().getPath());	
			}
		}
	}

	private int sourceLine(ASTNode node) {
		return ((CompilationUnit) node.getRoot()).getLineNumber(node.getStartPosition());
	}
}
