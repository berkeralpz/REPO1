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
        	yemek="Ezogelin �orba, Gr. Izgara Tavuk/Etli Taze Fasulye, Soslu Makarna|Kalburabast�";
        	}
		else if(gun==2){
        	yemek="Yo�urt �orba, Soslu Misket K�fte / Etli Bamya, Ezogelin Pilav�.|�ekerpare";
        }
		else if(gun==3){
        	yemek="Mercimek �orbas�, Mezgit Pane / K�ymal� Ye�il Mercimek, Sebzeli Makarna.|Tahin helva";
        }
		else if(gun==4){
        	yemek="Domates �orbas�, Tavuk D�ner, Pirin� Pilav�.|Meyve";
        }
		else if(gun==7){
			yemek="�ehriye �orba, Etli Bezelye / Kabak Graten, Bulgur Pilav�.|Meyve";
		}
		else if(gun==8){
			yemek="Domates Sorbas�, Tavuk Sote / Sebzeli Misket K�fte, Soslu Makarna.|S�tl� irmik tatl�s�,Ayran";
		}
		else if(gun==9){
			yemek="Yo�urt �orba, Sal�al� Nohut / Mevsim T�rl�, Tavuklu Pilav.|Meyve";
		}
		else if(gun==10){
			yemek="Sebze �orba, �zmir K�fte / K�ymal� Ispanak, Eri�te.|Kalburabast�";
		}
		else if(gun==11){
			yemek="Domates �orba, Hamburger, Patates K�zartmas�|Supangle,Ayran";
		}
		else if(gun==21){
			yemek="Yo�urt �orba, K�ymal� Ka�arl� Patates / �iftlik Kebab�, Pirin� Pilav�.|Meyve";
		}
        else if(gun==22){
			yemek="Ezogelin �orba, Pili� ��t�r / Patl�can Oturtma, Eri�te.|Meyve";
		}
        else if(gun==23){
			yemek="Yo�urt �orba, Soslu Misket K�fte / Etli Bamya, Ezogelin Pilav�|�ekerpare";
		}
        else if(gun==24){
			yemek="Mercimek �orbas�, Mezgit Pane / K�ymal� Ye�il Mercimek, Sebzeli Makarna|Tahin helva";
		}
        else if(gun==25){
			yemek="Domates �orbas�, Tavuk D�ner, Pirin� Pilav�.|Meyve";
		}
        else if(gun==28){
			yemek="Ezogelin �orba, Pili� ��t�r / Patl�can Oturtma, Eri�te.|Meyve";
		}
        else if(gun==30){
        	yemek="Mercimek �orbas�, Mezgit Pane / K�ymal� Ye�il Mercimek, Sebzeli Makarna|Tahin helva";
		}
        else if(gun==31){
        	yemek="Domates �orbas�, Tavuk D�ner, Pirin� Pilav�.|Meyve";
        }
		else{
			yemek="Bu g�n tatil!";
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

	