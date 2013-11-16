package berker.ege.yemek;




import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;


public class wprovider extends AppWidgetProvider {
	private SharedPreferences preferences;
	private static final String PREF_MENU="PREF_MENU";
	private static final String PREF_GENEL="PREF_GENEL";
	Context context;
	RemoteViews rmv;
	 AppWidgetManager awm;
	 ComponentName cmName;
	 String yemek;
	 
	@Override
public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) { 
		preferences = context.getSharedPreferences(PREF_GENEL, 0);
		yemek=preferences.getString(PREF_MENU, "Güncel Menü Ýndirilemedi! Baðlantýnýzý Kontol Ediniz...");
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

	