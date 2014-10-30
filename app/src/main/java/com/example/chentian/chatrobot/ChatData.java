package com.example.chentian.chatrobot;

/**
 * @author chentian
 */
public class ChatData {

  private String text;

  private boolean isSent;

  public ChatData(String text, boolean isSent) {
    this.text = text;
    this.isSent = isSent;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public boolean isSent() {
    return isSent;
  }

  public void setSent(boolean isSent) {
    this.isSent = isSent;
  }
}
