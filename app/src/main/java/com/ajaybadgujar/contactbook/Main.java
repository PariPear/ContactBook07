// Main.java
package com.ajaybadgujar.contactbook;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

public class Main extends Activity {
	
	private ContactHandler handler;
	private List<Contact> contacts;
	ListView lv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		handler = new ContactHandler(getApplicationContext());
		
		Button btn_add_new = (Button) findViewById(R.id.btn_add_contact);
		btn_add_new.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				Intent intent = new Intent(Main.this, NewContact.class);
				startActivity(intent);
			}
		});	
		
		lv = (ListView) findViewById(R.id.lv_contact_list);
		
		loadContactData();	
		
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				Intent intent = new Intent(Main.this, ContactDetails.class);
				intent.putExtra("id", contacts.get(position).getID());
				intent.putExtra("name", contacts.get(position).getName());
				intent.putExtra("phone", contacts.get(position).getPhoneNumber());
				intent.putExtra("email", contacts.get(position).getEmail());
				intent.putExtra("address", contacts.get(position).getPostalAddress());
				intent.putExtra("photograph", contacts.get(position).getPhotograph());
				startActivity(intent);
				
			}
						
		});
		
	}
	
	
	
	private void loadContactData(){
		// Code for loading contact list in ListView		
		// Reading all contacts
		contacts = handler.readAllContacts();
		
		// Initialize Custom Adapter 
		CustomAdapter adapter = new CustomAdapter(this, contacts);
		
		// Set Adapter to ListView
		lv.setAdapter(adapter);		
		
		// See the log int LogCat
		for(Contact c : contacts){
			String record = "ID=" + c.getID() + " | Name=" + c.getName() + " | " + c.getPhoneNumber();			
			Log.d("Record",record);
		}
		
	}	
	

}
