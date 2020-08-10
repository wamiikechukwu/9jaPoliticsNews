package wami.ikechukwu.kanu.viewmodel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import wami.ikechukwu.kanu.R;
import wami.ikechukwu.kanu.model.onboarding.OnboardingModel;

public class newsAdapter extends RecyclerView.Adapter<newsAdapter.viewHolder> {

    private ArrayList<OnboardingModel> mOnboardingModel;
    private Context context;
    private onclicklistener clicklistener;

    public newsAdapter(Context context, ArrayList<OnboardingModel> mOnboardingModel, onclicklistener clicklistener) {

        this.context = context;
        this.mOnboardingModel = mOnboardingModel;
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

//        OnboardingModel OnboardingModel = mOnboardingModel.get(i);
//        viewHolder.mTextView.setText(OnboardingModel.getTitle());
//        viewHolder.mTextDescrip.setText(OnboardingModel.getDescrip());
//        Glide.with(context).load(OnboardingModel.getImage()).into(viewHolder.mImageView);

    }

    @Override
    public int getItemCount() {

        return mOnboardingModel.size();
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
