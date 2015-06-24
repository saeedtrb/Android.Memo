package ir.saeedtorabi.memo;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class HomeActivity extends ActionBarActivity {
    ListView lstMemo;
    private adaptor myAdaptor;
    private ImageButton imbAddMemo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initPage();
        UpdateList();
        imbAddMemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowAddMemoForm();
            }
        });

    }

    private void initPage()
    {
        imbAddMemo =(ImageButton) findViewById(R.id.add_item);
        lstMemo    =(ListView)findViewById(R.id.lstMemo);

    }

    @Override
    protected void onResume() {
        super.onResume();
        UpdateList();
    }
    @Override
    public void onBackPressed() {
        UpdateList();
    }
    public void UpdateList(){
        DataBase DB = new DataBase( this );
        getSupportActionBar().hide();
        ArrayList<MemoModel> list = DB.ListMemo(DB);
        myAdaptor = new adaptor(HomeActivity.this, DB);

        myAdaptor.updateMyList( list );
        lstMemo.setAdapter(myAdaptor);
        lstMemo.setEmptyView( findViewById( R.id.emptyMemoList ) );
        lstMemo.setOnItemClickListener( new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent editActivity = new Intent(getApplicationContext(), EditMemo.class );
                editActivity.putExtra( "positionID", i + "");
                startActivity( editActivity );
            }
        });
    }
    public void UpdateList( View v ){
        UpdateList();
    }
    public void ClearList( View v ){

    }

    public void ShowAddMemoForm(){
        try{
            Intent intent = new Intent(this, AddMemo.class );
            startActivity(intent);
        }catch (Exception ex){
            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
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
