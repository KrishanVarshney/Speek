package com.example.speek;

import java.util.Locale;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends ActionBarActivity {

	EditText et1;
	Button b1;
	TextToSpeech tts;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		tts=new TextToSpeech(MainActivity.this, new B());
		
		et1=(EditText) findViewById(R.id.editText1);
		b1=(Button) findViewById(R.id.button1);
		
		b1.setOnClickListener(new A());
	}
	class A implements OnClickListener
	{
		@Override
		public void onClick(View v) 
		{
			speakOut();
		}	
	}
	
	private void speakOut() 
	{	 
        	String text = et1.getText().toString();
         	tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    	}
	
class B implements TextToSpeech.OnInitListener
{
	@Override
	public void onInit(int status) 
	{
		if (status == TextToSpeech.SUCCESS) 
		{
		    	int result = tts.setLanguage(Locale.UK);
if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) 
            		{
    Toast.makeText(MainActivity.this, "This Language is not supported", Toast.LENGTH_LONG).show();
            		} 
            		else 
            		{
                		b1.setEnabled(true);
                		speakOut();
            		}
        	} 
	 	else 
        	{
	      		Toast.makeText(MainActivity.this, "Initialization Failed",Toast.LENGTH_LONG).show();
	  	}
}
} //end of class B
@Override
protected void onDestroy() 
{
	if (tts != null) 
	{
           tts.stop();
           tts.shutdown();
      }
	super.onDestroy(); //to destroy the current activity
}
}

