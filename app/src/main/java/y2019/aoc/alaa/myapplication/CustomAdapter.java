package y2019.aoc.alaa.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

//takes the data and TenPlate and put it on view.
public class CustomAdapter extends ArrayAdapter<Item> {
    private Context context;//view what i want to show on screen.
    private int resource;// id for xml how will the info will be ordered.



    public CustomAdapter(@NonNull Context context, int resource, @NonNull List<Item> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;//this is the item row resource, design for each row.
    }






    /*
    getView() method. This view is called when a listItem needs to be created and
    populated with the data.In this method first the View is inflated using the
    LayoutInflator.inflate() method. It is important that you check that if the view
    you are trying to inflate is new or reused
     */

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;

        if (view == null)//to make share it wont crash.
            view = LayoutInflater.from(context).inflate(resource, parent, false);

        Item item = getItem(position);//starts at first place 0. method from android studio not related to Item object.

        if (item != null) {
            ImageView imageView = view.findViewById(R.id.imageItem);
            TextView textViewDescription = view.findViewById(R.id.textviewDesc);
            Button itemButton = view.findViewById(R.id.itemButton);

            itemButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "this Item was added to shopping cart", Toast.LENGTH_LONG).show();
                }
            });
            imageView.setImageResource(item.getResid());
            textViewDescription.setText(item.getDescription());



        }


        return view;

    }
}
