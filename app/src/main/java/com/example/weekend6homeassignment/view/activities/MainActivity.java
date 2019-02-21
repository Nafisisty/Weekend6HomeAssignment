package com.example.weekend6homeassignment.view.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.example.weekend6homeassignment.R;
import com.example.weekend6homeassignment.databinding.ActivityMainBinding;
import com.example.weekend6homeassignment.model.books.Book;
import com.example.weekend6homeassignment.model.datasource.okhttp.OkHttpHelper;
import com.example.weekend6homeassignment.model.events.BookEvent;
import com.example.weekend6homeassignment.view.adapters.RecyclerViewAdapter;
import com.example.weekend6homeassignment.viewmodel.BookViewModel;
import com.flurry.android.FlurryAgent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Arrays;
import java.util.List;

import static com.example.weekend6homeassignment.model.Constants.BASE_URL;

public class MainActivity extends AppCompatActivity implements MainActivityContract {

    RecyclerViewAdapter recyclerViewAdapter;
    public static ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityMainBinding.setBookViewModel(new BookViewModel(this, new Book()));
        activityMainBinding.executePendingBindings();

        new FlurryAgent.Builder()
                .withLogEnabled(true)
                .build(this, "3F366G85PM62J4QYGQFD");

        OkHttpHelper.asyncOkHttpApiCall(BASE_URL);

    }

    @Override
    protected void onStart() {
        super.onStart();

        EventBus.getDefault().register(this);

    }

    @Override
    protected void onStop() {
        super.onStop();

        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void bookEvent(BookEvent event) {
        if(event != null) {

            activityMainBinding.booksRecyclerViewId.setLayoutManager(new LinearLayoutManager(this));
            activityMainBinding.booksRecyclerViewId.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
            List<Book> bookList = Arrays.asList(event.getBooks());

            recyclerViewAdapter = new RecyclerViewAdapter(bookList);
            activityMainBinding.booksRecyclerViewId.setAdapter(recyclerViewAdapter);

        }
    }

    @Override
    public void getSelectedBook(Book book) {
        if(book != null){

            FlurryAgent.logEvent(book.getTitle() + " Clicked");

            Intent intent = new Intent(this, DetailsActivity.class);
            intent.putExtra("book", book);
            startActivity(intent);

            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

        } else {
            Toast.makeText(this, "No book values", Toast.LENGTH_SHORT).show();
        }
    }
}
