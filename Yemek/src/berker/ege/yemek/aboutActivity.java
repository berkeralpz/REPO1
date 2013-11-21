package berker.ege.yemek;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class aboutActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		TextView linkv=(TextView)findViewById(R.id.linkview);
		linkv.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String url="https://github.com/berkeralpz/REPO1/tree/master/Yemek";
				Intent kaynakkod=new Intent(Intent.ACTION_VIEW);
				kaynakkod.setData(Uri.parse(url));
				startActivity(kaynakkod);
			}
		});
}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    // Respond to the action bar's Up/Home button
	    case android.R.id.home:
	    	Intent toabout = new Intent(aboutActivity.this,MainActivity.class);	
			toabout.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
			startActivity(toabout);
			overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
	        return true;
	    }
	    return super.onOptionsItemSelected(item);
	}
	}

