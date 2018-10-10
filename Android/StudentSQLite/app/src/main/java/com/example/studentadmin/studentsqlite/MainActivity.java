package com.example.studentadmin.studentsqlite;

import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper dbh;
    EditText Sid, Firstname, Lastname, Smarks;
    Button Binsert, Bupdate, Bdelete, Bview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbh = new DatabaseHelper(this);

        Sid = findViewById(R.id.id);
        Firstname = findViewById(R.id.FName);
        Lastname = findViewById(R.id.LName);
        Smarks = findViewById(R.id.marks);

        Binsert = findViewById(R.id.InsertData);
        Bupdate = findViewById(R.id.UpdateData);
        Bdelete = findViewById(R.id.DeleteData);
        Bview = findViewById(R.id.ViewData);

        Binsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isInserted = dbh.insertData(Integer.parseInt(Sid.getText().toString()), Firstname.getText().toString(), Lastname.getText().toString(), Integer.parseInt(Smarks.getText().toString()));

                if (isInserted == true){
                    Toast.makeText(getApplicationContext(),"Data Inserted Successfuly",Toast.LENGTH_SHORT).show();
                }
            }
        });

        Bupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isUpdated = dbh.updateData(Integer.parseInt(Sid.getText().toString()),Firstname.getText().toString(),Lastname.getText().toString(),Integer.parseInt(Smarks.getText().toString()));
                if(isUpdated){
                    Toast.makeText(getApplicationContext(),"Data Updated",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Data Not Updated",Toast.LENGTH_SHORT).show();
                }
            }
        });

        Bdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer deleteRows = dbh.deleteData(Integer.parseInt(Sid.getText().toString()));
                if(deleteRows > 0){
                    Toast.makeText(getApplicationContext(),"Data Deleted",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Data Not Deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Bview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = dbh.getData();
                if(res.getCount() == 0){

                }
                else {
                    StringBuffer sbf = new StringBuffer();
                    while (res.moveToNext()){
                        sbf.append("\nID: "+res.getInt(0)+
                                    "\nFirst Name: "+res.getString(1)+
                                    "\nLast Name: "+res.getString(2)+
                                    "\nMarks: "+res.getInt(3));
                    }
                    showMessage("Data",sbf.toString());
                }
            }
        });

    }
    public void showMessage(String title, String msg){
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setCancelable(true);
        adb.setTitle(title);
        adb.setMessage(msg);
        adb.show();
    }
}
