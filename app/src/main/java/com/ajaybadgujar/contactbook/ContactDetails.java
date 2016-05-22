package com.ajaybadgujar.contactbook;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ContactDetails extends Activity {	
	
	Bundle extras;
	
	private ContactHandler handler;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);	
		setContentView(R.layout.contact_details);	
		
		
		// Get intent data
		extras = getIntent().getExtras();
		
		handler = new ContactHandler(getApplicationContext());
		
		ImageView iv_photo = (ImageView) findViewById(R.id.iv_contact_photo);
		iv_photo.setImageBitmap(BitmapFactory.decodeFile(extras.getString("photograph")));
		
		TextView tv_name = (TextView) findViewById(R.id.tv_contact_name);
		tv_name.setText(extras.getString("name"));
		
		TextView tv_phone = (TextView) findViewById(R.id.tv_contact_phone);
		tv_phone.setText(extras.getString("phone"));
		
		TextView tv_email = (TextView) findViewById(R.id.tv_contact_email);
		tv_email.setText(extras.getString("email"));
		
		TextView tv_address = (TextView) findViewById(R.id.tv_contact_address);
		tv_address.setText(extras.getString("address"));
		
		Button btn_back = (Button) findViewById(R.id.btn_back_to_contact);
		btn_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				finish();
				Intent intent = new Intent(ContactDetails.this, Main.class);
				startActivity(intent);
			}
		});
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(1, 1, 0, "Edit");
		menu.add(1,2,1,"Delete");
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		 
		int itemid = item.getItemId();	
		
		switch(itemid){
			case 1:				
				//Toast.makeText(getApplicationContext(), "Edit", Toast.LENGTH_LONG).show();
				EditContact();
				break;
			case 2:
				// Deleteing Contact
				if(handler.deleteContact(extras.getInt("id"))){
					Toast.makeText(getApplicationContext(), "Contact has been deleted.", Toast.LENGTH_SHORT).show();
					Intent intent = new Intent(ContactDetails.this, Main.class);
					startActivity(intent);
				}
				break;
		
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	
	public void EditContact(){		
		Intent intent = new Intent(ContactDetails.this, EditContact.class);
		intent.putExtras(extras);
		startActivity(intent);
	}
}
