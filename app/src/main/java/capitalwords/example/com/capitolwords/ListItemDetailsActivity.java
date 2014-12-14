package capitalwords.example.com.capitolwords;

/**
 * Created by pavan on 12/14/14.
 */
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import java.lang.Math;

public class ListItemDetailsActivity extends Activity {

    private static final String[] titles = new String [] { "Banana" , "Apple",
            "Orange", "Four Season","Banana" , "Apple", "Orange", "Four Season" };

    private static final String[] descriptions = new String [] {
            "yum yum, best fruit in the summer.",
            "golden fruit with the symbol of wisdom.",
            "ok, your are staring like the sun, my dear yum oran~bo.",
            "I don't know who you are, but you are amazing!",
            "yum yum, best fruit in the summer.",
            "golden fruit with the symbol of wisdom.",
            "ok, your are staring like the sun, my dear yum oran~bo.",
            "I don't know who you are, but you are amazing!"
    };

    private static final String fullDescriptions = "Nunc ornare, magna sed fringilla suscipit, ante sapien placerat enim, sed lacinia eros neque at diam. Vivamus molestie, ante at rhoncus ornare, risus dolor tristique massa, quis rutrum sapien urna ac mi. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Curabitur rhoncus orci tortor, vitae venenatis elit condimentum sed. Integer fringilla eu diam vel ultrices. Ut lacus mauris, viverra tristique enim et, lobortis interdum purus. Integer rutrum massa vel velit sollicitudin, ullamcorper placerat arcu egestas. Aliquam eu sapien eros. Nunc blandit est nec aliquam consectetur. Nam ultrices, tortor et porttitor tristique, eros sem tempus neque, dapibus fermentum nunc lectus a erat. Cras vitae pretium nulla, eget tincidunt magna. Quisque in arcu nisi. Suspendisse vehicula nisl ac posuere sollicitudin. Nunc vitae fermentum nulla, venenatis ornare eros. Maecenas lobortis congue eros eu tristique. Nunc sodales orci vitae tempor ornare. Nunc ultrices justo sed turpis congue, ac scelerisque dui tincidunt. Maecenas volutpat aliquet ante ac venenatis. Donec ac aliquet diam. Vestibulum id nulla feugiat, volutpat mauris non, porttitor sapien.";

    private ImageView icon;
    private TextView title;
    private TextView desc;
    private TextView fullDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item_details);
        title = (TextView)findViewById(R.id.textView);
        desc = (TextView)findViewById(R.id.textView2);
        fullDesc = (TextView)findViewById(R.id.textView3);
        //retrieve data passed from main activity
        Bundle extras = getIntent().getExtras();
        displayItems(extras);
    }

    private void displayItems(Bundle extras)
    {
        if(extras != null)
        {
            int position = extras.getInt("POSITION");
            if(position<titles.length) {
                title.setText(titles[position]);
                desc.setText(descriptions[position]);
            }

            fullDesc.setText(fullDescriptions);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}
