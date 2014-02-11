package edu.psu.iam.cpr.rest.returns;

import javax.xml.bind.annotation.XmlRootElement;

import edu.psu.iam.cpr.core.api.returns.NamesServiceReturn;

@XmlRootElement
public class GetNameReturn {
	
	/** Contains the meta information */
	private ResourceMetadata resourceMetadata;
	
	/** Contains the response meta information */
	private ResponseMeta responseMetadata;
	
	/** Contains the name service return. */
	private NamesServiceReturn resource;
	
	/** 
	 * Constructor 
	 */
	public GetNameReturn() {
		super();
	}
	
	/**
	 * Constructor
	 * @param resourceMetadata contains the meta information.
	 * @param responseMetadata contains the response meta information.
	 * @param resource contains the name service return.
	 */
	public GetNameReturn(ResourceMetadata resourceMetadata, ResponseMeta responseMetadata, NamesServiceReturn resource) {
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
	public NamesServiceReturn getResource() {
		return resource;
	}

	/**
	 * @param resource the resource to set
	 */
	public void setResource(NamesServiceReturn resource) {
		this.resource = resource;
	}

}
