package com.example.chentian.chatrobot;

import java.util.Calendar;

import android.app.Application;
import android.test.ApplicationTestCase;
import junit.framework.Assert;

public class UtilTest extends ApplicationTestCase<Application> {

  public UtilTest() {
    super(Application.class);
  }

  public void testFormatDateTimeToday() throws Exception {
    Calendar now = Calendar.getInstance();
    String expected = String.format("%02d:%02d",
            now.get(Calendar.HOUR_OF_DAY), now.get(Calendar.MINUTE));
    String actual = Util.formatDateTime(now);
    Assert.assertEquals(expected, actual);
  }

  public void testFormatDateTimeOther() throws Exception {
    Calendar calendar = Calendar.getInstance();
    calendar.set(2014, Calendar.FEBRUARY, 14, 15, 30);
    String expected = "2014-02-14 15:30";
    String actual = Util.formatDateTime(calendar);
    Assert.assertEquals(expected, actual);
  }
}
