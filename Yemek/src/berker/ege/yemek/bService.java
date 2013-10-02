package berker.ege.yemek;

import java.util.Calendar;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;

public class bService extends Service {
	private static final String PREF_TATLI="PREF_TATLI";
	private static final String PREF_YEMEK="PREF_YEMEK";
	private SharedPreferences preferences;
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
	    new Thread(new Runnable(){
	    	@Override
			public void run() {
				final Calendar ca= Calendar.getInstance();
				int saat=ca.get(Calendar.HOUR_OF_DAY);
				int gu=ca.get(Calendar.DAY_OF_MONTH);
				if(saat>17){
				int gun=gu+1;
				// TODO Auto-generated method stub
				preferences = getSharedPreferences("PREF_GENEL",
				        MODE_PRIVATE);
				boolean tatlisecilimi=preferences.getBoolean(PREF_YEMEK, false);
				if(tatlisecilimi==true){	
				if(gun==8||gun==10||gun==23||gun==11||gun==30){
			    NotificationManager notman;
			    notman=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
				Notification bildirim=new Notification(R.drawable.yemek,"Yemekte tatlý var!",System.currentTimeMillis());
				Intent intent1=new Intent(Intent.ACTION_MAIN);
				intent1.addCategory(Intent.CATEGORY_HOME);
				PendingIntent contentIntent=PendingIntent.getActivity(bService.this, 0, intent1, 0);
				bildirim.setLatestEventInfo(bService.this, "Afiyet Olsun!", "Yarýn yemekte tatlý var!", contentIntent);
				notman.notify(0,bildirim);
				}
				}
				boolean yemeksecilimi=preferences.getBoolean(PREF_TATLI, false);	 
				if(yemeksecilimi==true){
					if(gun==3||gun==4||gun==8||gun==11||gun==22||gun==25||gun==28||gun==31){
				    NotificationManager notman;
				    notman=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
					Notification bildirim=new Notification(R.drawable.yemek,"Yemek güzel!",System.currentTimeMillis());
					Intent intent1=new Intent(Intent.ACTION_MAIN);
					intent1.addCategory(Intent.CATEGORY_HOME);
					PendingIntent contentIntent=PendingIntent.getActivity(bService.this, 0, intent1, 0);
					bildirim.setLatestEventInfo(bService.this, "Afiyet Olsun!", "Yarýnki yemek hoþuna gidebilir!", contentIntent);
					notman.notify(0,bildirim);
					}
				}
				}
				}
			}).start();
		return Service.START_STICKY;
	}
@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		}
@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
	//Servis durdurulduðunda
		super.onDestroy();
	}
}
