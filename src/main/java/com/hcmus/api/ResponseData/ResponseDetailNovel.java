package com.hcmus.api.ResponseData;

import com.hcmus.api.Dto.Novel;

public class ResponseDetailNovel extends Response{
    private Novel data;

    
    
	public ResponseDetailNovel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ResponseDetailNovel(String status, Novel data) {
		super(status);
		this.data = data;
	}

	public Novel getData() {
		return data;
	}

	public void setData(Novel data) {
		this.data = data;
	}
}
