package wami.ikechukwu.kanu;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class newsAdapter extends RecyclerView.Adapter<newsAdapter.viewHolder> {

    private ArrayList<dataModel> mDataModel;
    private Context context;

    public newsAdapter(Context context, ArrayList<dataModel> mDataModel) {

        this.context = context;
        this.mDataModel = mDataModel;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view =
                LayoutInflater.from(context).inflate(R.layout.recyclerview_layout,
                        viewGroup, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull newsAdapter.viewHolder viewHolder, int i) {

        dataModel dataModel = mDataModel.get(i);
        viewHolder.mTextView.setText(dataModel.getTitle());
        //viewHolder.mImageView.setImageResource(dataModel.getImage());
        Glide.with(context).load(dataModel.getImage()).into(viewHolder.mImageView);
    }

    @Override
    public int getItemCount() {

        return mDataModel.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {

        public TextView mTextView;
        public ImageView mImageView;

        public viewHolder(@NonNull View itemView) {

            super(itemView);
            mTextView = itemView.findViewById(R.id.layout_text);
            mImageView = itemView.findViewById(R.id.layout_image);
        }

    }

}
