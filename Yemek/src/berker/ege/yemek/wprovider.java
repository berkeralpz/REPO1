package berker.ege.yemek;



import java.util.Calendar;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;


public class wprovider extends AppWidgetProvider {
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
		 if(gun==19){
        	yemek="Mercimek �orbas�, Mezgit Pane / K�ymal� Ye�il Mercimek, Sebzeli Makarna";
        }
		else if(gun==20){
        	yemek="Domates �orbas�, Tavuk D�ner, Pirin� Pilav�";
        }
		else if(gun==23){
        	yemek="�ehriye �orba, Etli Bezelye / Kabak Graten, Bulgur Pilav�";
        }
		else if(gun==24){
        	yemek="Domates Sorbas�, Tavuk Sote / Sebzeli Misket K�fte, Soslu Makarna";
        }
		else if(gun==25){
			yemek="Yo�urt �orba, Sal�al� Nohut / Mevsim T�rl�, Tavuklu Pilav";
		}
		else if(gun==26){
			yemek="Sebze �orba, �zmir K�fte / K�ymal� Ispanak, Eri�te";
		}
		else if(gun==27){
			yemek="Domates �orba, Hamburger, Patates K�zartmas�";
		}
		else if(gun==30){
			yemek="Yo�urt �orba, K�ymal� Ka�arl� Patates / �iftlik Kebab�, Pirin� Pilav�";
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
	}

	