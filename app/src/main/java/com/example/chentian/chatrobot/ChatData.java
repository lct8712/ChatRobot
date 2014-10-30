package com.example.chentian.chatrobot;

/**
 * @author chentian
 */
public class ChatData {

  private String text;

  private boolean isClient;

  public ChatData(String text, boolean isClient) {
    this.text = text;
    this.isClient = isClient;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public boolean isClient() {
    return isClient;
  }

  public void setClient(boolean isSent) {
    this.isClient = isSent;
  }
}
