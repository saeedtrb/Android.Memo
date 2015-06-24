package ir.saeedtorabi.memo;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.widget.Toast.*;
import static android.widget.Toast.makeText;


public class AddMemo extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_memo);
        getSupportActionBar().hide();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_memo, menu);
        return true;
    }
    public void AddNewMemo( View v ){
        AddNewMemo();
    }
    public void AddNewMemo(){
        String title = GetTitleFieldValue();
        String content = GetContentFieldValue();
        if( title != "" && content != ""  ){
            DataBase myDb = new DataBase( this );
            try{
                myDb.Insert( myDb , title , content );
                makeText(this, R.string.msg_success_add_memo, LENGTH_LONG).show();
                ResetFormAddMemo();
            }catch (Exception e ){
                makeText(this, e.getMessage(), LENGTH_LONG).show();
            }
        }else{
            makeText(this, R.string.msg_data_not_valid, LENGTH_LONG).show();
        }


    }
    public void ResetFormAddMemo( View v ){
        ResetFormAddMemo();
    }
    public void ResetFormAddMemo(){
        this.SetContentFieldValue("");
        this.SetTitleFieldValue("");

    }
    public void SetTitleFieldValue( String val ){
        int id = R.id.titleMemo;
        View fieldV = findViewById( id );
        EditText field = ( EditText ) fieldV;
        field.setText( val );
    }
    public String GetTitleFieldValue(){
        int id = R.id.titleMemo;
        View fieldV = findViewById( id );
        EditText field = ( EditText ) fieldV;
        return field.getText().toString();
    }
    public void SetContentFieldValue( String val ){
        int id = R.id.contentMemo;
        View fieldV = findViewById( id );
        EditText field = ( EditText ) fieldV;
        field.setText( val );
    }
    public String GetContentFieldValue( ){
        int id = R.id.contentMemo;
        View fieldV = findViewById( id );
        EditText field = ( EditText ) fieldV;
        return field.getText().toString();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
