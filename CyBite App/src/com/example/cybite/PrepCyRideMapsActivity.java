package com.example.cybite;


import java.util.ArrayList;

import com.DatabaseAPI.Restaurant;



import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

public class PrepCyRideMapsActivity extends Activity {
int[] checkboxes = {R.id.checkBox1, R.id.checkBox2, R.id.checkBox3, R.id.checkBox4, R.id.checkBox5, R.id.checkBox6,
					R.id.checkBox7, R.id.checkBox8,R.id.checkBox9, R.id.checkBox10, R.id.checkBox11, R.id.checkBox12, 
					R.id.checkBox13, R.id.checkBox14, R.id.checkBox15, R.id.checkBox16, R.id.checkBox17, R.id.checkBox18};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prep_cyridemaps);
        
        Button viewTimesButton = (Button) findViewById(R.id.ViewTimes);
        viewTimesButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.cyride.com/index.aspx?page=897"));
				startActivity(browserIntent);
			} });
        
        Button submitButton = (Button) findViewById(R.id.ShowRoutes);
        submitButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ArrayList<String> checkedBoxes = new ArrayList<String>();
				if(((CheckBox) findViewById(R.id.checkBox20)).isChecked()) {
					checkedBoxes.add(((CheckBox)findViewById(R.id.checkBox20)).getText().toString());
				}
				else {
				for(int i = 0; i < checkboxes.length; i++)
				{
					CheckBox box = (CheckBox) findViewById(checkboxes[i]);
					if((box.isChecked() && box.isEnabled()) || ((CheckBox) findViewById(R.id.checkBox19)).isChecked())
					{
						checkedBoxes.add(box.getText().toString());
					}				
				}
				}
				 Intent intent = new Intent(PrepCyRideMapsActivity.this, CyRideMapsActivity.class);
				 intent.putExtra("restaurant", (Restaurant) getIntent().getExtras().get("restaurant"));
				 intent.putExtra("CheckedBoxes", checkedBoxes);
				 PrepCyRideMapsActivity.this.startActivity(intent);
			}
		});
        
        ((CheckBox) findViewById(R.id.checkBox19)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
    		
    		@Override
    		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
    			for(int i = 0; i < checkboxes.length; i++)
    			{
    				if(isChecked) {
    					((CheckBox) findViewById(checkboxes[i])).setEnabled(false);
    				} else {
    					((CheckBox) findViewById(checkboxes[i])).setEnabled(true);
    				}
    			}
    			if(isChecked) {
    				((CheckBox) findViewById(R.id.checkBox20)).setEnabled(false);
    			} else {
    				((CheckBox) findViewById(R.id.checkBox20)).setEnabled(true);
    			}
    			
    		}
    	});
        
        ((CheckBox) findViewById(R.id.checkBox20)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
    		
    		@Override
    		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
    			for(int i = 0; i < checkboxes.length; i++)
    			{
    				if(isChecked) {
    					((CheckBox) findViewById(checkboxes[i])).setEnabled(false);
    				} else {
    					((CheckBox) findViewById(checkboxes[i])).setEnabled(true);
    				}
    			}
    			if(isChecked) {
    				((CheckBox) findViewById(R.id.checkBox19)).setEnabled(false);
    			} else {
    				((CheckBox) findViewById(R.id.checkBox19)).setEnabled(true);
    			}
    			
    		}
    	});
    }
}
