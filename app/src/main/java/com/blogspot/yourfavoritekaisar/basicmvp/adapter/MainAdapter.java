package com.blogspot.yourfavoritekaisar.basicmvp.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blogspot.yourfavoritekaisar.basicmvp.R;
import com.blogspot.yourfavoritekaisar.basicmvp.model.user.UserData;
import com.blogspot.yourfavoritekaisar.basicmvp.model.utils.constant;
import com.blogspot.yourfavoritekaisar.basicmvp.view.DetailActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    private final Context context;
    private final List<UserData> userDataList;


    public MainAdapter(Context context, List<UserData> userDataList) {
        this.context = context;
        this.userDataList = userDataList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_user, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final UserData userData = userDataList.get(i);

        viewHolder.txtFirst.setText(userData.getFirst_name());
        viewHolder.txtLast.setText(userData.getLast_name());

        RequestOptions options = new RequestOptions().error(R.drawable.ic_error_black_24dp).placeholder(R.drawable.ic_broken_image_black_24dp);

        Glide.with(context).load(userData.getAvatar()).apply(options).into(viewHolder.imgOrang);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mengirim data menggunakan bundle
                // Buat object bundle
                Bundle bundle = new Bundle();
                bundle.putInt(constant.KEY_ID,userData.getId());

                // Berpindah halaman dengan membawa data
                context.startActivity(new Intent(context, DetailActivity.class).putExtras(bundle));
            }
        });

    }

    @Override
    public int getItemCount() {
        return userDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imgOrang)
        ImageView imgOrang;
        @BindView(R.id.txtFirst)
        TextView txtFirst;
        @BindView(R.id.txtLast)
        TextView txtLast;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
