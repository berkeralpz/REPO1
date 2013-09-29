package berker.ege.yemek;

import android.os.Bundle;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ToggleButton;


public class MainActivity extends Activity {
public static final String PREF_TATLI="PREF_TATLI";
public static final String PREF_YEMEK="PREF_YEMEK";
private ToggleButton tatli,yemek;
public SharedPreferences preferences;
private static final int ID_ABOUT=Menu.FIRST;
@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// ayarlarioku();
		preferences=getPreferences(MODE_PRIVATE); 
		yemek=(ToggleButton)findViewById(R.id.Tyemek);
		 tatli=(ToggleButton)findViewById(R.id.Ttatli);
		 Button kaydet=(Button)findViewById(R.id.kaydet);
		 kaydet.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ayarlariyaz();
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
	public void ayarlarioku(){
		boolean tatlisecilimi=preferences.getBoolean(PREF_TATLI, false);
		 boolean yemeksecilimi=preferences.getBoolean(PREF_YEMEK, false);
	}
	public void bildirimyolla(){
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
