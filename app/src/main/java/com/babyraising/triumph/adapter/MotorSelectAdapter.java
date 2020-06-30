package com.babyraising.triumph.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.babyraising.triumph.R;
import com.babyraising.triumph.bean.Motor;
import com.babyraising.triumph.ui.main.MotorSetupActivity;
import com.babyraising.triumph.ui.profile.ModeSelectActivity;

import java.util.List;

public class MotorSelectAdapter extends RecyclerView.Adapter<MotorSelectAdapter.ViewHolder> {

    private List<String> mList;
    private ModeSelectActivity context;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tipTxt;

        public ViewHolder(View view) {
            super(view);
            tipTxt = (TextView) view.findViewById(R.id.tip);
        }

    }

    public MotorSelectAdapter(ModeSelectActivity context, List<String> mList) {
        this.context = context;
        this.mList = mList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_motor_select, parent, false);
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

        holder.tipTxt.setText(mList.get(position));
        holder.tipTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.selectMode(position);
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
