package wami.ikechukwu.kanu;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
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
    private onclicklistener clicklistener;

    public newsAdapter(Context context, ArrayList<dataModel> mDataModel, onclicklistener clicklistener) {

        this.context = context;
        this.mDataModel = mDataModel;
        this.clicklistener = clicklistener;
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
    public void onBindViewHolder(@NonNull final newsAdapter.viewHolder viewHolder, final int i) {

        dataModel dataModel = mDataModel.get(i);
        viewHolder.mTextView.setText(dataModel.getTitle());
        viewHolder.mTextDescrip.setText(dataModel.getDescrip());
        Glide.with(context).load(dataModel.getImage()).into(viewHolder.mImageView);

    }

    @Override
    public int getItemCount() {

        return mDataModel.size();
    }

    public interface onclicklistener {

        void onItemClick(int position);

    }

    public class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView mTextView;
        public ImageView mImageView;
        public TextView mTextDescrip;
        public CardView mCardView;

        public viewHolder(@NonNull View itemView) {

            super(itemView);
            mTextView = itemView.findViewById(R.id.layout_text);
            mImageView = itemView.findViewById(R.id.layout_image);
            mTextDescrip = itemView.findViewById(R.id.layout_descrip);
            mCardView = itemView.findViewById(R.id.recyclerviewlayout);
            mCardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            int adapterposition = getAdapterPosition();
            clicklistener.onItemClick(adapterposition);
        }

    }

}
