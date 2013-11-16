package berker.ege.yemek;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class alarmReceiver extends BroadcastReceiver {

	
	@Override
	public void onReceive(Context context, Intent intent) {
		Intent servis = new Intent(context, bService.class);
		context.startService(servis);
	}
}
