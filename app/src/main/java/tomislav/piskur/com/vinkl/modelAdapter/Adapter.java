package tomislav.piskur.com.vinkl.modelAdapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import tomislav.piskur.com.vinkl.AlaCarte;
import tomislav.piskur.com.vinkl.R;

/**
 * Created by srs on 17.02.2018.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<Menu> listItems;


    public Adapter(List<Menu> listItems, Context context) {
        this.listItems = listItems;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.menu, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


        Menu listItem = listItems.get(position);
        holder.tvHead.setText(listItem.getHead());
        holder.tvDesc.setText(listItem.getDesc());
        holder.tvDan.setText(listItem.getDan());

        if (holder.tvHead.getText().toString().matches("")) {
            holder.tvHead.setVisibility(View.GONE);
        } else {
            holder.tvHead.setVisibility(View.VISIBLE);
        }


    }


    @Override
    public int getItemCount() {
        return listItems.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvHead, tvDesc, tvDan;


        public ViewHolder(View itemView) {
            super(itemView);


            tvHead = itemView.findViewById(R.id.tvHead);
            tvDesc = itemView.findViewById(R.id.tvDesc);
            tvDan = itemView.findViewById(R.id.tvDan);

        }


    }


}

