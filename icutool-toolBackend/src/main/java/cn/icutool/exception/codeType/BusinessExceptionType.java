package cn.icutool.exception.codeType;

public enum BusinessExceptionType {

  DEFAULT_ERROR(-100,"业务异常"),
  USER_INPUT_ERROR(-101,"您输入的数据格式错误或您没有权限访问资源！"),
  LOCK_LIMIT(-102,"您输入的数据格式错误或您没有权限访问资源！"),
  SYSTEM_ERROR (-103,"系统出现异常，请您稍后再试或联系管理员！");

  //异常类型中文描述
  private final String message;

  private final int code;

  BusinessExceptionType(int code, String message) {
    this.code = code;
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public int getCode() {
    return code;
  }

}
