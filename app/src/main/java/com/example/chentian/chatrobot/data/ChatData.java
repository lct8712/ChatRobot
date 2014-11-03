package com.example.chentian.chatrobot.data;

import java.util.Calendar;

import com.example.chentian.chatrobot.tuling.TulingApiResult;

/**
 * Base model of a chat item, could be a server item or a client item
 *
 * @author chentian
 */
public abstract class ChatData {

  protected String content;

  private Calendar calendar;

  private String tipText;

  protected ChatData() {
    this.calendar = Calendar.getInstance();
  }

  public abstract boolean isClient();

  public abstract TulingApiResult getApiResult();

  public abstract CharSequence getFormattedContent();

  public boolean isSentFailed() {
    return !isClient() && !getApiResult().isSuccess();
  }
  public boolean isServerError() {
    return !isClient() &&
            (getApiResult().getCode() >= TulingApiResult.TypeCode.SERVER_ERROR_BEGIN) &&
            (getApiResult().getCode() <= TulingApiResult.TypeCode.SERVER_ERROR_END);
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
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
