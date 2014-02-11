package edu.psu.iam.cpr.rest.returns;

import javax.xml.bind.annotation.XmlRootElement;

import edu.psu.iam.cpr.core.api.returns.ServiceReturn;

@XmlRootElement
public class WriteApiReturn {
	
	/** Contains the meta information */
	private ResourceMetadata resourceMetadata;
	
	/** Contains the response meta information */
	private ResponseMeta responseMetadata;
	
	/** Contains the service return. */
	private ServiceReturn resource;

	/**
	 * Constructor.
	 */
	public WriteApiReturn() {
		super();
	}

	/**
	 * Constructor.
	 * @param resourceMetadata contains the resource information metadata.
	 * @param responseMetadata contains the response metadata.
	 * @param resource contains the actual resource.
	 */
	public WriteApiReturn(ResourceMetadata resourceMetadata,
			ResponseMeta responseMetadata, ServiceReturn resource) {
		super();
		this.resourceMetadata = resourceMetadata;
		this.responseMetadata = responseMetadata;
		this.resource = resource;
	}

	/**
	 * @return the resourceMetadata
	 */
	public ResourceMetadata getResourceMetadata() {
		return resourceMetadata;
	}

	/**
	 * @param resourceMetadata the resourceMetadata to set
	 */
	public void setResourceMetadata(ResourceMetadata resourceMetadata) {
		this.resourceMetadata = resourceMetadata;
	}

	/**
	 * @return the responseMetadata
	 */
	public ResponseMeta getResponseMetadata() {
		return responseMetadata;
	}

	/**
	 * @param responseMetadata the responseMetadata to set
	 */
	public void setResponseMetadata(ResponseMeta responseMetadata) {
		this.responseMetadata = responseMetadata;
	}

	/**
	 * @return the resource
	 */
	public ServiceReturn getResource() {
		return resource;
	}

	/**
	 * @param resource the resource to set
	 */
	public void setResource(ServiceReturn resource) {
		this.resource = resource;
	}

}
