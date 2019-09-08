package wami.ikechukwu.kanu;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class newsAdapter extends RecyclerView.Adapter<newsAdapter.viewHolder> {
    //TODO: if the code doesn't work, change this ARRAYLIST TO LIST USING THE FORMAT BELOW
    // private List<dataModel> mDataModel;

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
