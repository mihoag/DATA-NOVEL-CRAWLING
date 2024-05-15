package com.hcmus.api.ResponseData;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ResponseError extends Response{
  private Date timestamp;
  private String path;
  private String message;
  
  
  
  public ResponseError()
  {
  }
  
  public ResponseError(Date timestamp, String path, String message) {
	super(StatusEnum.error.toString());
	this.timestamp = timestamp;
	this.path = path;
	this.message = message;
  }
  public Date getTimestamp() {
	return timestamp;
  }
  
  public void setTimestamp(Date timestamp) {
	this.timestamp = timestamp;
  }
  
  public String getPath() {
	return path;
  }
  
  public void setPath(String path) {
	this.path = path;
  }

  public String getMessage() {
	return message;
  }

  public void setMessage(String message) {
	this.message = message;
  }
}
