package com.hcmus.api.ResponseData;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class ResponseError extends Response{
  private Date timestamp;
  private String path;
  private String message;

  public ResponseError(Date timestamp, String path, String message) {
      super(StatusEnum.error.toString());
      this.timestamp = timestamp;
      this.path = path;
      this.message = message;
  }
}
