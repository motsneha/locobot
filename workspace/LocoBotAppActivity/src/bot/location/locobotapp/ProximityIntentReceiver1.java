package bot.location.locobotapp;




import android.app.NotificationManager;
	import android.content.BroadcastReceiver;
	import android.content.Context;
	import android.content.Intent;
	import android.location.LocationManager;
	import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

	public class ProximityIntentReceiver1 extends BroadcastReceiver
	{

		long eventID;
		String message;
		public static final String EVENT_ID_INTENT_EXTRA = "EventIDIntentExtraKey";
		public static final String EVENT_ID_INTENT_MESSAGE_EXTRA = "EventIDIntentExtraMessage";
		private  int NOTIFICATION_ID = 1000000;
		long pattern[] = {0,1000,500 };
		
		@Override
		public void onReceive(Context context, Intent intent)
		{
			eventID = intent.getLongExtra(EVENT_ID_INTENT_EXTRA, -1);
			message = intent.getStringExtra(EVENT_ID_INTENT_MESSAGE_EXTRA);
			
			String key = LocationManager.KEY_PROXIMITY_ENTERING;
			
			//	 message = intent.getStringExtra("message1");
			 
	          
			 Log.v( "message", "message =" + message);
			 
			 
			Boolean entering = intent.getBooleanExtra(key, false);
			
			if (entering) {
				Log.d(getClass().getSimpleName(), "entering");
			//	Toast.makeText(context, "entering"+eventID, Toast.LENGTH_SHORT).show();
				
			
				
			/*	NotificationManager notificationManager = 
						(NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
				//	Intent notificationIntent = new Intent(context, SMSActivity.class); 
				//	PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, notificationIntent, 0);
					Notification notification = createNotification();
					
				//	notification.setLatestEventInfo(context, "Proximity Message!", "MESSAGE SEND .", pendingIntent);
					notificationManager.notify(NOTIFICATION_ID, notification);
			*/
				
				NotificationCompat.Builder mBuilder =
				        new NotificationCompat.Builder(context)
				        .setSmallIcon(R.drawable.ic_menu_notifications)
				        .setContentTitle("PROXIMITY REMINDER")
				        .setContentText(message)
				         .setWhen(System.currentTimeMillis())
				         .setAutoCancel(true)
				         .setVibrate(pattern);  
				
				NotificationManager mNotificationManager =
					    (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
					// mId allows you to update the notification later on.
					mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
					
					 NOTIFICATION_ID = (int) (NOTIFICATION_ID + eventID);
					
			}
			else {
			//	Log.d(getClass().getSimpleName(), "exiting");
				Toast.makeText(context, "exiting"+ eventID, Toast.LENGTH_SHORT).show();
			}
			
			
			
		}
	
}
