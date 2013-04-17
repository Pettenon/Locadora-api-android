package br.com.rubythree.locadora_android;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		Button movieButton = (Button)findViewById(R.id.main_button);
		Button genreButton = (Button)findViewById(R.id.second_button);
		
		movieButton.setOnClickListener (new OnClickListener(){
			
			@Override
			public void onClick (View v){
				Intent intent = new Intent (MainActivity.this, AddActivity.class);
				startActivity (intent);
			}
		});
		
		genreButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent in = new Intent(MainActivity.this, ShowActivity.class);
				startActivity(in);
			}
		});
	}
}
