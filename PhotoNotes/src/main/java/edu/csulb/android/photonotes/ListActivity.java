package edu.csulb.android.photonotes;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;

public class ListActivity extends AppCompatActivity {

    public static initializeDB dbinit;
    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        list = (ListView)findViewById(R.id.listview);
        dbinit = new initializeDB(this);
        list.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,new helperDB(dbinit).readData()));
        list.setOnItemClickListener( new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                TextView txt = (TextView)view;
                String caption=txt.getText().toString();
                Intent viewIntent = new Intent(ListActivity.this,ViewPhotoActivity.class);
                viewIntent.putExtra("caption",caption);
                String transition = getString(R.string.transition);

                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(ListActivity.this,view,transition);
                ActivityCompat.startActivity(ListActivity.this,viewIntent,options.toBundle());

            }
        });
        registerForContextMenu(list);





        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(ListActivity.this,AddPhotoActivity.class);
                startActivity(myintent);
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        list.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,new helperDB(dbinit).readData()));
        list.setOnItemClickListener( new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                TextView txt = (TextView)view;
                String caption=txt.getText().toString();
                Intent viewIntent = new Intent(ListActivity.this,ViewPhotoActivity.class);
                viewIntent.putExtra("caption",caption);
                String transition = getString(R.string.transition);

                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(ListActivity.this,view,transition);
                ActivityCompat.startActivity(ListActivity.this,viewIntent,options.toBundle());

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_uninstall) {
            Intent intent=new Intent(Intent.ACTION_DELETE);
            intent.setData(Uri.parse("package:edu.csulb.android.photonotes"));
            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.action_delete:
                String caption=(String)list.getItemAtPosition(info.position);
                deleteItem(caption);
                // TODO: Implement Delete
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    public void deleteItem(String caption)
    {
        helperDB dbhelp = new helperDB(this.dbinit);
        String path=dbhelp.getImagePath(caption);
        File file = new File(path);
        dbhelp.deleteData(caption);
        this.recreate();
    }



}
