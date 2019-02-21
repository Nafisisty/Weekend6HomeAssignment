package com.example.weekend6homeassignment.view.activities;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.weekend6homeassignment.R;
import com.example.weekend6homeassignment.databinding.ActivityDetailsBinding;
import com.example.weekend6homeassignment.model.books.Book;
import com.example.weekend6homeassignment.viewmodel.BookViewModel;


public class DetailsActivity extends AppCompatActivity {

    Book aBook = new Book();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();

        if(bundle != null) {

            aBook = bundle.getParcelable("book");

        }

        ActivityDetailsBinding activityDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_details);
        activityDetailsBinding.setBookViewModel(new BookViewModel(aBook));
        activityDetailsBinding.executePendingBindings();
    }

    @BindingAdapter("imageUrl")
    public static void setImage(ImageView imageView, String url) {
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }
}
