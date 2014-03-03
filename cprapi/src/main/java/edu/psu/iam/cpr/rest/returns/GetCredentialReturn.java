package edu.psu.iam.cpr.rest.returns;

import javax.xml.bind.annotation.XmlRootElement;

import edu.psu.iam.cpr.core.api.returns.CredentialServiceReturn;

@XmlRootElement
public class GetCredentialReturn {
	
	/** Contains the meta information */
	private ResourceMetadata resourceMetadata;
	
	/** Contains the response meta information */
	private ResponseMeta responseMetadata;
	
	/** Contains the name service return. */
	private CredentialServiceReturn resource;

	/**
	 * Constructor
	 * @param resourceMetadata contains the meta information.
	 * @param responseMetadata contains the response meta information.
	 * @param resource contains the credential service return.
	 */
	public GetCredentialReturn(final ResourceMetadata resourceMetadata,
			final ResponseMeta responseMetadata, final CredentialServiceReturn resource) {
		super();
		this.resourceMetadata = resourceMetadata;
		this.responseMetadata = responseMetadata;
		this.resource = resource;
	}

	/**
	 * Constructor.
	 */
	public GetCredentialReturn() {
		super();
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
	public void setResourceMetadata(final ResourceMetadata resourceMetadata) {
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
	public void setResponseMetadata(final ResponseMeta responseMetadata) {
		this.responseMetadata = responseMetadata;
	}

	/**
	 * @return the resource
	 */
	public CredentialServiceReturn getResource() {
		return resource;
	}

	/**
	 * @param resource the resource to set
	 */
	public void setResource(final CredentialServiceReturn resource) {
		this.resource = resource;
	}
	

}
