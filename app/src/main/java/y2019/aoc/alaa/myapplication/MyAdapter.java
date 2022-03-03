package y2019.aoc.alaa.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Context context;
    private List<String> titles;
    private List<Integer> images;
    private String type;

    public MyAdapter(Context context, List<String> titles, List<Integer> images, String type){
        this.context=context;
        this.titles=titles;
        this.images=images;
        this.type = type;


    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.view_item,parent,false);
        return new MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.mTextview.setText(titles.get(position));
        holder.mImageView.setImageResource(images.get(position));
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView mImageView;
        TextView mTextview;
        private String type = getType();

        public MyViewHolder(@NonNull View itemView){
            super(itemView);

            mImageView = itemView.findViewById(R.id.randopicture);
            mTextview = itemView.findViewById(R.id.cvname1);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
//                    Toast.makeText(itemView.getContext(), "CLICKED:"+mTextview.getText(), Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(itemView.getContext(), InformationActivity.class);
                    intent.putExtra("category",mTextview.getText());
                    intent.putExtra("type", type);
                    itemView.getContext().startActivity(intent);
                }
            });


        }
    }
}
