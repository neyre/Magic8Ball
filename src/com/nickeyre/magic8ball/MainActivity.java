package com.nickeyre.magic8ball;


import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity implements ShakeListener.OnShakeListener {

	private ShakeListener mShaker;
	private final String[] responses = {"It is certain","It is decidedly so","Without a doubt","Yes – definitely","You may rely on it","As I see it, yes","Most likely","Outlook good","Yes","Signs point to yes","Reply hazy, try again","Ask again later","Better not tell you now","Cannot predict now","Concentrate and ask again","Don't count on it","My reply is no","My sources say no","Outlook not so good","Very doubtful"};
	//private final String[] responses = {"Yes","No"};
	private Random rand = new Random();
	private TextView textView;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        
    	textView = (TextView) findViewById(R.id.textView1);
        
        mShaker = new ShakeListener(this);
        mShaker.setOnShakeListener(this);
    }

	
    public void onResume(){
    	mShaker.resume();
    	super.onResume();
    }
    
    public void onPause(){
    	mShaker.pause();
    	super.onPause();
    }
    
    public void onTap(View view){
    	changeResponse();
    }
    
    public void onShake(){
    	changeResponse();
    }
    
    public void changeResponse(){
    	textView.setText(responses[rand.nextInt(responses.length)]);    	
    }
    
    
}
