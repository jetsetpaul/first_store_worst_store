package jetsetpaul.e_commerce_app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by pauljoiner on 6/27/16.
 */
public class DetailFragment extends Fragment {
    private String product;
    private String description;
    private int image;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return our inflated layout
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }
    public void setProductTitle(String product){
        //access our textView
        this.product = product;
    }
    public void setProductDescription(String description){
        this.description = description;
    }
    public void setProductImage(int image){
        this.image = image;
    }

    @Override
    public void onResume() {
        super.onResume();
        TextView title = (TextView) getView().findViewById(R.id.fragmentTitle);
        title.setText(product);
        TextView itemDescription = (TextView) getView().findViewById(R.id.fragmentDescription);
        itemDescription.setText(description);
        ImageView itemImage = (ImageView) getView().findViewById(R.id.fragmentImage);
        itemImage.setImageResource(image);

    }
}
