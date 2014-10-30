package com.example.chentian.chatrobot;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * @author chentian
 */
public class ChatInfoAdapter extends BaseAdapter {

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

    LinearLayout itemWrapper = (LinearLayout) convertView.findViewById(R.id.itemWrapper);
    TextView textView = (TextView) convertView.findViewById(R.id.txtContent);
    ImageView avatarClient = (ImageView) convertView.findViewById(R.id.imageClientAvatar);
    ImageView avatarService = (ImageView) convertView.findViewById(R.id.imageServiceAvatar);

    ChatData chatData = dataList.get(position);
    textView.setText(chatData.getText());
    if (chatData.isClient()) {
      textView.setBackgroundResource(R.drawable.pop_right);
      avatarClient.setVisibility(View.VISIBLE);
      avatarService.setVisibility(View.GONE);
      itemWrapper.setGravity(Gravity.END);
    } else {
      textView.setBackgroundResource(R.drawable.pop_left);
      avatarClient.setVisibility(View.GONE);
      avatarService.setVisibility(View.VISIBLE);
      itemWrapper.setGravity(Gravity.START);
    }

    return convertView;
  }
}
