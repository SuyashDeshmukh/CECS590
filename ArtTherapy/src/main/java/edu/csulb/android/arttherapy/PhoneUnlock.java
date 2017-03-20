package edu.csulb.android.arttherapy;

import android.app.KeyguardManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;

public class PhoneUnlock extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        KeyguardManager myKeyManager = (KeyguardManager)context.getSystemService(Context.KEYGUARD_SERVICE);

        if( !myKeyManager.inKeyguardRestrictedInputMode()) {
            notifyUser(context);

        }
    }

    private void notifyUser(Context context) {
        NotificationCompat.Builder mBuilder =
                (NotificationCompat.Builder) new NotificationCompat.Builder(context)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Art Therapy")
                        .setContentText("Lets Draw!");
// Creates an explicit intent for an Activity in your app
        Intent resultIntent = new Intent(context, DrawActivity.class);

// The stack builder object will contain an artificial back stack for the
// started Activity.
// This ensures that navigating backward from the Activity leads out of
// your application to the Home screen.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
// Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(DrawActivity.class);
// Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
// mId allows you to update the notification later on.
        mNotificationManager.notify(0, mBuilder.build());


    }
}
