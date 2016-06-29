package jetsetpaul.e_commerce_app;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by pauljoiner on 6/25/16.
 */
public class View_Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public CardView cv;
    public TextView title;
    public TextView artist;
    public TextView description;
    public TextView price;
    public ImageView imageView;
    public IMyViewHolderClicks mListener;
    private Product product;

    @Override
    public void onClick(View v) {
        mListener.onItemClick(product);
    }

    View_Holder(View itemView, IMyViewHolderClicks mListener) {
        super(itemView);
        cv = (CardView) itemView.findViewById(R.id.cardView);
        title = (TextView) itemView.findViewById(R.id.title);
        artist = (TextView) itemView.findViewById(R.id.artist);
        description = (TextView) itemView.findViewById(R.id.description);
        price = (TextView) itemView.findViewById(R.id.price);
        imageView = (ImageView) itemView.findViewById(R.id.imageView);
        itemView.setOnClickListener((View.OnClickListener) this);
        this.mListener = mListener;
    }

    public interface IMyViewHolderClicks{
        void onItemClick(Product product);
    }

    public void bindData(Product product){
        this.product = product;
    }
}
