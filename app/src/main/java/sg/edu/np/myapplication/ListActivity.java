package sg.edu.np.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Random;

public class ListActivity extends AppCompatActivity {
    //ArrayList<String> myList = new ArrayList<>();
    public static ArrayList<User> userList = new ArrayList<>();
    static Context context;

    UserDbHandler dbHandler = new UserDbHandler(this, null,null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        context = this;

        setUsers();

        userList = dbHandler.getUsers();
        RecyclerView recyclerView = findViewById(R.id.recycleview);
        Adapter myAdaptor = new Adapter(this, userList);
        LinearLayoutManager LayoutManger = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(LayoutManger);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(myAdaptor);

    }

    public void profileView(User u){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Profile");
        builder.setMessage(u.getName()).setCancelable(false);
        builder.setPositiveButton("View", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Bundle bundle = new Bundle();
                bundle.putString("Name", u.getName());
                bundle.putString("desc", u.getDesc());
                bundle.putInt("id",u.getId());
                bundle.putBoolean("followed", u.getFollowed());
                Intent intent = new Intent(ListActivity.this, MainActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }


    private int randomInt(){
        Random ran = new Random();
        int value = ran.nextInt(10000);
        return value;
    }

    private void setUsers(){
        for (int i = 0; i< 20; i++){
            String username = "Name"+ String.valueOf(randomInt());
            String desc = "Description" + String.valueOf(randomInt());
            boolean followed = new Random().nextBoolean();
            int id = randomInt();
            dbHandler.addUser(new User(username, desc, id, followed));
        }
    }


}