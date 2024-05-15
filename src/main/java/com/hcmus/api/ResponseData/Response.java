package com.hcmus.api.ResponseData;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.hateoas.RepresentationModel;

public class Response extends RepresentationModel<Response>  {
   protected String status;
   
   public Response() {
	super();
	// TODO Auto-generated constructor stub
   }

	public Response(String status) {
		super();
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}  
}
