package coursera.labs.modernartui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;


public class ModernArtUIMainActivity extends Activity {

	private TextView mTextView1;
	private TextView mTextView2;
	private TextView mTextView3;
	private TextView mTextView5;
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modern_art_ui_main);
        
        mTextView1 = (TextView)findViewById(R.id.box1);
        mTextView2 = (TextView)findViewById(R.id.box2);
        mTextView3 = (TextView)findViewById(R.id.box3);
        mTextView5 = (TextView)findViewById(R.id.box5);
        
        SeekBar seekBar = (SeekBar)findViewById(R.id.seekbar);
        seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				setBoxesColor(progress);
				}
		});
        
        setBoxesColor(seekBar.getProgress());
     }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.modern_art_uimain, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_moreInformation) {
        	showDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    private int getRGB(int red, int green, int blue, int alpha)
    {
    	int value = (alpha & 0x0ff);
    	value = (value << 8) + (red & 0x0ff);
    	value = (value << 8) + (green & 0x0ff);
    	value = (value << 8) + (blue & 0x0ff);
    	
    	return value;
    }
    private void setBoxesColor(int position)
    {
    	if (mTextView1 != null)
    		mTextView1.setBackgroundColor(getRGB(0x0ff, position, 0, 0x0ff));
    	
    	if (mTextView5 != null)
    		mTextView5.setBackgroundColor(getRGB(0x0aa, position, position, 0x0ff));
   	
    	if (mTextView2 != null)
    		mTextView2.setBackgroundColor(getRGB(position, position, 0x0ff, 0x0ff));
    	
    	if (mTextView3 != null)
    		mTextView3.setBackgroundColor(getRGB(position, position, 0x0aa, 0x0ff));
    }
    
    @SuppressWarnings("deprecation")
	private void showDialog()
    {
    	Resources res = getResources();
    			
    	AlertDialog dialog = new AlertDialog.Builder(ModernArtUIMainActivity.this).create();
    	TextView myMsg = new TextView(this);
    	myMsg.setGravity(Gravity.CENTER_HORIZONTAL);
    	myMsg.setText(res.getString(R.string.message) + "\n\n" + res.getString(R.string.message2));
    
    	dialog.setTitle("");
    	dialog.setView(myMsg);
    	
    	dialog.setButton2(res.getString(R.string.moma),  new DialogInterface.OnClickListener() {
    		 
            public void onClick(DialogInterface dialog, int which) {
                
            	try
            	{
	            	Intent intent = new Intent(Intent.ACTION_VIEW);
	            	intent.setData(Uri.parse("http://www.moma.org"));
	            	startActivity(intent);
            	}
            	catch(Exception e)
            	{
            		dialog.cancel();
              	}
            }
        });

    	dialog.setButton(res.getString(R.string.cancel), new DialogInterface.OnClickListener() {
   		 
            public void onClick(DialogInterface dialog, int which) {
                
                dialog.cancel();
            }
        });
    
    	dialog.show();
    }
}
