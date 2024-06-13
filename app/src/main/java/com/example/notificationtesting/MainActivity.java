package com.example.notificationtesting;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    //episode 2
    //1. Notification channel
    //2. Notification Builder
    //3. Notification Manager

    private  static  final String ChannerID = "Anthony";
    private  static  final String ChannerName = "Brillante";
    private  static  final String ChannerDesc = "Notification";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(ChannerID, ChannerName, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(ChannerDesc);
            NotificationManager manager = getSystemService(NotificationManager.class);
            //This will create the notification channel
            manager.createNotificationChannel(channel);
        }

        //This button is use for trigger for display the notification
        findViewById(R.id.NotificationButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayNotification();
            }
        });

    }
    //this function will display the notification
    @SuppressLint("MissingPermission")
    public void displayNotification(){
        //This is the built in Notification in android studio
        //.setSmallIcon; this will use for displaying the icon or image on notification
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this, ChannerID)
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        //This will set the title of notification
                        .setContentTitle("Episode 2 Notification")
                        //This will use as the body of notification
                        .setContentText("Notification Working")
                        //This wil only use if the notification has priority
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                ;

        // This part is for notification manager
        NotificationManagerCompat mnotificationmgr = NotificationManagerCompat.from(this);
        // We can now use "notificationmanager" to display the notification
        //Notify is use for displaying the notification
        //1. This ID is use for Update or delete the notification
        //2. Is used for displaying the messages on notification
        mnotificationmgr.notify(1, mBuilder.build());
    }
}