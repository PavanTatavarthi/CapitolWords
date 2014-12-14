package capitalwords.example.com.capitolwords;

/**
 * Created by pavan on 12/14/14.
 */
import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CustomBaseAdapter extends BaseAdapter {

    Context context;
    List<CapitolWorldListItem> rowItems;

    public CustomBaseAdapter(Context context, List<CapitolWorldListItem> rowItems) {
        this.context = context;
        this.rowItems = rowItems;
    }

    private class ViewHolder {
        TextView tfidf;
        TextView count;
        TextView ngram;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder = null;
        LayoutInflater mInflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if(view == null)
        {
            view = mInflater.inflate(R.layout.list_item, null);
            holder  = new ViewHolder();
            holder.tfidf = (TextView) view.findViewById(R.id.textView);
            holder.count = (TextView) view.findViewById(R.id.textView2);
            holder.ngram = (TextView) view.findViewById(R.id.textView3);


            view.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) view.getTag();
        }

        CapitolWorldListItem rowItem = (CapitolWorldListItem) getItem(position);
        holder.tfidf.setText(rowItem.getTfidf());
        holder.count.setText(rowItem.getCount());
        holder.ngram.setText(rowItem.getNgram());

        return view;
    }

    @Override
    public int getCount() {
        return rowItems.size();
    }

    @Override
    public Object getItem(int position) {
        return rowItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return rowItems.indexOf(getItem(position));
    }


}
