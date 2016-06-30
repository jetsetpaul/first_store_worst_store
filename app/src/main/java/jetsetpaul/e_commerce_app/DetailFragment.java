package jetsetpaul.e_commerce_app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by pauljoiner on 6/27/16.
 */
public class DetailFragment extends Fragment {
    private String productTitle;
    private String artist;
    private String description;
    private String price;
    private Product product;
    private Button addButton;
    private int image;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return our inflated layout
        View root = inflater.inflate(R.layout.fragment_detail, container, false);
        return root;
    }
    public void setProductTitle(String product){
        //access our textView
        this.productTitle = product;
    }
    public void setProductArtist(String artist){
        this.artist = artist;
    }
    public void setProductDescription(String description){
        this.description = description;
    }
    public void setProductCost(String price){
        this.price = price;
    }
    public void setProductImage(int image){
        this.image = image;
    }
    public void setProduct(Product product){
        this.product = product;
    }


    @Override
    public void onResume() {
        super.onResume();
        TextView title = (TextView) getView().findViewById(R.id.fragmentTitle);
        title.setText(productTitle);
        TextView band = (TextView) getView().findViewById(R.id.fragmentArtist);
        band.setText(artist);
        TextView itemDescription = (TextView) getView().findViewById(R.id.fragmentDescription);
        itemDescription.setText(description);
        TextView itemCost = (TextView) getView().findViewById(R.id.fragmentPrice);
        itemCost.setText(price);
        ImageView itemImage = (ImageView) getView().findViewById(R.id.fragmentImage);
        itemImage.setImageResource(image);
        addButton = (Button) getView().findViewById(R.id.button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cart.getInstance().addProduct(product);
                Toast.makeText(getActivity(), "Item has been added to cart!", Toast.LENGTH_SHORT).show();

            }
        });

    }
}
