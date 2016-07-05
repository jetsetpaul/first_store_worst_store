package jetsetpaul.e_commerce_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

public class DetailActivity extends AppCompatActivity {
    public static final String EXTRA_PRODUCT = "product";
    public static final String NEW_EXTRA = "product2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent i = getIntent();
        if(i.hasExtra(NEW_EXTRA)){
            Log.d("PaulsApp", "Had Extra!");
            //set the text in my fragment
            Product product = (Product) i.getSerializableExtra(NEW_EXTRA);
            Fragment newFragment = new DetailFragment();
            Button playButton = (Button)findViewById(R.id.play);

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.detail_fragment, newFragment);
            transaction.commit();
            ((DetailFragment) newFragment).setProductTitle(product.title);
            ((DetailFragment) newFragment).setProductArtist(product.artist);
            ((DetailFragment) newFragment).setProductDescription(product.description);
            ((DetailFragment) newFragment).setProductCost(product.price);
            ((DetailFragment) newFragment).setProductImage(product.imageId);
            ((DetailFragment) newFragment).setProduct(product);

        }
    }
}
