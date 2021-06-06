package sg.edu.np.myapplication;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder{
    TextView txt1;
    TextView txt2;
    ImageView img;

    public ViewHolder(View itemView){
        super(itemView);
        txt1 = itemView.findViewById(R.id.textView);
        txt2 = itemView.findViewById(R.id.textView2);
        img = itemView.findViewById(R.id.imageView3);
    }

}
