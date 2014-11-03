package com.example.chentian.chatrobot.tuling;

import java.util.List;

/**
 * Model of tuling api response
 * <a href="http://www.tuling123.com/openapi/cloud/access_api.jsp#responseParam">Tuling api response</a>
 *
 * @author chentian
 */
public class TulingApiResult {

  public class TypeCode {

    public static final int FAILED = -1;

    public static final int SERVER_ERROR_BEGIN = 40000;

    public static final int SERVER_ERROR_END = 41000;

    public static final int TEXT = 100000;

    public static final int LINK = 200000;

    public static final int NOVEL = 301000;

    public static final int NEWS = 302000;

    public static final int APP = 304000;

    public static final int TRAIN = 305000;

    public static final int FLIGHT = 306000;

    public static final int GROUPON = 307000;

    public static final int DISCOUNT = 308000;

    public static final int HOTEL = 309000;

    public static final int LOTTERY = 310000;

    public static final int PRICE = 311000;

    public static final int RESTAURANT = 312000;

  }

  public class ResultExtra {

    private String name;

    private String author;

    private String detailurl;

    private String icon;

    private String article;

    private String source;

    private String count;

    private String trainnum;

    private String start;

    private String terminal;

    private String starttime;

    private String endtime;

    private String flight;

    private String route;

    private String state;

    private String info;

    private String price;

    private String satisfaction;

    private String number;

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getAuthor() {
      return author;
    }

    public void setAuthor(String author) {
      this.author = author;
    }

    public String getDetailurl() {
      return detailurl;
    }

    public void setDetailurl(String detailurl) {
      this.detailurl = detailurl;
    }

    public String getIcon() {
      return icon;
    }

    public void setIcon(String icon) {
      this.icon = icon;
    }

    public String getArticle() {
      return article;
    }

    public void setArticle(String article) {
      this.article = article;
    }

    public String getSource() {
      return source;
    }

    public void setSource(String source) {
      this.source = source;
    }

    public String getCount() {
      return count;
    }

    public void setCount(String count) {
      this.count = count;
    }

    public String getTrainnum() {
      return trainnum;
    }

    public void setTrainnum(String trainnum) {
      this.trainnum = trainnum;
    }

    public String getStart() {
      return start;
    }

    public void setStart(String start) {
      this.start = start;
    }

    public String getTerminal() {
      return terminal;
    }

    public void setTerminal(String terminal) {
      this.terminal = terminal;
    }

    public String getStarttime() {
      return starttime;
    }

    public void setStarttime(String starttime) {
      this.starttime = starttime;
    }

    public String getEndtime() {
      return endtime;
    }

    public void setEndtime(String endtime) {
      this.endtime = endtime;
    }

    public String getFlight() {
      return flight;
    }

    public void setFlight(String flight) {
      this.flight = flight;
    }

    public String getRoute() {
      return route;
    }

    public void setRoute(String route) {
      this.route = route;
    }

    public String getState() {
      return state;
    }

    public void setState(String state) {
      this.state = state;
    }

    public String getInfo() {
      return info;
    }

    public void setInfo(String info) {
      this.info = info;
    }

    public String getPrice() {
      return price;
    }

    public void setPrice(String price) {
      this.price = price;
    }

    public String getSatisfaction() {
      return satisfaction;
    }

    public void setSatisfaction(String satisfaction) {
      this.satisfaction = satisfaction;
    }

    public String getNumber() {
      return number;
    }

    public void setNumber(String number) {
      this.number = number;
    }
  }

  private int code;

  private String text;

  private String url;

  private List<ResultExtra> list;

  public TulingApiResult(int code) {
    this.code = code;
  }

  public boolean isSuccess() {
    return code >= TypeCode.TEXT;
  }

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

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public List<ResultExtra> getList() {
    return list;
  }

  public void setList(List<ResultExtra> list) {
    this.list = list;
  }

  @Override
  public String toString() {
    return "code: " + code + ", " + "text: " + text;
  }
}
