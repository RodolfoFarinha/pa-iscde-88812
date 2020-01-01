package pt.iscte.pidesco.search.internal;

import java.util.HashSet;
import java.util.Set;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import pt.iscte.pidesco.extensibility.PidescoServices;
import pt.iscte.pidesco.search.internal.SearchActivator;
import pt.iscte.pidesco.search.internal.SearchServicesImpl;
import pt.iscte.pidesco.search.service.SearchServices;

public class SearchActivator implements BundleActivator {

	private static SearchActivator instance;
	//private Set<ProjectBrowserListener> listeners;
	private ServiceRegistration<SearchServices> service;
	private PidescoServices pidescoServices;
	
	private static BundleContext context;
	
	static BundleContext getContext() {
		return context;
	}
	
	@Override	
	public void start(BundleContext bundleContext) throws Exception {
		SearchActivator.context = bundleContext;		
		instance = this;
		//listeners = new HashSet<>();
		service = context.registerService(SearchServices.class, new SearchServicesImpl(), null);

		ServiceReference<PidescoServices> ref = context.getServiceReference(PidescoServices.class);
		pidescoServices = context.getService(ref);
	}

	public void stop(BundleContext bundleContext) throws Exception {
		SearchActivator.context = null;
	}
}
