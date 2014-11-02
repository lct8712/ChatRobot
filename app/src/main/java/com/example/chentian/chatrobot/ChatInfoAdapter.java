package com.example.chentian.chatrobot;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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
    setChatTimeText(chatData);
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

    LinearLayout itemWrapper = (LinearLayout) convertView.findViewById(R.id.itemWrapper);
    TextView textView = (TextView) convertView.findViewById(R.id.txtContent);
    ImageView avatarClient = (ImageView) convertView.findViewById(R.id.imageClientAvatar);
    ImageView avatarService = (ImageView) convertView.findViewById(R.id.imageServiceAvatar);

    ChatData chatData = dataList.get(position);
    textView.setText(chatData.getContent());
    if (chatData.isClient()) {
      textView.setBackgroundResource(R.drawable.pop_right);
      textView.setGravity(Gravity.END | Gravity.CENTER_VERTICAL);
      avatarClient.setVisibility(View.VISIBLE);
      avatarService.setVisibility(View.GONE);
      itemWrapper.setGravity(Gravity.END);
    } else {
      textView.setBackgroundResource(R.drawable.pop_left);
      textView.setGravity(Gravity.START | Gravity.CENTER_VERTICAL);
      avatarClient.setVisibility(View.GONE);
      avatarService.setVisibility(View.VISIBLE);
      itemWrapper.setGravity(Gravity.START);
    }

    TextView timeTextView = (TextView) convertView.findViewById(R.id.txtTime);
    if (!TextUtils.isEmpty(chatData.getTimeText())) {
      timeTextView.setText(chatData.getTimeText());
      timeTextView.setVisibility(View.VISIBLE);
    } else {
      timeTextView.setVisibility(View.GONE);
    }

    return convertView;
  }

  /**
   * Set time info of each chat item
   * Only displayed when time interval of current and latest time is bigger then 3 minutes
   */
  private void setChatTimeText(ChatData chatData) {
    if (dataList.isEmpty()) {
      chatData.setTimeText(Util.formatDateTime(chatData.getCalendar()));
      return;
    }

    Calendar latestTime = dataList.get(dataList.size() - 1).getCalendar();
    latestTime.add(Calendar.MINUTE, CHAT_TIME_TEXT_THRESHOLD_MINUTE);
    if (chatData.getCalendar().after(latestTime)) {
      chatData.setTimeText(Util.formatDateTime(chatData.getCalendar()));
    }
  }
}
