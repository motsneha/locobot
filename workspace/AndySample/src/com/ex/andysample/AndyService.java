package com.ex.andysample;

import android.os.AsyncTask;


public class AndyService {
	 
	private String category;
	private String results;
	public AndyService()
	{
		category="null";
		results="null";
	}
	
	public AndyService(String category)
	{
		this.category=category;
	}
	

public String getJason() {
	new LongRunningGetIO().execute();

	return results;
}

private class LongRunningGetIO extends AsyncTask <Void, Void, String> {
	
//	protected String getASCIIContentFromEntity(HttpEntity entity) throws IllegalStateException, IOException {
//       InputStream in = entity.getContent();
//         StringBuffer out = new StringBuffer();
//         int n = 1;
//         while (n>0) {
//             byte[] b = new byte[4096];
//             n =  in.read(b);
//             if (n>0) out.append(new String(b, 0, n));
//         }
//         return out.toString();
//    }
//	
	@Override
	protected String doInBackground(Void... params) {
//		 HttpClient httpClient = new DefaultHttpClient();
//		 HttpContext localContext = new BasicHttpContext();
//         HttpGet httpGet = new HttpGet("http://www.cheesejedi.com/rest_services/get_big_cheese.php?puzzle=1");
         String text = null;
         int counter=0;
         
//         try {
//               HttpResponse response = httpClient.execute(httpGet, localContext);
//               HttpEntity entity = response.getEntity();
//               text = getASCIIContentFromEntity(entity);
//         } catch (Exception e) {
//        	 return e.getLocalizedMessage();
//         }
         for(int i=0;i<10;i++)
         {
        	 counter+=counter+2*i;
         }
         text=category+String.valueOf(counter);
         return text;
	}	
	
	protected void onPostExecute(String results) {
		if (results!=null) {
			
			
			//TextView et = (TextView) findViewById(R.id.textView1);
			//et.setText(results);
		
		
		}
		
	}
}
	
	
}
