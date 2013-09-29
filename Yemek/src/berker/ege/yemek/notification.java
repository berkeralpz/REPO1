package berker.ege.yemek;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;

public class notification extends Activity {
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		NotificationManager notman;
		notman=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
	Notification bildirim=new Notification(R.drawable.logo,"Bildirgeç!",System.currentTimeMillis());
	Intent intent=new Intent(this,MainActivity.class);
	intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	PendingIntent contentIntent=PendingIntent.getActivity(this, 0, intent, 0);
	bildirim.setLatestEventInfo(this, "Yemek Güzel", "Bu gün yemek hoþuna gidebilir!", contentIntent);
	notman.notify(0,bildirim);
}
	}
