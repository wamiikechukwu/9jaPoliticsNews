package wami.ikechukwu.kanu;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class newsAdapter extends RecyclerView.Adapter<newsAdapter.viewHolder> {

    private ArrayList<dataModel> mDataModel;

    public newsAdapter(ArrayList<dataModel> mDataModel) {

        this.mDataModel = mDataModel;
    }

    @NonNull
    @Override
    public newsAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view =
                LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_layout,
                        viewGroup, false);
        newsAdapter.viewHolder holder = new newsAdapter.viewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull newsAdapter.viewHolder viewHolder, int i) {

        dataModel currentItem = mDataModel.get(i);
        viewHolder.mTextView.setText(currentItem.getTitle());
    }

    @Override
    public int getItemCount() {

        return mDataModel.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {

        public TextView mTextView;

        public viewHolder(@NonNull View itemView) {

            super(itemView);
            mTextView = itemView.findViewById(R.id.layout_text);
        }

    }

}
