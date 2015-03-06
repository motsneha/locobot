package bot.location.locobotapp;


import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.support.v4.app.NotificationCompat;
import android.telephony.SmsManager;
import android.util.Log;

public class ProximityIntentReceiver extends BroadcastReceiver
{

	String receivepno;
	String receivemessage;
	
	private  int NOTIFICATION_ID = 10000;
	public static final String EVENT_ID_INTENT_SMSMESSAGE = "EventIdIntentMessage";
	public static final String EVENT_ID_INTENT_PHONENO = "EventIdIntentPhoneNo";
	long pattern1[] = {0,1000,500 };
	
	@Override
	public void onReceive(Context context, Intent intent)
	{
		
		
		
		
		 receivemessage = intent.getStringExtra(EVENT_ID_INTENT_SMSMESSAGE);
		 receivepno = intent.getStringExtra(EVENT_ID_INTENT_PHONENO);
          
		 
		 Log.d( "message", receivemessage);
		 Log.d( "phone no",  receivepno);
		 String key = LocationManager.KEY_PROXIMITY_ENTERING;
		Boolean entering = intent.getBooleanExtra(key, false);
		
		if (entering) {
			Log.d(getClass().getSimpleName(), "entering");
			
			try{
			SmsManager smsManager = SmsManager.getDefault();
			smsManager.sendTextMessage(receivepno, null, receivemessage, null, null);
			
			NotificationCompat.Builder mBuilder =
			        new NotificationCompat.Builder(context)
			        .setSmallIcon(R.drawable.message)
			        .setContentTitle("PROXIMITY MESSAGE")
			        .setContentText("SMS SENT" + receivemessage)
			         .setWhen(System.currentTimeMillis())
			         .setAutoCancel(true)
			         .setVibrate(pattern1);   
			
			NotificationManager mmNotificationManager =
				    (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
				// mId allows you to update the notification later on.
				mmNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
			
			}
			catch(Exception e){
				e.printStackTrace();
			}
			Log.v( "message send", "message" );
	//		Toast.makeText(context, "SMS Sent! to "+ pno,
		//				Toast.LENGTH_LONG).show();
			
		/*	NotificationManager notificationManager = 
					(NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
			//	Intent notificationIntent = new Intent(context, SMSActivity.class); 
			//	PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, notificationIntent, 0);
				Notification notification = createNotification();
				
			//	notification.setLatestEventInfo(context, "Proximity Message!", "MESSAGE SEND .", pendingIntent);
				notificationManager.notify(NOTIFICATION_ID, notification);
		*/
			
			
				
			
				
		}
		else {
			Log.d(getClass().getSimpleName(), "exiting");
		}
		
		
	}
/*	private Notification createNotification() {
		Notification notification = new Notification();
		
		notification.icon = R.drawable.message;
		notification.when = System.currentTimeMillis();
		
		notification.flags |= Notification.FLAG_AUTO_CANCEL;
		notification.flags |= Notification.FLAG_SHOW_LIGHTS;
		
		notification.defaults |= Notification.DEFAULT_VIBRATE;
		notification.defaults |= Notification.DEFAULT_LIGHTS;
		
		notification.ledARGB = Color.WHITE;
		notification.ledOnMS = 1500;
		notification.ledOffMS = 1500;
		
		return notification;
	}
	
	*/
}
