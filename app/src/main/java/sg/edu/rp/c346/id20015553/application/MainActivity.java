package sg.edu.rp.c346.id20015553.application;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnAdd, btnShow;
    EditText etName, etDescription, etPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final DBHelper dbh = new DBHelper(this);

        etName = findViewById(R.id.etName);
        etDescription = findViewById(R.id.etDescription);
        etPrice = findViewById(R.id.etPrice);

        btnAdd = findViewById(R.id.btnInsert);
        btnShow = findViewById(R.id.btnShowList);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String description = etDescription.getText().toString().trim();
                float price = Float.parseFloat(etPrice.getText().toString().trim());
                if (name.isEmpty() || description.isEmpty() || String.valueOf(price).length()==0){
                    Toast.makeText(MainActivity.this, "Incomplete data", Toast.LENGTH_SHORT).show();
                }
                else{
                    DBHelper dbh = new DBHelper(MainActivity.this);
                    long result = dbh.insertCart(name, description, price);
                    if (result != -1) {
                        Toast.makeText(MainActivity.this, "Song inserted", Toast.LENGTH_LONG).show();
                        etName.setText("");
                        etDescription.setText("");
                        etPrice.setText("");
                    } else {
                        Toast.makeText(MainActivity.this, "Insert failed", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent i = new Intent(MainActivity.this, FirstActivity.class);
                startActivity(i);
            }
        });


    }
}