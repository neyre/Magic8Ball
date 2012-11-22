package com.nickeyre.magic8ball;

import java.util.Random;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.Toast;

public class WidgetProvider extends AppWidgetProvider {

	private final String[] responses = {"It is certain","It is decidedly so","Without a doubt","Yes – definitely","You may rely on it","As I see it, yes","Most likely","Outlook good","Yes","Signs point to yes","Reply hazy, try again","Ask again later","Better not tell you now","Cannot predict now","Concentrate and ask again","Don't count on it","My reply is no","My sources say no","Outlook not so good","Very doubtful"};
	
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		//Method called when it's time to setup the widget

		//Get IDs of all widgets
		ComponentName thisWidget = new ComponentName(context,WidgetProvider.class);
		int[] allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);
		
		//Register onClickListener for each Widget
		for (int widgetId : allWidgetIds) {
			Intent intent = new Intent(context, WidgetProvider.class);
			intent.setAction("showToast");
			PendingIntent pendingIntent = PendingIntent.getBroadcast(context,0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
			RemoteViews remoteViews = new RemoteViews(context.getPackageName(),R.layout.widget_layout);
			remoteViews.setOnClickPendingIntent(R.id.imageViewWidget, pendingIntent);
			appWidgetManager.updateAppWidget(widgetId, remoteViews);
		}
	}
	
	@Override
    public void onReceive(Context context, Intent intent) {
		//Method an intent.  If the requested action is to show the toast, do it.  Otherwise, proceed as normal.
		
		if(intent.getAction() == "showToast"){
			Random rand = new Random();
	 		Toast.makeText(context,responses[rand.nextInt(responses.length)],Toast.LENGTH_SHORT).show();
		}else{
			super.onReceive(context, intent);
		}
    }
} 