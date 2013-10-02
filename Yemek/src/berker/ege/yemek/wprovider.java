package berker.ege.yemek;



import java.util.Calendar;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;


public class wprovider extends AppWidgetProvider {
	public SharedPreferences preferences;
	Context context;
	RemoteViews rmv;
	 AppWidgetManager awm;
	 ComponentName cmName;
	 String yemek;
	@Override
public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) { 
		Calendar c=Calendar.getInstance();
		int gun=c.get(Calendar.DAY_OF_MONTH);
		int saat=c.get(Calendar.HOUR_OF_DAY);
		if(saat>13){
			gun=gun+1;
		}
		 if(gun==1){
        	yemek="Ezogelin Çorba, Gr. Izgara Tavuk/Etli Taze Fasulye, Soslu Makarna|Kalburabastý";
        	}
		else if(gun==2){
        	yemek="Yoðurt Çorba, Soslu Misket Köfte / Etli Bamya, Ezogelin Pilavý.|Þekerpare";
        }
		else if(gun==3){
        	yemek="Mercimek Çorbasý, Mezgit Pane / Kýymalý Yeþil Mercimek, Sebzeli Makarna.|Tahin helva";
        }
		else if(gun==4){
        	yemek="Domates Çorbasý, Tavuk Döner, Pirinç Pilavý.|Meyve";
        }
		else if(gun==7){
			yemek="Þehriye Çorba, Etli Bezelye / Kabak Graten, Bulgur Pilavý.|Meyve";
		}
		else if(gun==8){
			yemek="Domates Sorbasý, Tavuk Sote / Sebzeli Misket Köfte, Soslu Makarna.|Sütlü irmik tatlýsý,Ayran";
		}
		else if(gun==9){
			yemek="Yoðurt Çorba, Salçalý Nohut / Mevsim Türlü, Tavuklu Pilav.|Meyve";
		}
		else if(gun==10){
			yemek="Sebze Çorba, Ýzmir Köfte / Kýymalý Ispanak, Eriþte.|Kalburabastý";
		}
		else if(gun==11){
			yemek="Domates Çorba, Hamburger, Patates Kýzartmasý|Supangle,Ayran";
		}
		else if(gun==21){
			yemek="Yoðurt Çorba, Kýymalý Kaþarlý Patates / Çiftlik Kebabý, Pirinç Pilavý.|Meyve";
		}
        else if(gun==22){
			yemek="Ezogelin Çorba, Piliç Çýtýr / Patlýcan Oturtma, Eriþte.|Meyve";
		}
        else if(gun==23){
			yemek="Yoðurt Çorba, Soslu Misket Köfte / Etli Bamya, Ezogelin Pilavý|Þekerpare";
		}
        else if(gun==24){
			yemek="Mercimek Çorbasý, Mezgit Pane / Kýymalý Yeþil Mercimek, Sebzeli Makarna|Tahin helva";
		}
        else if(gun==25){
			yemek="Domates Çorbasý, Tavuk Döner, Pirinç Pilavý.|Meyve";
		}
        else if(gun==28){
			yemek="Ezogelin Çorba, Piliç Çýtýr / Patlýcan Oturtma, Eriþte.|Meyve";
		}
        else if(gun==30){
        	yemek="Mercimek Çorbasý, Mezgit Pane / Kýymalý Yeþil Mercimek, Sebzeli Makarna|Tahin helva";
		}
        else if(gun==31){
        	yemek="Domates Çorbasý, Tavuk Döner, Pirinç Pilavý.|Meyve";
        }
		else{
			yemek="Bu gün tatil!";
		}
		 rmv=new RemoteViews(context.getPackageName(), R.layout.widgetv);
		cmName = new ComponentName(context, wprovider.class);
		rmv.setTextViewText(R.id.textView1,yemek);
		appWidgetManager.updateAppWidget(cmName, rmv);
		Intent configIntent = new Intent(context, MainActivity.class);
		 PendingIntent configPendingIntent = PendingIntent.getActivity(context, 0, configIntent, 0);
		    rmv.setOnClickPendingIntent(R.id.wlay, configPendingIntent);
		    appWidgetManager.updateAppWidget(appWidgetIds, rmv);
		    // TODO Auto-generated method stub
		super.onUpdate(context, appWidgetManager, appWidgetIds);
	}
	public void bildir(){
		
	
	}
	}

	