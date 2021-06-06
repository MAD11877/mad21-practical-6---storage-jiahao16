package sg.edu.np.myapplication;

import java.io.Serializable;

public class User implements Serializable {
    public String name;
    public String desc;
    public int id;
    public boolean followed;

    public User(){ }

    public User(String n, String d, int i, boolean f){
        name = n;
        desc = d;
        id = i;
        followed = f;
    }

    public String getName() {
        return name;
    }

    public void setName(String n) {
        this.name = n;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String d) {
        this.desc = d;
    }

    public int getId() {
        return id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public boolean getFollowed() {return followed; }

    public void  setFollowed(boolean f) {this.followed = f;}

}
