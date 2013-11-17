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
private static final int ID_ABOUT=Menu.FIRST;
private SharedPreferences preferences;
@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button guncelle= (Button)findViewById(R.id.guncelle);
		guncelle.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent a=new Intent(MainActivity.this,bService.class);
				a.putExtra(PREF_TATLI, preferences.getBoolean(PREF_TATLI, false));
				startService(a);
				Toast.makeText(MainActivity.this,"Widget Güncellendi!", Toast.LENGTH_SHORT).show();
			    Intent kapat=new Intent(Intent.ACTION_MAIN);
				kapat.addCategory(Intent.CATEGORY_HOME);
				startActivity(kapat);
				}
		});
		preferences = getSharedPreferences("PREF_GENEL",
		        MODE_PRIVATE);
		// ayarlarioku();
	     yemek=(CheckBox)findViewById(R.id.Tyemek);
		 tatli=(CheckBox)findViewById(R.id.Ttatli);
		 Button kaydet=(Button)findViewById(R.id.kaydet);
		 Button about=(Button)findViewById(R.id.aboutButton);
		 about.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
			Intent toabout = new Intent(MainActivity.this,aboutActivity.class);	
			toabout.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
			startActivity(toabout);
			overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
			}
		});
		 kaydet.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ayarlariyaz();
				//Toast.makeText(MainActivity.this,"Ayarlarýnýz Kaydedildi!", Toast.LENGTH_SHORT).show();
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
