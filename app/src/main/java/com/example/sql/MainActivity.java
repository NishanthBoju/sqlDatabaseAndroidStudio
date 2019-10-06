package com.example.sql;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity<showMessage> extends AppCompatActivity {
Button btnadd,btnupdate,btndelete,btnviewall;
EditText e1,e2,e3,e4,e5;
DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnadd=(Button)findViewById(R.id.button);
        btnupdate=(Button)findViewById(R.id.button3);
        btndelete=(Button)findViewById(R.id.button2);
        btnviewall=(Button)findViewById(R.id.button4);

        e1=(EditText)findViewById(R.id.editText);
        e2=(EditText)findViewById(R.id.editText2);
        e3=(EditText)findViewById(R.id.editText3);
        e4=(EditText)findViewById(R.id.editText4);
        e5=(EditText)findViewById(R.id.editText5);
        db=new DatabaseHelper(this);
        add_product();
        update_product();
        delete_product();
        view_product();


    }

    private void update_product() {
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean is_updated= db.updateProduct(e1.getText().toString(),e2.getText().toString(),e3.getText().toString(),e4.getText().toString(),e5.getText().toString());
                if(is_updated){
                    Toast.makeText(getApplicationContext(), "Successfully Updated", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Not Updated", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void add_product() {
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean is_inserted= db.additems(e2.getText().toString(),e3.getText().toString(),e4.getText().toString(),e5.getText().toString());
                if(is_inserted){
                    Toast.makeText(getApplicationContext(), "Successfully Inserted", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Not Inserted", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void delete_product() {

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer is_deleted=db.deleteProduct(e1.getText().toString());
                if(is_deleted>0){
                    Toast.makeText(getApplicationContext(), "Successfully Deleted", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Not Deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void view_product() {
        btnviewall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res=db.viewAllproduct();
                if(res.getCount()==0) {
                    showMessage("error", "No products found");
                    return;
                    //// Toast.makeText(getApplicationContext(), "Successfully Viewed", Toast.LENGTH_SHORT).show();
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("id,"+res.getString(0)+"\n");
                    buffer.append("name,"+res.getString(1)+"\n");
                    buffer.append("brand,"+res.getString(2)+"\n");
                    buffer.append("cost,"+res.getString(3)+"\n");
                    buffer.append("qty,"+res.getString(4)+"\n");


                }

                showMessage("data",buffer.toString());
            }
        });
    }

    private void showMessage(String error,String s){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(error);
        builder.setMessage(s);
        builder.show();

    }
}
