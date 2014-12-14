package capitalwords.example.com.capitolwords;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Toast;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

// Launcher activity
public class MainActivity extends ActionBarActivity  implements OnItemClickListener  {

    private static final String CWORLD_URL =
            "http://capitolwords.org/api/1/phrases.json?entity_type=month&entity_value=201412&n=1&apikey=4421142fd7464215a547bc60231387ab";
    ProgressBar pb;
    private ListView listView;
    List<CapitolWorldListItem> mItemList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pb = (ProgressBar) findViewById(R.id.progressBar1);
        pb.setVisibility(View.INVISIBLE);
        listView = (ListView) findViewById(R.id.listView);
        requestData(CWORLD_URL);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(MainActivity.this,ListItemDetailsActivity.class);
        intent.putExtra("POSITION", position);
        startActivity(intent);
        Toast toast = Toast.makeText(MainActivity.this, position + " is selected", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER |Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
    }

    private void requestData(String uri) {
        WorkerTask task = new WorkerTask();
        task.execute(uri);
    }

    protected void updateDisplay() {
        //Use CustomBaseAdapter to display data
        CustomBaseAdapter adapter = new CustomBaseAdapter(this,mItemList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    private class WorkerTask extends AsyncTask<String, String, List<CapitolWorldListItem>> {

        @Override
        protected void onPreExecute() {
                pb.setVisibility(View.VISIBLE);
        }

        @Override
        protected List<CapitolWorldListItem> doInBackground(String... params) {

            String content = HttpManager.GET("http://capitolwords.org/api/1/phrases.json?entity_type=month&entity_value=201412&n=1&apikey=4421142fd7464215a547bc60231387ab");
            Log.d("Pavan" , content);
            mItemList = CWorldJsonParser.parseFeed(content);

            return mItemList;
        }

        @Override
        protected void onPostExecute(List<CapitolWorldListItem> result) {

                pb.setVisibility(View.INVISIBLE);

            if (result == null) {
                Toast.makeText(MainActivity.this, "Web service not available", Toast.LENGTH_LONG).show();
                return;
            }
            updateDisplay();

        }

    }
}
