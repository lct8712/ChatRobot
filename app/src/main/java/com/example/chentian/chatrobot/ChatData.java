package com.example.chentian.chatrobot;

import java.util.Calendar;

/**
 * Model of a chat item
 *
 * @author chentian
 */
public class ChatData {

  private String content;

  private boolean isClient;

  private Calendar calendar;

  private String timeText;

  public ChatData(String content, boolean isClient) {
    this.content = content;
    this.isClient = isClient;
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

  public String getTimeText() {
    return timeText;
  }

  public void setTimeText(String timeText) {
    this.timeText = timeText;
  }
}
