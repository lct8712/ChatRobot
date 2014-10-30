package com.example.chentian.chatrobot.tuling;

/**
 * @author chentian
 */
public class TulingApiResult {

  public class TypeCode {

    public static final int TEXT = 100000;

    public static final int LINK = 200000;

    public static final int NOVEL = 301000;

    public static final int NEWS = 302000;

    public static final int APP = 304000;

  }

  private int code;

  private String text;

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  @Override
  public String toString() {
    return "code: " + code + ", " + "text: " + text;
  }
}
