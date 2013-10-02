package berker.ege.yemek;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class aboutActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
		TextView linkv=(TextView)findViewById(R.id.linkview);
		linkv.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String url="https://github.com/berkeralpz/REPO1/tree/master/Yemek";
				Intent kaynakkod=new Intent(Intent.ACTION_VIEW);
				kaynakkod.setData(Uri.parse(url));
				startActivity(kaynakkod);
				/*MainActivity  sinif = new MainActivity();
				sinif.ayarlarioku();
				//baþka sýnýftan metod çaðýrmak için//
				*/
			}
		});
}
	}

