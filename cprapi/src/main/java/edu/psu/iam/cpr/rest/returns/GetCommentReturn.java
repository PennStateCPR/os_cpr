package edu.psu.iam.cpr.rest.returns;

import javax.xml.bind.annotation.XmlRootElement;

import edu.psu.iam.cpr.core.api.returns.UserCommentServiceReturn;

@XmlRootElement
public class GetCommentReturn {

	/** Contains the meta information */
	private ResourceMetadata resourceMetadata;
	
	/** Contains the response meta information */
	private ResponseMeta responseMetadata;
	
	/** Contains the address service return. */
	private UserCommentServiceReturn resource;

	/**
	 * Constructor.
	 * @param resourceMetadata contains the resource metadata.
	 * @param responseMetadata contains the response metadata.
	 * @param resource contains the resource.
	 */
	public GetCommentReturn(ResourceMetadata resourceMetadata,
			ResponseMeta responseMetadata, UserCommentServiceReturn resource) {
		super();
		this.resourceMetadata = resourceMetadata;
		this.responseMetadata = responseMetadata;
		this.resource = resource;
	}

	/**
	 * Constructor.
	 */
	public GetCommentReturn() {
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
	public UserCommentServiceReturn getResource() {
		return resource;
	}

	/**
	 * @param resource the resource to set
	 */
	public void setResource(UserCommentServiceReturn resource) {
		this.resource = resource;
	}

}
