package com.example.chentian.chatrobot;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.content.Context;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import com.example.chentian.chatrobot.data.ChatData;
import com.example.chentian.chatrobot.tuling.TulingApiResult;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Adapter of chat list
 *
 * @author chentian
 */
public class ChatInfoAdapter extends BaseAdapter {

  private static final int CHAT_TIME_TEXT_THRESHOLD_MINUTE = 3;

  private List<ChatData> dataList;

  private Context context;

  public ChatInfoAdapter(Context context) {
    this.dataList = new ArrayList<ChatData>();
    this.context = context;
  }

  public void add(ChatData chatData) {
    dataList.add(chatData);
    notifyDataSetChanged();
  }

  @Override
  public int getCount() {
    return dataList.size();
  }

  @Override
  public Object getItem(int position) {
    return dataList.get(position);
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    if (convertView == null) {
      convertView = LayoutInflater.from(context).inflate(R.layout.chat_item, null);
    }

    ChatData chatData = dataList.get(position);

    setTipView(chatData, convertView);
    setChatItemView(chatData, convertView);
    setExtraContentView(chatData, convertView);

    return convertView;
  }

  private void setTipView(ChatData chatData, View convertView) {
    String tip;
    if (chatData.isSentFailed()) {
      tip = this.context.getResources().getString(R.string.send_failed);
    } else if (chatData.isServerError()) {
      tip = this.context.getResources().getString(R.string.server_error);
    } else {
      tip = getTimeTip(chatData);
    }

    TextView tipTextView = (TextView) convertView.findViewById(R.id.txtTip);
    if (!TextUtils.isEmpty(tip)) {
      tipTextView.setText(tip);
      tipTextView.setVisibility(View.VISIBLE);
    } else {
      tipTextView.setVisibility(View.GONE);
    }
  }

  /**
   * Get time info tip of a chat item
   * Only displayed when time interval of current and latest time is bigger then 3 minutes
   */
  private String getTimeTip(ChatData chatData) {
    if (dataList.isEmpty()) {
      return Util.formatDateTime(chatData.getCalendar());
    }

    Calendar latestTime = dataList.get(dataList.size() - 1).getCalendar();
    latestTime.add(Calendar.MINUTE, CHAT_TIME_TEXT_THRESHOLD_MINUTE);
    if (chatData.getCalendar().after(latestTime)) {
      return Util.formatDateTime(chatData.getCalendar());
    }
    return null;
  }

  private void setChatItemView(ChatData chatData, View convertView) {
    LinearLayout itemWrapper = (LinearLayout) convertView.findViewById(R.id.chatTextLayout);
    itemWrapper.setVisibility(View.GONE);
    if (chatData.isSentFailed() || chatData.isSentFailed()) {
      return;
    }

    itemWrapper.setVisibility(View.VISIBLE);
    TextView textViewContent = (TextView) convertView.findViewById(R.id.txtContent);
    ImageView avatarClient = (ImageView) convertView.findViewById(R.id.imageClientAvatar);
    ImageView avatarService = (ImageView) convertView.findViewById(R.id.imageServiceAvatar);

    textViewContent.setText(chatData.getFormattedContent());
    textViewContent.setMovementMethod(LinkMovementMethod.getInstance());
    if (chatData.isClient()) {
      textViewContent.setBackgroundResource(R.drawable.pop_right);
      textViewContent.setGravity(Gravity.END | Gravity.CENTER_VERTICAL);
      avatarClient.setVisibility(View.VISIBLE);
      avatarService.setVisibility(View.GONE);
      itemWrapper.setGravity(Gravity.END);
    } else {
      textViewContent.setBackgroundResource(R.drawable.pop_left);
      textViewContent.setGravity(Gravity.START | Gravity.CENTER_VERTICAL);
      avatarClient.setVisibility(View.GONE);
      avatarService.setVisibility(View.VISIBLE);
      itemWrapper.setGravity(Gravity.START);
    }
  }

  private void setExtraContentView(ChatData chatData, View convertView) {
    RelativeLayout extraInfoLayout = (RelativeLayout) convertView.findViewById(R.id.extraInfoLayout);
    extraInfoLayout.setVisibility(View.GONE);
    if (chatData.isClient()) {
      return;
    }

    TulingApiResult apiResult = chatData.getApiResult();
    if (apiResult == null ||
            apiResult.getCode() == TulingApiResult.TypeCode.TEXT ||
            apiResult.getCode() == TulingApiResult.TypeCode.LINK ||
            apiResult.getList() == null || apiResult.getList().isEmpty()) {
      return;
    }

    extraInfoLayout.setVisibility(View.VISIBLE);

    TulingApiResult.ResultExtra extra = apiResult.getList().get(0);
    ImageView imageViewIcon = (ImageView) convertView.findViewById(R.id.extraIcon);
    imageViewIcon.setVisibility(TextUtils.isEmpty(extra.getIcon()) ? View.GONE : View.VISIBLE);
    ImageLoader.getInstance().displayImage(extra.getIcon(), imageViewIcon);
    TextView txtExtraType = (TextView) convertView.findViewById(R.id.txtExtraType);
    txtExtraType.setText(getExtraType(apiResult.getCode()));
    TextView txtExtraTitle = (TextView) convertView.findViewById(R.id.txtExtraTitle);
    txtExtraTitle.setText(getExtraTitle(apiResult.getCode(), extra));
    TextView txtExtraContent = (TextView) convertView.findViewById(R.id.txtExtraContent);
    String extraContent = getExtraContent(apiResult.getCode(), extra);
    txtExtraContent.setText(Html.fromHtml(extraContent));
    txtExtraContent.setMovementMethod(LinkMovementMethod.getInstance());
  }

  private String getExtraType(int typeCode) {
    switch (typeCode) {
      case TulingApiResult.TypeCode.NOVEL:
        return context.getResources().getString(R.string.extra_type_novel);
      case TulingApiResult.TypeCode.NEWS:
        return context.getResources().getString(R.string.extra_type_news);
      case TulingApiResult.TypeCode.APP:
        return context.getResources().getString(R.string.extra_type_app);
      case TulingApiResult.TypeCode.TRAIN:
        return context.getResources().getString(R.string.extra_type_train);
      case TulingApiResult.TypeCode.FLIGHT:
        return context.getResources().getString(R.string.extra_type_flight);
      case TulingApiResult.TypeCode.GROUPON:
        return context.getResources().getString(R.string.extra_type_groupon);
      case TulingApiResult.TypeCode.DISCOUNT:
        return context.getResources().getString(R.string.extra_type_discount);
      case TulingApiResult.TypeCode.HOTEL:
        return context.getResources().getString(R.string.extra_type_hotel);
      case TulingApiResult.TypeCode.LOTTERY:
        return context.getResources().getString(R.string.extra_type_lottery);
      case TulingApiResult.TypeCode.PRICE:
        return context.getResources().getString(R.string.extra_type_price);
      case TulingApiResult.TypeCode.RESTAURANT:
        return context.getResources().getString(R.string.extra_type_restaurant);
      default:
        return "";
    }
  }

  private String getExtraTitle(int typeCode, TulingApiResult.ResultExtra extra) {
    switch (typeCode) {
      case TulingApiResult.TypeCode.NOVEL:
        return extra.getName();
      case TulingApiResult.TypeCode.NEWS:
        return extra.getArticle();
      case TulingApiResult.TypeCode.APP:
        return extra.getName();
      case TulingApiResult.TypeCode.TRAIN:
        return extra.getTrainnum();
      case TulingApiResult.TypeCode.FLIGHT:
        return extra.getFlight();
      case TulingApiResult.TypeCode.GROUPON:
        return extra.getName();
      case TulingApiResult.TypeCode.DISCOUNT:
        return extra.getName();
      case TulingApiResult.TypeCode.HOTEL:
        return extra.getName();
      case TulingApiResult.TypeCode.LOTTERY:
        return extra.getNumber();
      case TulingApiResult.TypeCode.PRICE:
        return extra.getName();
      case TulingApiResult.TypeCode.RESTAURANT:
        return extra.getName();
      default:
        return "";
    }
  }

  private String getExtraContent(int typeCode, TulingApiResult.ResultExtra extra) {
    String result = "";
    switch (typeCode) {
      case TulingApiResult.TypeCode.NOVEL: {
        String pattern = context.getResources().getString(R.string.extra_content_pattern_novel);
        result = String.format(pattern, extra.getAuthor());
        break;
      }
      case TulingApiResult.TypeCode.NEWS: {
        String pattern = context.getResources().getString(R.string.extra_content_pattern_news);
        result = String.format(pattern, extra.getSource());
        break;
      }
      case TulingApiResult.TypeCode.APP: {
        String pattern = context.getResources().getString(R.string.extra_content_pattern_app);
        result = String.format(pattern, extra.getCount());
        break;
      }
      case TulingApiResult.TypeCode.TRAIN: {
        String pattern = context.getResources().getString(R.string.extra_content_pattern_train);
        result = String.format(pattern, extra.getStart(), extra.getTerminal(),
                extra.getStarttime(), extra.getEndtime());
        break;
      }
      case TulingApiResult.TypeCode.FLIGHT: {
        String pattern = context.getResources().getString(R.string.extra_content_pattern_flight);
        result = String.format(pattern, extra.getRoute(),
                extra.getStarttime(), extra.getEndtime(), extra.getState());
        break;
      }
      case TulingApiResult.TypeCode.GROUPON: {
        String pattern = context.getResources().getString(R.string.extra_content_pattern_groupon);
        result = String.format(pattern, extra.getIcon(), extra.getPrice(), extra.getCount());
        break;
      }
      case TulingApiResult.TypeCode.DISCOUNT: {
        String pattern = context.getResources().getString(R.string.extra_content_pattern_discount);
        result = String.format(pattern, extra.getInfo());
        break;
      }
      case TulingApiResult.TypeCode.HOTEL: {
        String pattern = context.getResources().getString(R.string.extra_content_pattern_hotel);
        result = String.format(pattern, extra.getPrice(),
                extra.getSatisfaction(), extra.getCount());
        break;
      }
      case TulingApiResult.TypeCode.LOTTERY: {
        String pattern = context.getResources().getString(R.string.extra_content_pattern_lottery);
        result = String.format(pattern, extra.getInfo());
        break;
      }
      case TulingApiResult.TypeCode.PRICE: {
        String pattern = context.getResources().getString(R.string.extra_content_pattern_price);
        result = String.format(pattern, extra.getPrice(), extra.getInfo());
        break;
      }
      case TulingApiResult.TypeCode.RESTAURANT: {
        String pattern = context.getResources().getString(R.string.extra_content_pattern_restaurant);
        result = String.format(pattern, extra.getPrice(), extra.getInfo());
      }
    }

    if (!TextUtils.isEmpty(extra.getDetailurl())) {
      String pattern = context.getResources().getString(R.string.extra_content_pattern_more);
      result += String.format(pattern, extra.getDetailurl());
    }
    return result;
  }
}
