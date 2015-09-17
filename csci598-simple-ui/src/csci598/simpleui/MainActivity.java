package csci598.simpleui;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); 
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void pepsiClicked(View v)
    {
    	if(v.getId() == R.id.pepsi)
    	{
    		TextView newText = (TextView) findViewById(R.id.textView1);
    		newText.setText("Pepsi!");
    	}
    }
    
    public void cokeClicked(View v)
    {
    	if(v.getId() == R.id.coke)
    	{
    		TextView newText = (TextView) findViewById(R.id.textView1);
    		newText.setText("Coke!");
    	}
    }
    
}
