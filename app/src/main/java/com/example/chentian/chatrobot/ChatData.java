package com.example.chentian.chatrobot;

import java.util.Calendar;

import com.example.chentian.chatrobot.tuling.TulingApiResult;

/**
 * Model of a chat item
 *
 * @author chentian
 */
public class ChatData {

  private String content;

  private boolean isClient;

  private Calendar calendar;

  private String tipText;

  /**
   * Client chat item
   * @param content Message user sent
   */
  public ChatData(String content) {
    this.content = content;
    this.isClient = true;
    this.calendar = Calendar.getInstance();
  }

  /**
   * Server chat item
   * @param result Response from server
   */
  public ChatData(TulingApiResult result) {
    this.content = result.getText();
    this.isClient = false;
    this.calendar = Calendar.getInstance();
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public boolean isClient() {
    return isClient;
  }

  public void setClient(boolean isSent) {
    this.isClient = isSent;
  }

  public Calendar getCalendar() {
    return calendar;
  }

  public void setCalendar(Calendar calendar) {
    this.calendar = calendar;
  }

  public String getTipText() {
    return tipText;
  }

  public void setTipText(String tipText) {
    this.tipText = tipText;
  }
}
