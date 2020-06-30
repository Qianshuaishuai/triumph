package com.babyraising.triumph.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.babyraising.triumph.Constant;
import com.babyraising.triumph.R;
import com.babyraising.triumph.bean.Motor;
import com.babyraising.triumph.ui.main.MotorSetupActivity;

import java.util.List;

public class MotorAdapter extends RecyclerView.Adapter<MotorAdapter.ViewHolder> {

    private List<Motor> mList;
    private MotorSetupActivity context;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView numberTxt, statusTxt, nameTxt, modeTxt;
        ImageView pattery;

        public ViewHolder(View view) {
            super(view);
            numberTxt = (TextView) view.findViewById(R.id.number);
            statusTxt = (TextView) view.findViewById(R.id.status);
            nameTxt = (TextView) view.findViewById(R.id.name);
            modeTxt = (TextView) view.findViewById(R.id.mode);
            pattery = (ImageView) view.findViewById(R.id.status_pattery);
        }

    }

    public MotorAdapter(MotorSetupActivity context, List<Motor> mList) {
        this.context = context;
        this.mList = mList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_motor, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(position);
                }
            }
        });

        switch (mList.get(position).getStatus()) {
            case 1:
                holder.numberTxt.setTextColor(Color.GREEN);
                holder.statusTxt.setTextColor(Color.GREEN);
                holder.modeTxt.setTextColor(Color.GREEN);
                holder.pattery.setImageResource(R.mipmap.ic_pattery);
                break;
            case 2:
                holder.numberTxt.setTextColor(Color.RED);
                holder.statusTxt.setTextColor(Color.RED);
                holder.modeTxt.setTextColor(Color.RED);
                holder.pattery.setImageResource(R.mipmap.ic_pattery_empty);
                break;
            case 3:
                holder.numberTxt.setTextColor(Color.GRAY);
                holder.statusTxt.setTextColor(Color.GRAY);
                holder.modeTxt.setTextColor(Color.GRAY);
                holder.pattery.setImageResource(R.mipmap.ic_pattery_error);
                break;
        }

        switch (mList.get(position).getMode()) {

        }

        holder.modeTxt.setText("Mode " + Constant.LETTLE[mList.get(position).getMode()]);

        holder.nameTxt.setText(mList.get(position).getName());
        holder.numberTxt.setText("" + (position + 1));

        holder.modeTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startModeActivity(position, mList.get(position).getMode());
            }
        });

        holder.numberTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startSettingActivity(position, mList.get(position).getMode());
            }
        });

        holder.statusTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public interface OnItemClickListener {
        void onClick(int position);
    }

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

}
