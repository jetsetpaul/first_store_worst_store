package jetsetpaul.e_commerce_app;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CursorAdapter;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private List<Product> product;
    private CursorAdapter mCursorAdapter;
    private Recycler_View_Adapter adapter;
    private View_Holder.IMyViewHolderClicks listener;
    private Helper helper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addDatatoDb();
        mCursorAdapter = null;

        listener = new View_Holder.IMyViewHolderClicks() {
            @Override
            public void onItemClick(Product data) {
                Intent launchIntent = new Intent(MainActivity.this, DetailActivity.class);
                launchIntent.putExtra(DetailActivity.EXTRA_PRODUCT, "product");
                Log.d("PaulsApp", data + "");
                launchIntent.putExtra(DetailActivity.NEW_EXTRA, data);
                startActivity(launchIntent);
            }
        };
        // set up toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("The music store");
        toolbar.setSubtitle("music, merch, and more!");
        toolbar.setTitleTextColor(-1);
        toolbar.setSubtitleTextColor(-1);
        setSupportActionBar(toolbar);


        List<Product> product = Helper.getInstance(this).getAllProduct();
        // set up recyclerView
        adapter = new Recycler_View_Adapter(product, listener);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//     inflate menu
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_activity_main, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        MenuItemCompat.setOnActionExpandListener(menu.findItem(R.id.search),
                new MenuItemCompat.OnActionExpandListener(){
                    @Override
                    public boolean onMenuItemActionCollapse(MenuItem item){
                        Log.d("TAG", "this was run when we closed it");
                        product = helper.getInstance(MainActivity.this).getAllProduct();
                        adapter.switchList(product);
                        mRecyclerView.setAdapter(adapter);
                        return true;
                    }
                    @Override
                    public boolean onMenuItemActionExpand(MenuItem item){
                        Log.d("TAG", "this was run when we opened it");
                        return true;
                    }
                });


        return super.onCreateOptionsMenu(menu);
    }

    // define menu options and OnClick actions
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.browse:
                Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
                this.startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void addDatatoDb() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected void onPostExecute(Void aVoid) {
              product = Helper.getInstance(MainActivity.this).getAllProduct();
                adapter.switchList((ArrayList<Product>) product);
                adapter.notifyDataSetChanged();
            }

            @Override
            protected Void doInBackground(Void... params) {
                Helper helper = Helper.getInstance(MainActivity.this);
                helper.cleanDatabase();
                Product product1 = new Product("FTWT item", "First Thought Worst Thought", "music", "ftwt item description.", "$5", R.drawable.ftwt1);
                Product product2 = new Product("Ftwt item 2", "First Thought Worst Thought", "music", "ftwt item2 description ", "$5", R.drawable.ftwt2);
                Product product3 = new Product("ftwt merch", "First Thought Worst Thought", "clothing", "ftwt merch description", "$10", R.drawable.ftwt_merch1);
                Product product4 = new Product("luca item 1", "Luca", "music", "luca item1 description", "$5", R.drawable.luca1);
                Product product5 = new Product("luca merch1", "Luca", "clothing", "luca merch1 description", "$10", R.drawable.luca_shirt1);
                Product product6 = new Product("luca merch2", "Luca", "clothing", "luca merch2 item description ", "$10", R.drawable.luca_shirt2);
                Product product7 = new Product("civeta dei item", "Civeta Dei", "music", "Beautiful thinking", "$5", R.drawable.civetadei1);
                Product product8 = new Product("mutant love single", "Mutant Love", "music", "radiation..description", "$1", R.drawable.mutantlove1);
                Product product9 = new Product("bear on bear single", "Bear on Bear", "music", "full grown single", "$1", R.drawable.bear_on_bear_fullgrown);
                Product product10 = new Product("odd folks album", "Odd Folks", "music", "monica description", "$5", R.drawable.odd_folks_album);

                helper.insertRow(product1);
                helper.insertRow(product2);
                helper.insertRow(product3);
                helper.insertRow(product4);
                helper.insertRow(product5);
                helper.insertRow(product6);
                helper.insertRow(product7);
                helper.insertRow(product8);
                helper.insertRow(product9);
                helper.insertRow(product10);

                Band band1 = new Band("First Thought Worst Thought", "Indie-Rock", "Austin");
                Band band2 = new Band("Luca", "Alternative", "Bryan College Station");
                Band band3 = new Band("Civeta Dei", "Indie-Rock", "Houston");
                Band band4 = new Band("Mutant Love", "Punk", "Bryan College Station");
                Band band5 = new Band("Bear on Bear", "Alternative", "Austin");
                Band band6 = new Band("Odd Folks", "Pop-punk", "Bryan College Station");

                helper.insertRowBand(band1);
                helper.insertRowBand(band2);
                helper.insertRowBand(band3);
                helper.insertRowBand(band4);
                helper.insertRowBand(band5);
                helper.insertRowBand(band6);
                return null;
            }
        }.execute();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);

          ArrayList myList = Helper.getInstance(this).searchProducts(query);

          adapter = new Recycler_View_Adapter(myList, listener);
          mRecyclerView.setAdapter(adapter);
        }
        else {
            Helper.getInstance(MainActivity.this).getAllProduct();
        }
    }
}