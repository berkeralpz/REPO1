package berker.ege.yemek;

import android.os.Bundle;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ToggleButton;


public class MainActivity extends Activity {
public static final String PREF_TATLI="PREF_TATLI";
public static final String PREF_YEMEK="PREF_YEMEK";
private ToggleButton tatli,yemek;
SharedPreferences preferences;
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
				}
		});
		 ayarlarioku();
}
public void ayarlariyaz(){
		Editor editor=preferences.edit();
		editor.putBoolean(PREF_TATLI, tatli.isChecked());
		editor.putBoolean(PREF_YEMEK, yemek.isChecked());
		editor.apply();
	}
	public void ayarlarioku(){
		boolean tatlisecilimi=preferences.getBoolean(PREF_TATLI, false);
		 boolean yemeksecilimi=preferences.getBoolean(PREF_YEMEK, false);
		 tatli.setChecked(tatlisecilimi);
		 yemek.setChecked(yemeksecilimi);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
