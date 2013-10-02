package berker.ege.yemek;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class MainActivity extends Activity {
private static final String PREF_TATLI="PREF_TATLI";
private static final String PREF_YEMEK="PREF_YEMEK";
private CheckBox tatli,yemek;
private static final String PREF_GENEL="PREF_GENEL";
private static final int ID_ABOUT=Menu.FIRST;
private SharedPreferences preferences;
@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		preferences = getSharedPreferences("PREF_GENEL",
		        MODE_PRIVATE);
		Intent a=new Intent(MainActivity.this,bService.class);
		a.putExtra(PREF_TATLI, preferences.getBoolean(PREF_TATLI, false));
		startService(a);
		// ayarlarioku();
	     yemek=(CheckBox)findViewById(R.id.Tyemek);
		 tatli=(CheckBox)findViewById(R.id.Ttatli);
		 Button kaydet=(Button)findViewById(R.id.kaydet);
		 kaydet.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ayarlariyaz();
				Toast.makeText(MainActivity.this,"Ayarlarýnýz Kaydedildi!", Toast.LENGTH_SHORT).show();
			    Intent kapat=new Intent(Intent.ACTION_MAIN);
				kapat.addCategory(Intent.CATEGORY_HOME);
				startActivity(kapat);
				}
		});
		 ayarlariokuveuygula();
}
public void ayarlariyaz(){
		Editor editor=preferences.edit();
		editor.putBoolean(PREF_TATLI, tatli.isChecked());
		editor.putBoolean(PREF_YEMEK, yemek.isChecked());
		editor.apply();
	}
	public void ayarlariokuveuygula(){
		boolean tatlisecilimi=preferences.getBoolean(PREF_TATLI, false);
		 boolean yemeksecilimi=preferences.getBoolean(PREF_YEMEK, false);
		 tatli.setChecked(tatlisecilimi);
		 yemek.setChecked(yemeksecilimi);
	}
	/*public void yemekbildirimyolla(){
		boolean yemeksecilimi=preferences.getBoolean(PREF_YEMEK, false);
		if(yemeksecilimi=true){
	NotificationManager notman;
    notman=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
	Notification bildirim=new Notification(R.drawable.floating,"Yemek Güzel!",System.currentTimeMillis());
	Intent intent=new Intent(Intent.ACTION_MAIN);
	intent.addCategory(Intent.CATEGORY_HOME);
	intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	PendingIntent contentIntent=PendingIntent.getActivity(this, 0, intent, 0);
	bildirim.setLatestEventInfo(this, "Yemek Güzel", "Bu gün yemek hoþuna gidebilir!", contentIntent);
	notman.notify(0,bildirim);
	}
		}
	public void tatlibildirimyolla(){
		boolean tatlisecilimi=preferences.getBoolean(PREF_TATLI, false);
		 if(tatlisecilimi=true){
			 final ToneGenerator tg = new ToneGenerator(AudioManager.STREAM_NOTIFICATION, 100);
		     tg.startTone(ToneGenerator.TONE_PROP_BEEP);
		/*NotificationManager notman;
		notman=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
	Notification bildirim=new Notification(R.drawable.floating,"Yemekte tatlý var!",System.currentTimeMillis());
	Intent intent=new Intent(Intent.ACTION_MAIN);
	intent.addCategory(Intent.CATEGORY_HOME);
	intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	PendingIntent contentIntent=PendingIntent.getActivity(this, 0, intent, 0);
	bildirim.setLatestEventInfo(this, "Yemek Güzel", "Bu gün yemekte tatlý var!", contentIntent);
	notman.notify(0,bildirim);
}}*/
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		menu.add(Menu.NONE,ID_ABOUT,0,"Uygulama Hakkýnda");
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()){
	case ID_ABOUT:
		Intent hakkindayiac=new Intent(MainActivity.this,aboutActivity.class);
			startActivity(hakkindayiac);
		return super.onOptionsItemSelected(item);
	}
		return false;
	}
	}
