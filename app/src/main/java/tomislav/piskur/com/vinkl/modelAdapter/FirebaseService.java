package tomislav.piskur.com.vinkl.modelAdapter;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import tomislav.piskur.com.vinkl.MainActivity;
import tomislav.piskur.com.vinkl.R;

/**
 * Created by srs on 16.03.2018.
 */

public class FirebaseService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        showNotification(remoteMessage.getNotification().getBody());
    }

    public void showNotification(String message) {

        PendingIntent pi = PendingIntent.getActivity(this, 1, new Intent(this, MainActivity.class), 0);
        Notification notification = new NotificationCompat.Builder(this, "M_CH_ID")
                .setSmallIcon(R.drawable.ic_vinklstatusbaricon)
                .setContentTitle("Gostionica Vinkl")
                .setContentText(message)
                .setContentIntent(pi)
                .setAutoCancel(true)
                .build();


        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);

    }
}