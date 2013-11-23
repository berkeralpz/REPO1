package berker.ege.yemek;

import java.io.BufferedInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import android.widget.RemoteViews;

public class bService extends IntentService {
	
	private static final String TAG = "YemekWidgetIntentService";
	private static final String PREF_GENEL = "PREF_GENEL";
	private static final String PREF_MENU = "PREF_MENU";
	SharedPreferences preferences;
	Context context;
	RemoteViews rmv;
	 ComponentName cmName;
	
	 public bService() {
		super("YemekWidgetIntentService");
	}
	@Override
	protected void onHandleIntent(Intent intent) {
		String YemekListUrl = getResources().getString(R.string.url);
		if(isYemekTakip(YemekListUrl)) {
		}
		}
	
	private boolean isYemekTakip(String YemekListUrl) {
		
		HttpURLConnection urlConnection = null;
		
		try {
			URL url = new URL(YemekListUrl);
			urlConnection = (HttpURLConnection) url.openConnection();
            int sonucKodu = urlConnection.getResponseCode();
			if (sonucKodu == HttpURLConnection.HTTP_OK) {
				BufferedInputStream stream = new BufferedInputStream(urlConnection.getInputStream());
				return isYemekTakipInputStream(stream);
			}
			
		} catch (Exception e) {
			Log.d(TAG, "HTTP baðlantýsý kurulurken hata oluþtu", e);
		
		} finally {
			if (urlConnection != null)
				urlConnection.disconnect();
		}
		return false;
		}
	private boolean isYemekTakipInputStream(BufferedInputStream stream) {
		
		try {
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document document = docBuilder.parse(stream);
			
			Element firstCube = (Element) document.getElementsByTagName("Cube").item(0);
			Element secondCube = (Element) firstCube.getElementsByTagName("Cube").item(0);
			NodeList YemekNodeList = secondCube.getElementsByTagName("Cube");
			
			int YemekNodeListLength = YemekNodeList.getLength();
			for (int i = 0; i < YemekNodeListLength; i++) {
				Element MenuElement = (Element) YemekNodeList.item(i);
				String buguntarih = MenuElement.getAttribute("tarih");
				Calendar c=Calendar.getInstance();
				
				int tarihb=c.get(Calendar.DAY_OF_MONTH);
				int saat =c.get(Calendar.HOUR_OF_DAY);
				
				int haftaningunu=c.get(Calendar.DAY_OF_WEEK);
				
				if(haftaningunu==7||haftaningunu==8){
					if(haftaningunu==7){
						tarihb=tarihb+2;
					}
					else if(haftaningunu==8){
						tarihb++;
					}
				}
				else if(saat>=12){
					tarihb++;
				}
				
				String tarihc=String.valueOf(tarihb);
				preferences=getSharedPreferences(PREF_GENEL,MODE_PRIVATE);
				if(tarihc.equals(buguntarih)){
					String bugunyemek = MenuElement.getAttribute("yemek");
					
					Editor editor=preferences.edit();
					editor.putString(PREF_MENU, bugunyemek);
					editor.apply();
					
					Intent intent = new Intent(this, wprovider.class);
					intent.setAction("android.appwidget.action.APPWIDGET_UPDATE");
					
					int ids[] = AppWidgetManager.getInstance(getApplication()).getAppWidgetIds(new ComponentName(getApplication(), wprovider.class));
					intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS,ids);
					sendBroadcast(intent);
					}
				
			}
			
			stopSelf();
			
		} 
		
		catch (Exception e) {
			
			Log.d(TAG, "XML parse edilirken hata oluþtu", e);
			}
		stopSelf();
		return false;
	
	}
	
	}

