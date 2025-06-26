package org.sopt.global.exception.custom;


import org.sopt.global.exception.ApiException;
import org.sopt.global.exception.ErrorCode;

public class LikeNullException extends ApiException {
  public LikeNullException() {
    super(ErrorCode.NULL_LIKE);
  }
}
