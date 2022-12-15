package com.example.android.expencecalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView ex_recycleview;
    ArrayList<ex_data> transactionList;
    EditText ex_amount , ex_message;
    TextView ex_sign;
    ImageView ex_send;
    ex_Adapter adapter;
    boolean positive = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        ex_recycleview.setLayoutManager(new LinearLayoutManager(this));
        ex_recycleview.setAdapter(new ex_Adapter(this , transactionList));

        ex_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeSign();
            }
        });

        ex_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ex_amount.getText().toString().trim().isEmpty()){
                    ex_amount.setError("Enter Amount");
                    return;
                }
                if (ex_message.getText().toString().trim().isEmpty()){
                    ex_message.setError("Enter Message");
                    return;
                }

                try {
                    int amt = Integer.parseInt(ex_amount.getText().toString().trim());

                    // Adding Transaction to recycler View
                    sendTransaction(ex_message.getText().toString().trim(),amt,positive);

                    ex_amount.setText("");
                    ex_message.setText("");
                }
                catch (Exception e){
                    ex_amount.setError("Amount should be integer greater than zero!");
                }
            }
        });
    }

    private void changeSign(){
        if (positive){
            ex_sign.setText("-₹");
            ex_sign.setTextColor(Color.parseColor("#F30909"));
            positive = false;
        }
        else{
            ex_sign.setText("+₹");
            ex_sign.setTextColor(Color.parseColor("#29D631"));
            positive = true;
        }
    }

    private void sendTransaction(String message , int amount  , boolean positive){
        transactionList.add(new ex_data(message , amount , positive));
        adapter.notifyDataSetChanged();
    }

    public void initView(){
        ex_amount = findViewById(R.id.ex_amount);
        ex_message = findViewById(R.id.ex_message);
        ex_sign = findViewById(R.id.ex_sign);
        ex_send = findViewById(R.id.ex_send);
        ex_recycleview = findViewById(R.id.ex_recycleview);
    }


}