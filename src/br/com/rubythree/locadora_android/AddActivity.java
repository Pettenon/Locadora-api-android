package br.com.rubythree.locadora_android;

import java.io.IOException;

import us.monoid.web.Resty;
import static us.monoid.web.Resty.*;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends Activity {
	
	final Resty r = new Resty();
	
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate (savedInstanceState);
		setContentView (R.layout.second);
		
		//Get na JSON e trazer os dados
		setThreadPolicy();
		
		try {
            postMovie();
		} catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
		}
	}

	//permissão API
	@SuppressLint("NewApi")
	public void setThreadPolicy() {
	    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	    StrictMode.setThreadPolicy(policy);
	}
	
	public void postMovie() throws IOException {		

		Button button = (Button) findViewById (R.id.next_button);
		
		button.setOnClickListener (new OnClickListener(){
		
			public void onClick (View v){
				
				Toast.makeText(AddActivity.this, "Saved", Toast.LENGTH_SHORT).show();
				
				try {
					final EditText nameEditText = (EditText) findViewById (R.id.name_edit_text);
					final EditText genreEditText = (EditText) findViewById (R.id.genre_edit_text);
					
					String name = nameEditText.getEditableText().toString();
					String genre = genreEditText.getEditableText().toString();
	
					r.json("http://192.168.1.6:3000/movies",
						form(
							data("movie[name]", name), data("movie[genre]", genre)
						)
					);
					
					
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
}
