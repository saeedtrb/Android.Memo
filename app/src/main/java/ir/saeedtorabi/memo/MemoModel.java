package ir.saeedtorabi.memo;

/**
 * Created by saeed_trb on 6/20/2015.
 */
public class MemoModel {
    private String countent;
    private String title;

    public MemoModel(String title, String countent) {
        this.countent = countent;
        this.title = title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setCountent(String countent) {
        this.countent = countent;
    }
    public String getTitle() {
        return title;
    }
    public String getCountent() {
        return countent;
    }
}
