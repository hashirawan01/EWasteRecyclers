package com.example.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Models.RateListModel;
import com.example.ewasterecyclers.R;

import java.util.List;

public class RateListAdapters extends RecyclerView.Adapter<RateListAdapters.ViewHolder> {
    private List<RateListModel> classList;
    private Context context;

    public RateListAdapters(List<RateListModel> classList, Context context) {
        this.classList = classList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ratelist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int resource = classList.get(position).getImagebox();
        String title = classList.get(position).getTitle();
        String body = classList.get(position).getBody();
        holder.setdata(resource, title, body);

        //for simply applying click listner On recyclerView items
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context, "recyclerView clicked", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return classList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView title;
        private TextView body;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageview1);
            title = itemView.findViewById(R.id.ttle);
            body = itemView.findViewById(R.id.body);
        }
        private void setdata(int resource, String titletext, String textbody) {
            imageView.setImageResource(resource);
            title.setText(titletext);
            body.setText(textbody);
        }

    }

}
