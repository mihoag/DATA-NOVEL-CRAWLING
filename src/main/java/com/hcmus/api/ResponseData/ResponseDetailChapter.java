package com.hcmus.api.ResponseData;

import com.hcmus.api.Dto.DetailChapter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDetailChapter extends Response {
    private DetailChapter data;
    public ResponseDetailChapter(String status, DetailChapter data)
    {
    	super(status);
    	this.data = data;
    }
}
