package com.hcmus.api.ResponseData;

import com.hcmus.api.Dto.DetailChapter;

public class ResponseDetailChapter extends Response {
    private DetailChapter data;
    public ResponseDetailChapter()
    {
    }
    public ResponseDetailChapter(String status, DetailChapter data)
    {
    	super(status);
    	this.data = data;
    }
	public DetailChapter getData() {
		return data;
	}
	public void setData(DetailChapter data) {
		this.data = data;
	}
}
