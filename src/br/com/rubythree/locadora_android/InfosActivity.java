package br.com.rubythree.locadora_android;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class InfosActivity extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.info);
		
		//Inserir valor na Intent
		Bundle infos = getIntent().getExtras();
		String name = infos.getString("name");
		String genre = infos.getString("genre");
		
		//Criando as labels
		TextView name_info = (TextView)findViewById(R.id.name);
		TextView genre_info = (TextView)findViewById(R.id.genre);
		
		name_info.setText(getString(R.string.movie_name, name));
		genre_info.setText(getString(R.string.movie_genre, genre));
		
		
	}
}
