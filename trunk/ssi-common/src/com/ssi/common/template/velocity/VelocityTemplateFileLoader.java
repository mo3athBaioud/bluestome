package com.ssi.common.template.velocity;

import java.io.InputStream;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.resource.Resource;
import org.apache.velocity.runtime.resource.loader.FileResourceLoader;

public class VelocityTemplateFileLoader extends FileResourceLoader {

	@Override
	public InputStream getResourceStream(String arg0) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		return super.getResourceStream(arg0);
	}

	@Override
	public long getLastModified(Resource resource) {
		return 0;
	}

	@Override
	public boolean isSourceModified(Resource resource) {
		// TODO Auto-generated method stub
		if (log.isDebugEnabled()) {
			log.debug("Checking if the source had been modified");
		}
		return (resource.getLastModified() != getLastModified(resource));
	}
	
	
	
}
