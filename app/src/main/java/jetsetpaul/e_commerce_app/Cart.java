package jetsetpaul.e_commerce_app;

import java.util.ArrayList;

/**
 * Created by pauljoiner on 6/29/16.
 */
public class Cart {
    //define the cart object
    private static Cart cart;

    //define the list to hold the products
    ArrayList<Product> products;

    private Cart(){
        products = new ArrayList<>();
    }
    public static Cart getInstance(){
        if(cart == null)
            cart = new Cart();
        return cart;
    }
    public void addProduct(Product product){
        products.add(product);
    }

    public void removeProduct(Product product){
        products.remove(product);
    }

    public ArrayList<Product> getProducts(){
        return products;
    }
}
