package pt.iscte.pidesco.search;

import java.awt.Canvas;
import java.awt.List;
import java.io.File;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import pt.iscte.pidesco.demo.extensibility.DemoAction;
import pt.iscte.pidesco.extensibility.PidescoView;
import pt.iscte.pidesco.javaeditor.service.JavaEditorServices;
import pt.iscte.pidesco.projectbrowser.model.SourceElement;
import pt.iscte.pidesco.projectbrowser.service.ProjectBrowserListener;
import pt.iscte.pidesco.projectbrowser.service.ProjectBrowserServices;

public class Search implements PidescoView {

	@Override
	public void createContents(Composite viewArea, Map<String, Image> imageMap) {

		Label space;
		
		viewArea.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
		viewArea.setLayout(new GridLayout(6, false));
		
		
		
		
		Label searchBarTitle = new Label(viewArea, SWT.NONE);
		searchBarTitle.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 6, 1));
		searchBarTitle.setText("Search Bar");

		Text searchBar = new Text(viewArea, SWT.BORDER);
		searchBar.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 6, 1));

		
		
		
		Button caseSensitive = new Button(viewArea, SWT.CHECK);
		caseSensitive.setText("Case Sensitive");
		
		space = new Label(viewArea, SWT.NONE);
		
		Composite composite0 = new Composite(viewArea, SWT.NONE);
		composite0.setLayout(new GridLayout(4, false));
		composite0.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 4, 1));

		Button equals = new Button(composite0, SWT.CHECK);
		equals.setText(" Equals  ");
		
		Button contains = new Button(composite0, SWT.CHECK);
		contains.setText(" Contains  ");
		
		Button startsWith = new Button(composite0, SWT.CHECK);
		startsWith.setText(" Starts With  ");
		
		Button endsWith = new Button(composite0, SWT.CHECK);
		endsWith.setText(" Ends With");
		
		
		
		
		space = new Label(viewArea, SWT.NONE);
		space.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 6, 1));
		
		
		
	
		Label fileNameTitle = new Label(viewArea, SWT.NONE);
		fileNameTitle.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 6, 1));
		fileNameTitle.setText("File Name (Ex.: file1; file2; ...)");
		
		Combo fileName = new Combo(viewArea, SWT.BORDER);
		fileName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 5, 1));
		
		Button select = new Button(viewArea, SWT.NONE);
		select.setText("     Select     ");
		
		
		
		
		space = new Label(viewArea, SWT.NONE);
		space.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 6, 1));
		
		
		
		
		Composite composite1 = new Composite(viewArea, SWT.NONE);
		composite1.setLayout(new GridLayout(6, false));
		composite1.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 6, 1));

		Button type = new Button(composite1, SWT.CHECK);
		type.setText(" Type  ");
		
		Button packag = new Button(composite1, SWT.CHECK);
		packag.setText(" Package  ");
		
		Button module = new Button(composite1, SWT.CHECK);
		module.setText(" Module  ");
		
		Button method = new Button(composite1, SWT.CHECK);
		method.setText(" Method  ");
		
		Button constructor = new Button(composite1, SWT.CHECK);
		constructor.setText(" Constructor  ");
		
		Button field = new Button(composite1, SWT.CHECK);
		field.setText(" Field");
		
		
		
		
		
		space = new Label(viewArea, SWT.NONE);
		space.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 6, 1));
		
		
		
		
		Composite composite2 = new Composite(viewArea, SWT.NONE);
		composite2.setLayout(new GridLayout(6, false));
		composite2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 6, 1));
		
		Label replaceTitle = new Label(composite2, SWT.NONE);
		replaceTitle.setText("Replace: ");
		
		Text replace = new Text(composite2, SWT.BORDER);
		replace.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 4, 1));

		space = new Label(composite2, SWT.NONE);
		space.setText("                      ");
		
		Label withTitle = new Label(composite2, SWT.NONE);
		withTitle.setText("With:    ");
		
		Text with = new Text(composite2, SWT.BORDER);
		with.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 4, 1));

		space = new Label(composite2, SWT.NONE);
		space.setText("                      ");
		
		
		
		
		space = new Label(viewArea, SWT.NONE);
		space.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 6, 1));
		
		
		
		
		space = new Label(viewArea, SWT.NONE);		
		space = new Label(viewArea, SWT.NONE);		
		space = new Label(viewArea, SWT.NONE);

		Composite composite3 = new Composite(viewArea, SWT.NONE);
		composite3.setLayout(new GridLayout(3, false));
		composite3.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 3, 1));
		
		Button replaceButton = new Button(composite3, SWT.NONE);
		replaceButton.setText("    Replace    ");
		
		Button searchButton = new Button(composite3, SWT.NONE);
		searchButton.setText("    Search     ");
		
		Button cancelButton = new Button(composite3, SWT.NONE);
		cancelButton.setText("    Cancel     ");
		
		

		
		replaceButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				
			}
		});
	
		searchButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				
			}
		});
		
		cancelButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				
			}
		});
	}
}
