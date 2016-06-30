package jetsetpaul.e_commerce_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class CartList extends AppCompatActivity{
    Intent intent1 = getIntent();
    ArrayList<Product> products;
    Button buyButton;
    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_list);
        products = Cart.getInstance().products;

        final ListView cartList = (ListView)findViewById(R.id.listView);

        final ArrayAdapter<Product> arrayAdapter =
                new ArrayAdapter<Product>(this, android.R.layout.simple_list_item_1, products);
        cartList.setAdapter(arrayAdapter);
        buyButton = (Button) findViewById(R.id.buyButton);
        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CartList.this, "$$$$$$$", Toast.LENGTH_SHORT).show();
            }
        });
        cartList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Cart.getInstance().removeProduct(products.get(position));
                arrayAdapter.notifyDataSetChanged();

                return false;
            }
        });
    }
}
