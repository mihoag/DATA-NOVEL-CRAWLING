package com.hcmus.api.ResponseData;

import com.hcmus.api.Dto.Novel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDetailNovel extends Response{
    private Novel data;
	public ResponseDetailNovel(String status, Novel data) {
		super(status);
		this.data = data;
	}
}
