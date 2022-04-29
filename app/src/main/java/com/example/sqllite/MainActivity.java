package com.example.sqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username, pass ,id;
    Button view, delete, update, save ;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        id = findViewById(R.id.input_id_txt) ;
        username = findViewById(R.id.input_username_txt) ;
        pass = findViewById(R.id.input_pass_txt) ;


        view = findViewById(R.id.view);
        delete = findViewById(R.id.dlt);
        update = findViewById(R.id.updt);
        save = findViewById(R.id.save);

        DB = new DBHelper(this );

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idTXT = id.getText().toString();
                String nameTXT = username.getText().toString() ;
                String passTXT = pass.getText().toString() ;

                Boolean checkinsertdata = DB.insertuserdata(idTXT, nameTXT, passTXT) ;
                if(checkinsertdata == true ){
                    Toast.makeText(MainActivity.this, " New Entry Inserted ",Toast.LENGTH_SHORT ).show();
                } else
                    Toast.makeText(MainActivity.this, " New Entry Not Inserted", Toast.LENGTH_SHORT).show();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idTXT = id.getText().toString();
                String nameTXT = username.getText().toString() ;
                String passTXT = pass.getText().toString() ;

                Boolean checkupdatedata = DB.updateuserdata(idTXT ,nameTXT, passTXT) ;
                if(checkupdatedata == true ){
                    Toast.makeText(MainActivity.this, " Entry Updated ",Toast.LENGTH_SHORT ).show();
                } else
                    Toast.makeText(MainActivity.this, " Entry Not Updated", Toast.LENGTH_SHORT).show();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idTXT = id.getText().toString();
//                String nameTXT = username.getText().toString() ;

                Boolean checkdeletedata = DB.deletedata(idTXT) ;
                if(checkdeletedata == true ){
                    Toast.makeText(MainActivity.this, " Entry Deleted ",Toast.LENGTH_SHORT ).show();
                } else
                    Toast.makeText(MainActivity.this, " Entry Not Deleted", Toast.LENGTH_SHORT).show();
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                if(res.getCount()==0){
                    Toast.makeText(MainActivity.this, " No Entry Exsist ", Toast.LENGTH_SHORT).show();
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Name : "+res.getString(0)+"\n");
                    buffer.append("Name : "+res.getString(1)+"\n");
                    buffer.append("Name : "+res.getString(2)+"\n\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this) ;
                builder.setCancelable(true);
                builder.setTitle("User Entries ");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
    }

}