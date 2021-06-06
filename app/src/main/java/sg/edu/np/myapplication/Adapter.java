package sg.edu.np.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<ViewHolder>{
    ArrayList<User> data;
    ListActivity activity;
    Context context;


    public Adapter (Context c, ArrayList<User> input){
        context = c;
        data = input;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item;
        User u = data.get(viewType);

        if (viewType == 0){
            item = LayoutInflater.from(parent.getContext()).inflate(R.layout.customlayout,parent,false);
        }
        else{
            item = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_view_item,parent,false);
        }

        return new ViewHolder(item);
    }

    public void onBindViewHolder(ViewHolder holder, int position){
        User u = data.get(position);
        holder.txt1.setText(u.getName());
        holder.txt2.setText(u.getDesc());
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
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
                        Intent intent = new Intent(context, MainActivity.class);
                        intent.putExtras(bundle);
                        context.startActivity(intent);
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
            });
    }

    public int getItemCount(){
        return data.size();
    }

    @Override
    public int getItemViewType(int position){
        if(data.get(position).getName().endsWith("7") == true)
        {
            return 0;
        }
        return 1;
    }
}
