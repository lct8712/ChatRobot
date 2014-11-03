package com.example.chentian.chatrobot.data;

import android.text.Html;

import com.example.chentian.chatrobot.tuling.TulingApiResult;

/**
 * Model of a chat item
 *
 * @author chentian
 */
public class ChatDataServer extends ChatData {

  private TulingApiResult result;

  public ChatDataServer(TulingApiResult result) {
    super();

    this.result = result;
    this.content = result.getText();
  }

  @Override
  public CharSequence getFormattedContent() {
    if (result.getCode() == TulingApiResult.TypeCode.LINK) {
      String html = String.format("<html>%s<br/><a href=%s>%s</a></html>", content, result.getUrl(), result.getUrl());
      return Html.fromHtml(html);
    }
    return content;
  }

  @Override
  public boolean isClient() {
    return false;
  }

  @Override
  public TulingApiResult getApiResult() {
    return result;
  }
}
