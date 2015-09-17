package csci598.eventreceiver;

import android.os.Bundle;
import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.view.Menu;
import android.view.MotionEvent;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {

    	NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    	
        switch (e.getAction()) {
            case MotionEvent.ACTION_MOVE:            	
            	NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
            		    								.setSmallIcon(R.drawable.ic_launcher)
            		    								.setContentTitle("CSCI 598")
            		    								.setContentText("Event received!")
            		    								.setAutoCancel(true);
            	
            	notificationManager.cancelAll();
            	notificationManager.notify(1, mBuilder.build());
            	
            	TextView textViewToChange = (TextView) findViewById(R.id.text1);
            	textViewToChange.setText(R.string.event);
            	
        }

        return true;
    }
    
}
