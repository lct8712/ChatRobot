package com.example.chentian.chatrobot;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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

    TextView textView = (TextView) convertView.findViewById(R.id.txtContent);
    textView.setText(dataList.get(position).getText());

    return convertView;
  }
}
