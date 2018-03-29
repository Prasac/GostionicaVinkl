package tomislav.piskur.com.vinkl.modelAdapter;

/**
 * Created by srs on 17.02.2018.
 */

public class Menu {

    private String head, desc, dan;

    public Menu(String dan, String desc) {
        this.dan = dan;
        this.desc = desc;
    }

    public Menu(String head, String desc, String dan) {
        this.head = head;
        this.desc = desc;
        this.dan = dan;
    }


    String getHead() {
        return head;
    }

    String getDesc() {
        return desc;
    }

    String getDan() {
        return dan;
    }


}
