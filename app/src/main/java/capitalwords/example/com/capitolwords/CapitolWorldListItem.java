package capitalwords.example.com.capitolwords;

/**
 * Created by pavan on 12/14/14.
 *
 * Container object
 */
public class CapitolWorldListItem {

    public String tfidf;
    public String count;
    public String ngram;

    //Constructor
    public CapitolWorldListItem(String tfidf,String count, String ngrm){
        this.tfidf = tfidf;
        this.count = count;
        this.ngram = ngrm;
    }

    public CapitolWorldListItem(){
        this.tfidf = " ";
        this.count = " ";
        this.ngram = " ";
    }

    public String getTfidf() {
        return tfidf;
    }

    public String getCount(){
        return count;
    }


    public String getNgram(){
        return ngram;
    }

    public void setTfidf(String tfidf){
        this.tfidf = tfidf;
    }

    public void setCount(int count){
        this.count = count+"";
    }

    public void setNgram(String ngram){
        this.ngram = ngram;
    }
}
