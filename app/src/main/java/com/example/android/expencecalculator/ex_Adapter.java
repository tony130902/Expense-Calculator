package com.example.android.expencecalculator;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ex_Adapter extends RecyclerView.Adapter<ex_Adapter.viewHolder> {

    Context ctx;
    ArrayList<ex_data> transactionList;

    public ex_Adapter(Context ctx, ArrayList<ex_data> transactionList) {
        this.ctx = ctx;
        this.transactionList = transactionList;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflater = LayoutInflater.from(ctx).inflate(R.layout.expence_items , parent , false);
        return new ex_Adapter.viewHolder(inflater);
    }

    @Override
    public void onBindViewHolder(viewHolder holder, int position) {

        holder.tv_message.setText(transactionList.get(holder.getAdapterPosition()).getMessage());

        if (transactionList.get(holder.getAdapterPosition()).isPositive()){
            holder.tv_amount.setText("+₹" + Integer.toString(transactionList.get(holder.getAdapterPosition()).getAmount()));
            holder.tv_amount.setTextColor(Color.parseColor("#29D631"));
        }
        else {
            holder.tv_amount.setText("-₹" + Integer.toString(transactionList.get(holder.getAdapterPosition()).getAmount()));
            holder.tv_amount.setTextColor(Color.parseColor("#F30909"));
        }

        holder.tv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog dialog = new AlertDialog.Builder(ctx)
                        .setCancelable(false)
                        .setTitle("Are you sure? The transaction will be deleted.")
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                transactionList.remove(holder.getAdapterPosition());
                                dialogInterface.dismiss();
                                notifyDataSetChanged();
                            }
                        })
                        .create();
                dialog.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return transactionList.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder{
        TextView tv_amount , tv_message;
        ImageView tv_delete;

        public viewHolder(View itemView) {
            super(itemView);
            tv_amount = itemView.findViewById(R.id.tv_amount);
            tv_message = itemView.findViewById(R.id.tv_message);
            tv_delete = itemView.findViewById(R.id.tv_delete);
        }
    }
}
