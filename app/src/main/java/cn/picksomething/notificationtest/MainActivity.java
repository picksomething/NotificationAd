package cn.picksomething.notificationtest;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;


public class MainActivity extends Activity implements View.OnClickListener {

    public NotificationManager notificationManager;
    public Notification notification;

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
    }

    /**
     * initDates use for init some necessary things
     */
    private void initDatas() {
        notificationManager = (NotificationManager) getSystemService(this.getApplicationContext().NOTIFICATION_SERVICE);
    }

    /**
     * findViews by ids
     */
    private void findViews() {
        mShowNormalNotification = (Button) findViewById(R.id.addNormalNotify);
        mShowMultiIconNotification = (Button) findViewById(R.id.addMultiIconNotify);
        mShowBannerNotification = (Button) findViewById(R.id.addBannerNotify);
        mCancelAllNotification = (Button) findViewById(R.id.cancelAllNotify);
    }

    /**
     * set listeners for some elements
     */
    private void setListeners() {
        mShowNormalNotification.setOnClickListener(this);
        mShowMultiIconNotification.setOnClickListener(this);
        mShowBannerNotification.setOnClickListener(this);
        mCancelAllNotification.setOnClickListener(this);
    }

    private void showBannerNotification() {
        RemoteViews bannerViews = new RemoteViews(getPackageName(),
                R.layout.banner_view);
        bannerViews.setImageViewResource(R.id.banner, R.drawable.baidu);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://huhulab.com/"));
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
                this).setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle("Custom Notification")
                .setTicker("new message");
        //mBuilder.setAutoCancel(true);
        mBuilder.setOngoing(true);
        mBuilder.setContentIntent(pendingIntent);
        mBuilder.setContent(bannerViews);
        notificationManager.notify(100, mBuilder.build());
    }

    private void showMultiIconNotificaiton() {
        RemoteViews multiIconView = new RemoteViews(getPackageName(),
                R.layout.multi_icon_view);
        multiIconView.setImageViewResource(R.id.icon1, R.drawable.ic_launcher);
        multiIconView.setImageViewResource(R.id.icon2, R.drawable.ic_launcher);
        multiIconView.setImageViewResource(R.id.icon3, R.drawable.ic_launcher);
        multiIconView.setImageViewResource(R.id.icon4, R.drawable.ic_launcher);
        multiIconView.setImageViewResource(R.id.icon5, R.drawable.ic_launcher);
        multiIconView.setImageViewResource(R.id.icon6, R.drawable.ic_launcher);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://huhulab.com/"));
        PendingIntent pendingIntent = PendingIntent.getActivity(
                MainActivity.this, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
                this).setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle("Custom Notification")
                .setTicker("new message");
        //mBuilder.setAutoCancel(true);
        mBuilder.setOngoing(true);
        mBuilder.setContentIntent(pendingIntent);
        mBuilder.setContent(multiIconView);
        notificationManager.notify(1000, mBuilder.build());
    }

    /**
     * create Notifications according param isResident
     */
    private void showNoramlNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.drawable.ic_launcher);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://huhulab.com/"));
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        builder.setContentIntent(pendingIntent);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher));
        builder.setContentTitle("This is ContentTitle");
        builder.setContentText("This is ContentText");
        builder.setSubText("This is subText");
        //builder.setAutoCancel(true);
        builder.setOngoing(true);
        notificationManager.notify(mNoPresidentNotificationNum, builder.build());

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
                showNoramlNotification();
                break;
            case R.id.addMultiIconNotify:
                showMultiIconNotificaiton();
                break;
            case R.id.addBannerNotify:
                showBannerNotification();
                break;
            case R.id.cancelAllNotify:
                cancelAllNotification();
                break;
            default:
                break;
        }
    }
}
