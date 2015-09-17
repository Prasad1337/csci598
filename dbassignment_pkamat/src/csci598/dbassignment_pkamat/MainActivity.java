package csci598.dbassignment_pkamat;

import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

    private class dbHelper extends SQLiteOpenHelper {

        public dbHelper(Context context) {
            super(context, TABLE_NAME, null, DATABASE_VERSION);

        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            System.out.println("create statement"+SQL_CREATE_ENTRIES);
            try
            {
                db.execSQL(SQL_CREATE_ENTRIES);

            }catch(SQLiteException sql)
            {
                sql.printStackTrace();
            }
        }

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS records");
			onCreate(db);
			
		}
		
	    public void addRecord(String t) {

	        ContentValues values = new ContentValues(1);
	        values.put(RECORDS_COLUMN_TEXTS,t);
	        
	        getWritableDatabase().insert(TABLE_NAME, RECORDS_COLUMN_TEXTS, values);
	    }

	    public Cursor getRecords() {
	    	
	        return getWritableDatabase().rawQuery("select texts from " + TABLE_NAME, null);

	    }   

    }

	private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "records";

    public static final String RECORDS_COLUMN_TEXTS = "texts";

    private dbHelper openHelper;

    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + TABLE_NAME + "(" + RECORDS_COLUMN_TEXTS + " TEXT PRIMARY KEY" + ")";
    
    private void toast(String text)
    {
        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
        toast.show();
    }
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		openHelper = new dbHelper(this);
		
		Button fet = (Button) findViewById(R.id.fetch1);   
        fet.setOnClickListener(this);
        
        Button ap = (Button) findViewById(R.id.append1);   
        ap.setOnClickListener(this);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		TextView tv=(TextView)findViewById(R.id.textView1);
		EditText et=(EditText)findViewById(R.id.editText1);
		
		Cursor cursor = openHelper.getRecords();
		
        switch(v.getId()) {
        
        case R.id.fetch1:
        	StringBuffer sb = new StringBuffer();
        	
        	while(cursor.moveToNext()) {
        	    sb.append(cursor.getString(0) + "\n");
        	}
        	tv.setText(sb.toString());
        	
        	break;
        	
        case R.id.append1:
        	String txt=et.getText().toString();
        	int flag=0;
        	
        	while(cursor.moveToNext())	{
        		
        		if(cursor.getString(0).equals(txt))
        		{
        			toast("Duplicate entry!");
        			flag=1;
        			break;
        		}
        		
        	}
        	
        	if(flag==0)
        	{
    			openHelper.addRecord(txt);
            	et.setText("");
            		
        	}
        	
        	break;
		
	}

}
}