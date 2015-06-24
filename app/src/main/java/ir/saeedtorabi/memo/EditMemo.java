package ir.saeedtorabi.memo;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class EditMemo extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        setContentView(R.layout.activity_edit_memo);
        DataBase DB = new DataBase( this );
        MemoModel memo = DB.GetMemo( DB , i.getStringExtra("positionID") );
        SetTitleFieldValue( memo.getTitle() );
        SetContentFieldValue( memo.getCountent() );
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_memo, menu);
        return true;
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
