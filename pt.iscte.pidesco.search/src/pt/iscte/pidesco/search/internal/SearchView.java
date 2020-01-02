package pt.iscte.pidesco.search.internal;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import pt.iscte.pidesco.extensibility.PidescoView;
import pt.iscte.pidesco.javaeditor.service.JavaEditorServices;
import pt.iscte.pidesco.outline.internal.OutlineservicesImpl;
import pt.iscte.pidesco.outline.service.OutlineServices;
import pt.iscte.pidesco.search.extensibility.SearchRefactor;
import pt.iscte.pidesco.search.service.SearchServices;


public class SearchView implements PidescoView {
		
	private Search search = new Search();
	private static Tree tree;
	private static Image searchIcon, packageIcon, classIcon, folderIcon, lineIcon;
	private static String pathName, packageName, itemName, className;
	private static List<String> pathClass, packageClass, types, fileLines, methods, fields;
	private static TreeItem searchItem;
	private static boolean treeBool;
	private static int i;
	
	static Text searchBar;
	static Combo fileName;
	static Button caseSensitive, equals, contains, startsWith, endsWith, allFile, type, method, field;
	
	@Override
	public void createContents(Composite viewArea, Map<String, Image> imageMap) {

		Label space;
		searchIcon = imageMap.get("find_obj.gif");
		packageIcon = imageMap.get("package.gif");
		classIcon = imageMap.get("class.gif");
		folderIcon = imageMap.get("folder.gif");
		lineIcon = imageMap.get("line.gif");

		viewArea.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
		viewArea.setLayout(new GridLayout(6, false));

		Label searchBarTitle = new Label(viewArea, SWT.NONE);
		searchBarTitle.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 6, 1));
		searchBarTitle.setText("Search Bar");

		searchBar = new Text(viewArea, SWT.BORDER);
		searchBar.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 6, 1));

		caseSensitive = new Button(viewArea, SWT.CHECK);
		caseSensitive.setText("Case Sensitive");

		space = new Label(viewArea, SWT.NONE);

		Composite composite0 = new Composite(viewArea, SWT.NONE);
		composite0.setLayout(new GridLayout(4, false));
		composite0.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 4, 1));

		equals = new Button(composite0, SWT.CHECK);
		equals.setText(" Equals  ");
		equals.setSelection(true);
		caseSensitive.setSelection(true);
		
		contains = new Button(composite0, SWT.CHECK);
		contains.setText(" Contains  ");

		startsWith = new Button(composite0, SWT.CHECK);
		startsWith.setText(" Starts With  ");

		endsWith = new Button(composite0, SWT.CHECK);
		endsWith.setText(" Ends With");
		
		caseSensitive.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if(equals.getSelection() && caseSensitive.getSelection())
					caseSensitive.setSelection(true);
				else
					caseSensitive.setSelection(false);
			}
		});
		
		equals.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				equals.setSelection(true);
				contains.setSelection(false);
				startsWith.setSelection(false);
				endsWith.setSelection(false);
			}
		});
		
		contains.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				equals.setSelection(false);
				contains.setSelection(true);
				startsWith.setSelection(false);
				endsWith.setSelection(false);
				caseSensitive.setSelection(false);
			}
		});
		
		startsWith.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				equals.setSelection(false);
				contains.setSelection(false);
				startsWith.setSelection(true);
				endsWith.setSelection(false);
				caseSensitive.setSelection(false);
			}
		});
		
		endsWith.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				equals.setSelection(false);
				contains.setSelection(false);
				startsWith.setSelection(false);
				endsWith.setSelection(true);
				caseSensitive.setSelection(false);
			}
		});

		space = new Label(viewArea, SWT.NONE);
		space.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 6, 1));

		Label fileNameTitle = new Label(viewArea, SWT.NONE);
		fileNameTitle.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 6, 1));
		fileNameTitle.setText("Package Name");

		fileName = new Combo(viewArea, SWT.BORDER);
		fileName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 6, 1));

  	  	search.getPackage();
		    
		space = new Label(viewArea, SWT.NONE);
		space.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 6, 1));

		Composite composite1 = new Composite(viewArea, SWT.NONE);
		composite1.setLayout(new GridLayout(6, false));
		composite1.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 4, 1));

		allFile = new Button(composite1, SWT.CHECK);
		allFile.setText(" All File  ");
		
		type = new Button(composite1, SWT.CHECK);
		type.setText(" Type  ");

		method = new Button(composite1, SWT.CHECK);
		method.setText(" Method  ");

		field = new Button(composite1, SWT.CHECK);
		field.setText(" Field");

		allFile.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				type.setSelection(false);
				method.setSelection(false);
				field.setSelection(false);
			}
		});
		
		type.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if(!allFile.getSelection() && type.getSelection())
					type.setSelection(true);
				else
					type.setSelection(false);
			}
		});
		
		method.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if(!allFile.getSelection() && method.getSelection())
					method.setSelection(true);
				else
					method.setSelection(false);
			}
		});
		
		field.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if(!allFile.getSelection() && field.getSelection())
					field.setSelection(true);
				else
					field.setSelection(false);
			}
		});
		
		space = new Label(viewArea, SWT.NONE);
		space.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 6, 1));

		space = new Label(viewArea, SWT.NONE);
		space = new Label(viewArea, SWT.NONE);
		space = new Label(viewArea, SWT.NONE);
		space = new Label(viewArea, SWT.NONE);

		Composite composite3 = new Composite(viewArea, SWT.NONE);
		composite3.setLayout(new GridLayout(3, false));
		composite3.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 2, 1));

		Button searchButton = new Button(composite3, SWT.NONE);
		searchButton.setText("    Search     ");

		Button clearButton = new Button(composite3, SWT.NONE);
		clearButton.setText("    Clear     ");

		searchButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {

				tree.removeAll();
				
				boolean[] conditions = new boolean[9];
				
				String word = searchBar.getText().trim();
				String packageItem = fileName.getText().trim();
				
				conditions[0] = caseSensitive.getSelection();
				conditions[1] = equals.getSelection();
				conditions[2] = contains.getSelection();
				conditions[3] = startsWith.getSelection();
				conditions[4] = endsWith.getSelection();
				conditions[5] = allFile.getSelection();
				conditions[6] = type.getSelection();
				conditions[7] = method.getSelection();
				conditions[8] = field.getSelection();
			
				search.search(conditions, word, packageItem);
					
				if(conditions[7]) {
					if(!methods.isEmpty()) {
						for(String methodname : methods) {
							OutlineServices os = new OutlineservicesImpl();
							methodname = methodname.substring(methodname.indexOf(" - ")+3);				
							os.highlightText(methodname);
						}
					}
				}
			}
		});

		clearButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				tree.removeAll();
				fileName.setText("");
				searchBar.setText("");
							
				BundleContext context = SearchActivator.getContext();
				ServiceReference<SearchServices> serviceReference = context.getServiceReference(SearchServices.class);
				SearchServices searchServices = context.getService(serviceReference);
				
				searchServices.searchContains(search.getPackageElement(), "A", "",true,true,true);
			}
		});

		tree = new Tree(viewArea, SWT.BORDER | SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		tree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 6, 1));
		
		tree.addMouseListener(new MouseAdapter() {
			public void mouseDoubleClick(MouseEvent e) {		
				
				if(tree.getSelectionCount() > 0) {
					TreeItem[] treeItems = tree.getSelection();

					if(treeItems[0].getImage().equals(lineIcon)) {
						TreeItem treeFolder = treeItems[0].getParentItem();
						TreeItem treeClass = treeFolder.getParentItem();
						
						BundleContext context = SearchActivator.getContext();
						ServiceReference<JavaEditorServices> serviceReference = context.getServiceReference(JavaEditorServices.class);
						JavaEditorServices javaEditorServices = context.getService(serviceReference);
						
						File file = new File(treeClass.getData(treeClass.getText()).toString());

						javaEditorServices.openFile(file);
						
						try {

				            LineNumberReader reader = new LineNumberReader(new FileReader(file));
							
							int numberLine = Integer.parseInt(treeItems[0].getText().substring(treeItems[0].getText().indexOf("Line: ") + 6, treeItems[0].getText().indexOf(" - ")));
							String fileReader = "";
							String lineReader;
							
							while ((lineReader = reader.readLine()) != null) {
								fileReader = fileReader + "\n" + lineReader;
								if(reader.getLineNumber() == numberLine) {
									javaEditorServices.selectText(file, fileReader.indexOf(lineReader, 0)-1, lineReader.length()-1);
								}	
							}														
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});
	}
	
	public static void addItemsCombo(List<String> packageClassList) {	
		for(String item : packageClassList) 
			fileName.add(item);
	}

	public static void showTree(List<String> pathClassList, List<String> packageClassList, List<String> typesList, List<String> fileLinesList, List<String> methodsList, List<String> fieldsList) {	
		
		searchItem = new TreeItem(tree, SWT.NONE, 0);
		searchItem.setText("Search Results");
		searchItem.setImage(searchIcon);
		
		if(!packageClassList.isEmpty()) {
			pathClass = pathClassList;
			packageClass = packageClassList;
			types = typesList;
			fileLines = fileLinesList;
			methods = methodsList;
			fields = fieldsList;
			
			Collections.reverse(types);
			Collections.reverse(fileLines);
			Collections.reverse(methods);
			Collections.reverse(fields);
					
			i = 0;
			treeBool = true;
			treeItem(searchItem);
		}
	}

	private static void treeItem(TreeItem tree) {
		if (treeBool) {
			packageName = packageClass.get(i).substring(0, packageClass.get(i).lastIndexOf("..") + 2);
			itemName = packageClass.get(i).substring(0, packageClass.get(i).indexOf("."));
			className = packageClass.get(i).substring(packageClass.get(i).lastIndexOf("..") + 2);
			pathName = pathClass.get(i);

			i++;
			treeBool = false;
		}

		for (TreeItem treeItem : tree.getItems()) {
			if (itemName.equals(treeItem.getText())) {
				packageName = packageName.substring(packageName.indexOf(".") + 1);
				if (packageName.length() != 1) {
					itemName = packageName.substring(0, packageName.indexOf("."));
					treeItem(treeItem);
				} else {
					createClassItem(treeItem);
					treeBool = true;		
					if(i < packageClass.size())
						treeItem(searchItem);
					else
						return;
				}
			}
		}
		
		if(!treeBool) {
			createPackageItem(tree);
			treeItem(tree);
		}
	}

	private static void createPackageItem(TreeItem treeItem) {
		TreeItem packageItem = new TreeItem(treeItem, SWT.NONE, 0);
		packageItem.setText(itemName);
		packageItem.setImage(packageIcon);
	}

	private static void createClassItem(TreeItem packageItem) {
		TreeItem classItem = new TreeItem(packageItem, SWT.NONE, 0);
		classItem.setText(className);
		classItem.setData(className, pathName);
		classItem.setImage(classIcon);
		
		if(!types.isEmpty()) {
			TreeItem folderItem = new TreeItem(classItem, SWT.NONE, 0);
			folderItem.setText("Types");
			folderItem.setImage(folderIcon);
			
			for(String type : types) {
				if(className.equals(type.substring(0, type.indexOf("::"))))								
						createLineItem(folderItem, type);
			}
		}

		if(!fileLines.isEmpty()) {		
			TreeItem folderItem = new TreeItem(classItem, SWT.NONE, 0);
			folderItem.setText("All Lines");
			folderItem.setImage(folderIcon);
			
			for(String fileLine : fileLines) {
				if(className.equals(fileLine.substring(0, fileLine.indexOf("::"))))								
						createLineItem(folderItem, fileLine);
			}
		}
		
		if(!methods.isEmpty()) {		
			TreeItem folderItem = new TreeItem(classItem, SWT.NONE, 0);
			folderItem.setText("Methods");
			folderItem.setImage(folderIcon);
			
			for(String method : methods) {
				if(className.equals(method.substring(0, method.indexOf("::"))))								
						createLineItem(folderItem, method);
			}
		}
		
		if(!fields.isEmpty()) {
			TreeItem folderItem = new TreeItem(classItem, SWT.NONE, 0);
			folderItem.setText("Fields");
			folderItem.setImage(folderIcon);
			
			for(String field : fields) {
				if(className.equals(field.substring(0, field.indexOf("::"))))								
						createLineItem(folderItem, field);
			}
		}
	}
	
	private static void createLineItem(TreeItem classItem, String line) {
		TreeItem lineItem = new TreeItem(classItem, SWT.NONE, 0);
		lineItem.setText(line.substring(line.indexOf("::") + 2));
		lineItem.setImage(lineIcon);
	}
}
