package edu.psu.iam.cpr.rest.returns;

import javax.xml.bind.annotation.XmlRootElement;

import edu.psu.iam.cpr.core.api.returns.PersonServiceReturn;

@XmlRootElement
public class UpdatePersonReturn {
	
	/** Contains the meta information */
	private ResourceMetadata resourceMetadata;
	
	/** Contains the response meta information */
	private ResponseMeta responseMetadata;
	
	/** Contains the person service return. */
	private PersonServiceReturn resource;

	/**
	 * Constructor.
	 */
	public UpdatePersonReturn() {
		super();
	}

	/**
	 * @param resourceMetadata contains the meta information.
	 * @param responseMetadata contains the response meta information.
	 * @param resource contains the person service return.
	 */
	public UpdatePersonReturn(ResourceMetadata resourceMetadata,
			ResponseMeta responseMetadata, PersonServiceReturn resource) {
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
	public PersonServiceReturn getResource() {
		return resource;
	}

	/**
	 * @param resource the resource to set
	 */
	public void setResource(PersonServiceReturn resource) {
		this.resource = resource;
	}


}
