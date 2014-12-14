package capitalwords.example.com.capitolwords;

/**
 * Created by pavan on 12/14/14.
 */

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CWorldJsonParser {

    private static final String TAG = "CWorldJsonParser";
    public static List<CapitolWorldListItem> parseFeed(String content) {

        try {
            JSONArray ar = new JSONArray(content);
            List<CapitolWorldListItem> mCountList = new ArrayList<>();

            for (int i = 0; i < ar.length(); i++) {

                JSONObject obj = ar.getJSONObject(i);
                CapitolWorldListItem item = new CapitolWorldListItem();
                String tfdf = obj.getString("tfidf");
                item.setTfidf(tfdf);
                int count = obj.getInt("count");
                item.setCount(count);
                String ngrm = obj.getString("ngram");
                Log.d(TAG," ngrm Itemt : " + count);
                item.setNgram(ngrm);
                mCountList.add(item);
            }

            return mCountList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }
}