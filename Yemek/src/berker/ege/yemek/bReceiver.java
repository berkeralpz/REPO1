package berker.ege.yemek;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class bReceiver extends BroadcastReceiver {

	private static final long INTERVAL = 60 * 1000 * 15;
	
	@Override
	public void onReceive(Context context, Intent intent) {
		AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		Intent serviceIntent = new Intent(context, bService.class);
		PendingIntent pendingIntent = PendingIntent.getService(context, 1, serviceIntent, 0);
		manager.setRepeating(AlarmManager.ELAPSED_REALTIME, INTERVAL, INTERVAL, pendingIntent);
		}
}
