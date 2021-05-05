package com.example.demo.exception;


import static com.example.demo.exception.ErrorCode.INTERNAL_SERVER_ERROR;

/**
 * NPI General Exception.
 *
 * @author Sudipto Sarkar (sudipto.sarkar@levadata.com)
 */
public class DemoServiceException extends BaseException {

  public DemoServiceException() {
    super(INTERNAL_SERVER_ERROR, "NPI General Error");
  }

  public DemoServiceException(String message) {
    super(INTERNAL_SERVER_ERROR, message);
  }

  public DemoServiceException(String message, Exception e) {
    super(INTERNAL_SERVER_ERROR, message, e);
  }
}
