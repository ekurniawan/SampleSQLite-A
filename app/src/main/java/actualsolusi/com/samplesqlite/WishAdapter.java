package actualsolusi.com.samplesqlite;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import models.MyWish;

/**
 * Created by erick on 06/11/2017.
 */

public class WishAdapter extends RecyclerView.Adapter<WishAdapter.MyViewHolder> {
    List<MyWish> wishList;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle, tvContent;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView)itemView.findViewById(R.id.tvTitle);
            tvContent = (TextView)itemView.findViewById(R.id.tvContent);
        }
    }

    public WishAdapter(List<MyWish> wishList){
        this.wishList = wishList;
    }

    @Override
    public WishAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_wish,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(WishAdapter.MyViewHolder holder, int position) {
        MyWish wish = wishList.get(position);
        holder.tvTitle.setText(wish.getTitle());
        holder.tvContent.setText(wish.getContent());
    }

    @Override
    public int getItemCount() {
        return wishList.size();
    }


}
