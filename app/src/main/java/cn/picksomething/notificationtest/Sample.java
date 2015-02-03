package cn.picksomething.notificationtest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by caobin on 15/2/2.
 */
public class Sample extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView textView = new TextView(this);
        textView.setText("Hello Notification");
        setContentView(textView);
    }
}
