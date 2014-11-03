package com.example.chentian.chatrobot.data;

import com.example.chentian.chatrobot.tuling.TulingApiResult;

/**
 * Model of a client chat item
 *
 * @author chentian
 */
public class ChatDataClient extends ChatData {

  public ChatDataClient(String content) {
    super();

    this.content = content;
  }

  @Override
  public boolean isClient() {
    return true;
  }

  @Override
  public TulingApiResult getApiResult() {
    return null;
  }

  @Override
  public CharSequence getFormattedContent() {
    return content;
  }
}
