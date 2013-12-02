package com.example.cybite;


import java.util.ArrayList;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class PrepCyRideMapsActivity extends Activity {
int[] checkboxes = {R.id.checkBox1, R.id.checkBox2, R.id.checkBox3, R.id.checkBox4, R.id.checkBox5, R.id.checkBox6,
					R.id.checkBox7, R.id.checkBox8,R.id.checkBox9, R.id.checkBox10, R.id.checkBox11, R.id.checkBox12, 
					R.id.checkBox13, R.id.checkBox14, R.id.checkBox15, R.id.checkBox16, R.id.checkBox17, R.id.checkBox18};
ArrayList<String> checkedBoxes = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prep_cyridemaps);
        
        Button submitButton = (Button) findViewById(R.id.ShowRoutes);
        submitButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				for(int i = 0; i < checkboxes.length; i++)
				{
					CheckBox box = (CheckBox) findViewById(checkboxes[i]);
					if(box.isChecked())
					{
						checkedBoxes.add(box.getText().toString());
					}				
				}
				 Intent intent = new Intent(PrepCyRideMapsActivity.this, CyRideMapsActivity.class);
				 intent.putExtra("CheckedBoxes", checkedBoxes);
				 PrepCyRideMapsActivity.this.startActivity(intent);
			}
		});
    }
}
