package berker.ege.yemek;

import java.util.Calendar;

import android.net.Uri;
import android.os.Build;
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
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
private static final String PREF_TATLI="PREF_TATLI";
private static final String PREF_YEMEK="PREF_YEMEK";
private static final String PREF_MENU = "PREF_MENU";
//private CheckBox tatli,yemek;
private static final int ID_WEB=Menu.FIRST;
private SharedPreferences preferences;
String gun;
@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button gorus=(Button)findViewById(R.id.gorus);
		gorus.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				String brand = Build.BRAND; 
				 String model = Build.MODEL; 
				 String cihaz=brand+" "+model;
				
				 final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
				emailIntent.setType("plain/text");
				emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"berkeralpz@gmail.com"});
				emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "YemekWidget"+"("+cihaz+")"+" Görüþlerim");
				emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Uygulama Hakkýndaki Görüþlerim: ");
				startActivity(Intent.createChooser(emailIntent, "Mail göndermek için uygulama seçiniz..."));
				//Görüþleri e-mail yoluyla ilet; konuya cihaz modelini yaz
			}
		});
		preferences = getSharedPreferences("PREF_GENEL",
		        MODE_PRIVATE);
		final TextView yemekview=(TextView)findViewById(R.id.yemekv);
		String syemek=preferences.getString(PREF_MENU, "");
		//yemekview.setText("Bugünkü yemek: "+yimek);
		Calendar c=Calendar.getInstance();
		if(c.get(Calendar.HOUR_OF_DAY)<13){
			gun=" Bugünkü";
		}
		else if (c.get(Calendar.HOUR_OF_DAY)>=13){
			gun=" Yarýnki";
			}
		yemekview.setText(gun+" "+"Yemek: "+syemek);
		//bugün-yarýn kavramlarýný ayýrt edebilmek için
		
		
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
			    kapat.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			    kapat.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
			    startActivity(kapat);
			    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
				}
		});
		preferences = getSharedPreferences("PREF_GENEL",
		        MODE_PRIVATE);
		// ayarlarioku();
	    // yemek=(CheckBox)findViewById(R.id.Tyemek);
		// tatli=(CheckBox)findViewById(R.id.Ttatli);
		//Önceden kullanýlan CheckBox'lar kullanýmdan kaldýrýldý. 
		//Bu özelliðe gerek yoktu ve Yüklenme süresini uzatabilirdi.
		
		Button kaydet=(Button)findViewById(R.id.kaydet);
		 Button about=(Button)findViewById(R.id.aboutButton);
		 about.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
			Intent toabout = new Intent(MainActivity.this,aboutActivity.class);	
			toabout.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
			toabout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
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
				 kapat.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
				 kapat.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(kapat);
				overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
				}
		});
		 ayarlariokuveuygula();
}
public void ayarlariyaz(){
		Editor editor=preferences.edit();
		//editor.putBoolean(PREF_TATLI, tatli.isChecked());
		//editor.putBoolean(PREF_YEMEK, yemek.isChecked());
		editor.apply();
	}
	public void ayarlariokuveuygula(){
		boolean tatlisecilimi=preferences.getBoolean(PREF_TATLI, false);
		 boolean yemeksecilimi=preferences.getBoolean(PREF_YEMEK, false);
		// tatli.setChecked(tatlisecilimi);
		// yemek.setChecked(yemeksecilimi);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		menu.add(Menu.NONE,ID_WEB,0,"Listeyi Web üzerinden görüntüle");
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()){
	case ID_WEB:
		String url="http://www.egelisesi.k12.tr/yemek-listesi.html";
		Intent weblist=new Intent(Intent.ACTION_VIEW);
		weblist.setData(Uri.parse(url));
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
		startActivity(weblist);
		//Web üzerinden yemek listesini açar
			return super.onOptionsItemSelected(item);
	}
		return false;
	}
	}
