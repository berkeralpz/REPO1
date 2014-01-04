package berker.ege.yemek;

import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
private static final String PREF_TATLI="PREF_TATLI";
private static final String PREF_YEMEK="PREF_YEMEK";
private static final String PREF_MENU = "PREF_MENU";
private SharedPreferences preferences;
boolean checkVal;
String gun;
String guni;
String syemek;
@SuppressLint("NewApi")
@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final Button gorus=(Button)findViewById(R.id.gorus);
	
	
		gorus.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				String brand = Build.BRAND; 
				 String model = Build.MODEL; 
				 String cihaz=brand+" "+model;
				
				 final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
				
				 emailIntent.setType("plain/text");
				emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"berkeralpz@gmail.com"});
				emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "YemekWidget"+"("+cihaz+")"+" G�r��lerim");
				emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Uygulama Hakk�ndaki G�r��lerim: ");
				final PackageManager pm = getPackageManager();
			    final List<ResolveInfo> matches = pm.queryIntentActivities(emailIntent, 0);
			    ResolveInfo best = null;
			    for(final ResolveInfo info : matches)
			        if (info.activityInfo.packageName.endsWith(".gm") || info.activityInfo.name.toLowerCase().contains("gmail"))
			            best = info;
			    if (best != null)
			        emailIntent.setClassName(best.activityInfo.packageName, best.activityInfo.name);
			   startActivity(emailIntent);
				//startActivity(Intent.createChooser(emailIntent, "Mail g�ndermek i�in uygulama se�iniz..."));
			   //Uygulama otomatik se�ilmeli...
				//Gorusleri e-mail yoluyla ilet; konuya cihaz modelini yaz
			}
		});
		preferences = getSharedPreferences("PREF_GENEL",
		        MODE_PRIVATE);
		final TextView yemekview=(TextView)findViewById(R.id.yemekv);
		
		
		
		//yemekview.setText("Bug�nk� yemek: "+yimek);
		
		final Calendar c=Calendar.getInstance();
		final int haftaningunu=c.get(Calendar.DAY_OF_WEEK);
		final int saatc=c.get(Calendar.HOUR_OF_DAY);
		if(haftaningunu==5&&saatc>=13){
			checkVal=true;
		}
		else{
			checkVal=false;
		}
		
		if(c.get(Calendar.HOUR_OF_DAY)<=12){
			gun=" Bug�nk�";
			guni="Bug�n";
		} //bug�n-yar�n kavramlar�n� ay�rt edebilmek i�in
		
		else if (c.get(Calendar.HOUR_OF_DAY)>=13){
			if(haftaningunu==6) {
			gun="Pazartesi g�n�";
    		guni="Pazartesi g�n�";
		}
			else{
				gun=" Yar�nki";
				guni="Yar�n";
			}
		}
		
		yemekal();
		
		if (syemek==""){
			Intent servis=new Intent(MainActivity.this,bService.class);
			startService(servis);
			
			new Timer().schedule(new TimerTask() {

		        @Override
		        public void run() {
		            runOnUiThread(new Runnable() {
		                public void run() {
		                	yemekal();
		                	if(haftaningunu==7||haftaningunu==1){
		                		gun="Pazartesi g�n�";
		                		guni="Pazartesi g�n�";
		            			yemekview.setText(gun+" "+"Yemek: "+syemek);
		            		}
		            		
		            		else{
		            			yemekview.setText(gun+" "+"Yemek: "+syemek);
		            		}
		                }
		            });
		        }
		    }, 0, 1);
		}
		
		
		
		if(haftaningunu==7||haftaningunu==1){
			gun="Pazartesi g�n�";
			guni="Pazartesi g�n�";
			yemekview.setText(gun+" "+"yemek: "+syemek);
		}
		
		else{
			yemekview.setText(gun+" "+"yemek: "+syemek);
		}
		
		Button guncelle= (Button)findViewById(R.id.guncelle);
		guncelle.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent a=new Intent(MainActivity.this,bService.class);
				a.putExtra(PREF_TATLI, preferences.getBoolean(PREF_TATLI, false));
				startService(a);
				
				Toast.makeText(MainActivity.this,"Widget G�ncellendi!", Toast.LENGTH_SHORT).show();
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
		//�nceden kullan�lan CheckBox'lar kullan�mdan kald�r�ld�. 
		//Bu �zelli�e gerek yoktu ve Y�klenme s�resini uzatabilirdi.
		
		Button kaydet=(Button)findViewById(R.id.kaydet);
		 Button about=(Button)findViewById(R.id.about);
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
				//Toast.makeText(MainActivity.this,"Ayarlar�n�z Kaydedildi!", Toast.LENGTH_SHORT).show();
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
		editor.apply();
	}
	public void ayarlariokuveuygula(){
		boolean tatlisecilimi=preferences.getBoolean(PREF_TATLI, false);
		 boolean yemeksecilimi=preferences.getBoolean(PREF_YEMEK, false);
	}
	public void yemekal(){
		syemek=preferences.getString(PREF_MENU, "");
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.main, menu);
	    return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	  
	    case R.id.action_share:
	    	Intent paylas= new Intent(Intent.ACTION_SEND);
	    	paylas.setType("text/plain");
	    	paylas.putExtra(Intent.EXTRA_TEXT, guni+" yemekte "+syemek+" var.");
	    	startActivity(Intent.createChooser(paylas, "Yeme�i payla�mak i�in uygulama se�iniz..."));
	    	return true;
	   
	    case R.id.action_settings:
	    		String url="http://www.egelisesi.k12.tr/yemek-listesi.html";
	    		Intent weblist=new Intent(Intent.ACTION_VIEW);
	    		weblist.setData(Uri.parse(url));
	    		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
	    		startActivity(weblist);
	    		//Web �zerinden yemek listesini a�ar

	            return true;
	           
	    }
	    return super.onOptionsItemSelected(item);
	}}
		