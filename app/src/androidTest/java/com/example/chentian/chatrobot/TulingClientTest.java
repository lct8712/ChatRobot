package com.example.chentian.chatrobot;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.text.TextUtils;
import junit.framework.Assert;

import com.example.chentian.chatrobot.tuling.TulingApiResult;
import com.example.chentian.chatrobot.tuling.TulingClient;

public class TulingClientTest extends ApplicationTestCase<Application> {

  public TulingClientTest() {
    super(Application.class);
  }

  public void testFetchResult() throws Exception {
    TulingClient client = new TulingClient(new TulingClient.Listener() {
      @Override
      public void onDataFetched(TulingApiResult result) {
        Assert.assertTrue(result.getCode() == TulingApiResult.TypeCode.TEXT);
        Assert.assertTrue(!TextUtils.isEmpty(result.getText()));
        System.out.println(result);
      }
    });
    client.execute("你好");
  }
}
