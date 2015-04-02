package com.vngrs.android.pomodoro.wear;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationManagerCompat;

import com.vngrs.android.pomodoro.shared.BaseNotificationReceiver;
import com.vngrs.android.pomodoro.shared.NotificationBuilder;
import com.vngrs.android.pomodoro.shared.PomodoroMaster;
import com.vngrs.android.pomodoro.shared.model.ActivityType;

import timber.log.Timber;

public class PomodoroNotificationReceiver extends BaseNotificationReceiver {

    public PomodoroNotificationReceiver() {

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        App.get(context).component().inject(this);
        super.onReceive(context, intent);
    }

    public void updateNotification(Context context, PomodoroMaster pomodoroMaster) {

        if (pomodoroMaster.getActivityType() != ActivityType.NONE) {
            NotificationBuilder builder = new NotificationBuilder(context, pomodoroMaster);
            Notification notification = builder.buildNotificationWear();
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
            notificationManager.notify(NOTIFICATION_ID, notification);
        } else {
            Timber.d("ignore notify for activityType " + ActivityType.NONE);
        }
    }
}
