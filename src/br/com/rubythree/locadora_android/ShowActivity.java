package br.com.rubythree.locadora_android;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ShowActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.third);
		
		ListView listview = (ListView) findViewById(R.id.list_view);
		//TextView textview = (TextView) findViewById(R.id.name);
		
		//criar arraylist
		final ArrayList<String> list = new ArrayList<String>();
		
		ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		
		final String response = makeRequest("http://192.168.1.6:3000/movies.json");
		
		Log.d("JSON",response);
		
		try {
			JSONArray json = new JSONArray(response);
			
			//adicionando itens na arraylist
			for (int i = 0; i < json.length(); i++) {
				String name = json.getJSONObject(i).getString("name");
				
				list.add(name);
			}
			
		} catch (JSONException e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}
		
		
		ArrayAdapter<String> myarrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
	    listview.setAdapter(myarrayAdapter);
	
	    listview.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
			
				JSONArray jsonInfo;	
			
				try {
					
					jsonInfo = new JSONArray(response);
					
					String jsonName = jsonInfo.getJSONObject(position).getString("name");
					String jsonGenre = jsonInfo.getJSONObject(position).getString("genre");
					Intent i = new Intent(ShowActivity.this, InfosActivity.class);
					
					i.putExtra("name", jsonName);
					i.putExtra("genre", jsonGenre);
					startActivity(i);
					
				} catch (JSONException e){
					e.printStackTrace();
				}
			}
	    });
	}
		
	//conectar com o server
	private String makeRequest(String urlAdress) {
	
		HttpURLConnection con = null;
		URL url = null;
		String response = null;
		
		try {
			url = new URL(urlAdress);
			con = (HttpURLConnection) url.openConnection();
			response = readStream(con.getInputStream());
		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		
		}finally{
			con.disconnect();
		
		}
		
		return response;
	}
	
	private String readStream(InputStream in){
	
		BufferedReader reader = null;
		StringBuilder builder = new StringBuilder();
		
		try{
			reader = new BufferedReader(new InputStreamReader(in));
			String line = null;
			
			while ((line = reader.readLine()) !=null){
				builder.append(line + "\n");
			}
			
		} catch (IOException e) {
		
			e.printStackTrace();
			// TODO: handle exception
		
		}finally{
			
			if (reader != null){
			
				try{
					reader.close();
					
				}catch (Exception e) {
					e.printStackTrace();
					// TODO: handle exception
				}
			}
		}
		return builder.toString();
	} 	
}	