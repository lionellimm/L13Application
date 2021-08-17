package sg.edu.rp.c346.id20015553.application;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {
    Context parent_context;
    int layout_id;
    ArrayList<Cart> cartList;

    public CustomAdapter(Context context, int resource,
                         ArrayList<Cart> objects){
        super(context, resource, objects);

        parent_context = context;
        layout_id = resource;
        cartList = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtain the LayoutInflater object
        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // "Inflate" the View for each row
        View rowView = inflater.inflate(layout_id, parent, false);

        // Obtain the UI components and do the necessary binding
       TextView tvName = rowView.findViewById(R.id.textViewName);
       TextView tvDescription = rowView.findViewById(R.id.textViewDescription);
       TextView tvPrice = rowView.findViewById(R.id.textViewPrice);
       ImageView ivThumb = rowView.findViewById(R.id.imageView);


        // Obtain the Android Version information based on the position
        Cart currentCart = cartList.get(position);

        if(currentCart.isPurchased()==true){
            ivThumb.setImageResource(R.drawable.thumbsup1);
        }
        else{
            ivThumb.setImageResource(0);
        }

        // Set values to the TextView to display the corresponding information
        tvName.setText(currentCart.getName());
        tvDescription.setText(currentCart.getDescription());
        tvPrice.setText(String.valueOf(currentCart.getCost()));

        return rowView;
    }

}
