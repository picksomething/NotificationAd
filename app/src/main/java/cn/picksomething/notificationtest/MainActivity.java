package cn.picksomething.notificationtest;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;


public class MainActivity extends Activity implements View.OnClickListener, IBaseSettings {

    private static final int PRO_NOTIFICATION_ID = 1000;
    private static final int MULIT_ICON_NOTIFICATION_ID = 1001;
    private static final int BANNER_NOTIFICATION_ID = 1002;

    NotificationManager notificationManager;
    RemoteViews remoteViews;
    NotificationCompat.Builder builder;
    Intent intent;
    PendingIntent pendingIntent;
    Handler handler;

    private Button mShowNormalNotification;
    private Button mShowMultiIconNotification;
    private Button mShowBannerNotification;
    private Button mCancelAllNotification;

    private int mNoPresidentNotificationNum = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDatas();
        findViews();
        setListeners();
        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                showNoramlNotification(false);
            }
        },5000);
    }

    /**
     * Init some necessary things
     */
    @Override
    public void initDatas() {
        notificationManager = (NotificationManager) getSystemService(this.getApplicationContext().NOTIFICATION_SERVICE);
        intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://huhulab.com/"));
        pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        showNoramlNotification(true);
        showMultiIconNotificaiton(true);
        showBannerNotification(true);
    }

    /**
     * FindViews by ids
     */
    @Override
    public void findViews() {
        mShowNormalNotification = (Button) findViewById(R.id.addNormalNotify);
        mShowMultiIconNotification = (Button) findViewById(R.id.addMultiIconNotify);
        mShowBannerNotification = (Button) findViewById(R.id.addBannerNotify);
        mCancelAllNotification = (Button) findViewById(R.id.cancelAllNotify);
    }

    /**
     * Set listeners for some elements
     */
    @Override
    public void setListeners() {
        mShowNormalNotification.setOnClickListener(this);
        mShowMultiIconNotification.setOnClickListener(this);
        mShowBannerNotification.setOnClickListener(this);
        mCancelAllNotification.setOnClickListener(this);
    }

    private void showBannerNotification(boolean isResident) {
        remoteViews = new RemoteViews(getPackageName(), R.layout.banner_view);
        remoteViews.setImageViewResource(R.id.banner, R.drawable.baidu);
        builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.drawable.ic_launcher);
        builder.setContentTitle("Custom Notification");
        builder.setTicker("new message");
        builder.setContentIntent(pendingIntent);
        builder.setContent(remoteViews);
        if(isResident){
            builder.setOngoing(true);
            notificationManager.notify(BANNER_NOTIFICATION_ID, builder.build());
        }else{
            builder.setAutoCancel(true);
            notificationManager.notify(mNoPresidentNotificationNum, builder.build());
        }

    }

    private void showMultiIconNotificaiton(boolean isResident) {
        remoteViews = new RemoteViews(getPackageName(), R.layout.multi_icon_view);
        remoteViews.setImageViewResource(R.id.icon1, R.drawable.ic_launcher);
        remoteViews.setImageViewResource(R.id.icon2, R.drawable.ic_launcher);
        remoteViews.setImageViewResource(R.id.icon3, R.drawable.ic_launcher);
        remoteViews.setImageViewResource(R.id.icon4, R.drawable.ic_launcher);
        remoteViews.setImageViewResource(R.id.icon5, R.drawable.ic_launcher);
        remoteViews.setImageViewResource(R.id.icon6, R.drawable.ic_launcher);
        builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.drawable.ic_launcher);
        builder.setContentTitle("Custom Notification");
        builder.setTicker("new message");
        builder.setContentIntent(pendingIntent);
        builder.setContent(remoteViews);
        if(isResident){
            builder.setOngoing(true);
            notificationManager.notify(MULIT_ICON_NOTIFICATION_ID, builder.build());
        }else{
            builder.setAutoCancel(true);
            notificationManager.notify(mNoPresidentNotificationNum, builder.build());
        }

    }

    /**
     * create Notifications according param isResident
     */
    private void showNoramlNotification(boolean isResident) {
        builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.drawable.ic_launcher);
        builder.setContentIntent(pendingIntent);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher));
        builder.setTicker("new message");
        builder.setContentText("This is ContentText");
        builder.setSubText("This is subText");
        if(isResident){
            builder.setOngoing(true);
            notificationManager.notify(PRO_NOTIFICATION_ID, builder.build());
        }else{
            builder.setAutoCancel(true);
            notificationManager.notify(mNoPresidentNotificationNum++, builder.build());
        }

    }

    /**
     * cancel all Notification
     */
    private void cancelAllNotification() {
        notificationManager.cancelAll();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addNormalNotify:
                showNoramlNotification(false);
                break;
            case R.id.addMultiIconNotify:
                showMultiIconNotificaiton(false);
                break;
            case R.id.addBannerNotify:
                showBannerNotification(false);
                break;
            case R.id.cancelAllNotify:
                cancelAllNotification();
                break;
            default:
                break;
        }
    }
}
