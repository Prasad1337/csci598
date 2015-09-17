package csci.fileassignment_pkamat;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;


public class MainActivity extends Activity implements OnClickListener {

	String file = Environment.getExternalStorageDirectory() + "/CSCI598.txt";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button rea = (Button) findViewById(R.id.reads1);   
        rea.setOnClickListener(this);
        
        Button ap = (Button) findViewById(R.id.appends1);   
        ap.setOnClickListener(this);
        
        Button rep = (Button) findViewById(R.id.replaces1);   
        rep.setOnClickListener(this);

    }

    private void toast(String text)
    {
        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
        toast.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


	@SuppressWarnings("resource")
	@Override
	public void onClick(View v) {
		
		TextView tv=(TextView)findViewById(R.id.textView1);
		EditText et=(EditText)findViewById(R.id.editText1);	
		String txt=et.getText().toString();	
		
        switch(v.getId()) {
        
        case R.id.reads1:
        	
        	   try
        	    {
        		   FileInputStream fis = new FileInputStream(file);
        		   BufferedReader bfr = new BufferedReader(new InputStreamReader(fis));
                   String in;
                   StringBuffer stringBuffer = new StringBuffer();        
                   
                   while ((in = bfr.readLine()) != null) {
                       stringBuffer.append(in + "\n");
                   }
                   
                   tv.setText(stringBuffer.toString());
        	        
        	    }
        	   
        	    catch (Exception ex)
        	    {
        	        toast("Error loading file: " + ex.getLocalizedMessage());
        	    }

        	break;
          
        case R.id.appends1:

        	 try
        	  {
        		 FileWriter writer = new FileWriter(file,true);
        		 writer.write(txt+"\n"); 
        		 writer.flush();
        		 writer.close();
        		
        	     et.setText("");
        	     toast("File appended!");
        	    }
        	 
        	    catch (Exception ex)
        	    {
        	        toast("Error saving file: " + ex.getLocalizedMessage());
        	    }
        	
        	break;
          
        case R.id.replaces1:
        	
        	try
      	  	{
        		FileWriter writer = new FileWriter(file,false);
        		writer.write(txt+"\n"); 
        		writer.flush();
      		 	writer.close();
      		
      		 	et.setText("");
      		 	toast("File replaced!");
      	    }
      	 
      	    catch (Exception ex)
      	    {
      	        toast("Error saving file: " + ex.getLocalizedMessage());
      	    }
        	
        	break;
      }
        
	}

}
