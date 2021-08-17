package sg.edu.rp.c346.id20015553.application;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class FirstActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<Cart> cartList;
    ArrayAdapter adapter;
    AlertDialog.Builder alert;
    Button btnClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        final DBHelper dbh = new DBHelper(this);
        listView = findViewById(R.id.listView);
//        btnClear = findViewById(R.id.btnClear);
        cartList = new ArrayList<Cart>();
        cartList = dbh.getAllCart();
        dbh.close();

        idCycle(cartList);
        adapter = new CustomAdapter(this, R.layout.row, cartList);
        listView.setAdapter(adapter);

//        btnClear.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DBHelper dbh = new DBHelper(FirstActivity.this);
//                dbh.clear();
//                cartList.clear();
//                adapter.notifyDataSetChanged();
//            }
//        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final AdapterView<?> adapterView, View view, int i, long l) {
                final Cart currentCart = cartList.get(i);
                AlertDialog.Builder check = new AlertDialog.Builder(FirstActivity.this);
                check.setTitle("Check this item?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                DBHelper dbh = new DBHelper(FirstActivity.this);
//                                dbh.changeCheckTrue(currentCart);
                                currentCart.setPurchased(true);
                                adapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                DBHelper dbh = new DBHelper(FirstActivity.this);
//                                dbh.changeCheck(currentCart);
                                currentCart.setPurchased(false);
                                adapter.notifyDataSetChanged();
                            }
                        })
                        .show();

                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here.
        int id = item.getItemId();

        if (id == R.id.Add) {

            LayoutInflater factory = LayoutInflater.from(FirstActivity.this);
            View textEntryView = factory.inflate(R.layout.add, null);

            final EditText editName = textEntryView.findViewById(R.id.editName);
            final EditText editDescription = textEntryView.findViewById(R.id.editDescription);
            final EditText editPrice = textEntryView.findViewById(R.id.editPrice);

            alert = new AlertDialog.Builder(FirstActivity.this);
            alert.setView(textEntryView)
                    .setPositiveButton("ADD", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (editName.getText().toString().isEmpty() || editDescription.getText().toString().isEmpty() || editPrice.getText().toString().isEmpty()) {
                                Toast.makeText(FirstActivity.this, "Empty Inputs", Toast.LENGTH_SHORT).show();
                            } else {

                                DBHelper dbh = new DBHelper(FirstActivity.this);
                                dbh.insertCart(editName.getText().toString(), editDescription.getText().toString(), Float.parseFloat(editPrice.getText().toString()));
                                Cart newCart = new Cart(editName.getText().toString(), editDescription.getText().toString(), Float.parseFloat(editPrice.getText().toString()), false);
                                cartList.add(newCart);
                                idCycle(cartList);
                                adapter.notifyDataSetChanged();
                            }
                        }
                    })
                    .setNegativeButton("CANCEL", null)
                    .show();
            adapter.notifyDataSetChanged();
            return true;
        }
        else if(id == R.id.Clear) {
            DBHelper dbh = new DBHelper(this);
            dbh.clear();
            cartList.clear();
            adapter.notifyDataSetChanged();
            return true;
        }
        adapter.notifyDataSetChanged();
        return super.onOptionsItemSelected(item);
    }

    public void idCycle(ArrayList<Cart> cartList){
        for(int i = 0; i < cartList.size(); i++){
            if(!cartList.get(i).getName().isEmpty()){
                cartList.get(i).setId(i);
            }
        }
    }
}
