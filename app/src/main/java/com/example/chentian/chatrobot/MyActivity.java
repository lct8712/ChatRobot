package com.example.chentian.chatrobot;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.chentian.chatrobot.tuling.TulingApiResult;
import com.example.chentian.chatrobot.tuling.TulingClient;


public class MyActivity extends Activity implements TulingClient.Listener {

  private ChatInfoAdapter chatInfoAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_my);

    ListView listViewChat = (ListView) findViewById(R.id.listChatContent);
    chatInfoAdapter = new ChatInfoAdapter(this);
    listViewChat.setAdapter(chatInfoAdapter);
  }

  public void onBtnSendClick(View view) {
    TextView txtSendContent = (TextView) findViewById(R.id.txtInput);
    String content = String.valueOf(txtSendContent.getText());
    if (TextUtils.isEmpty(content)) {
      return;
    }

    chatInfoAdapter.add(new ChatData(content, true));
    txtSendContent.setText("");

    TulingClient client = new TulingClient(this);
    client.execute(content);
  }

  @Override
  public void onDataFetched(TulingApiResult result) {
    chatInfoAdapter.add(new ChatData(result.getText(), false));
  }
}
