package com.example.weekend6homeassignment;

import com.example.weekend6homeassignment.model.datasource.okhttp.OkHttpHelper;
import com.example.weekend6homeassignment.view.activities.MainActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;


import static org.junit.Assert.*;


@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {

    private MainActivity mainActivity;

    @Before
    public void setMainActivity() throws Exception {

        mainActivity = Robolectric.buildActivity(MainActivity.class)
                .create()
                .resume()
                .get();

    }

    @Test
    public void mainActivityNotNull() throws Exception {

        assertNotNull(mainActivity);

    }

    @Test
    public void recyclerViewNotNull() throws Exception {

        assertNotNull(mainActivity.findViewById(R.id.booksRecyclerViewId));

    }

    @Test
    public void viewModelNotNull() throws Exception {

        assertNotNull(mainActivity.activityMainBinding.getBookViewModel());

    }

    @Test
    public void apiCall() throws Exception {

        OkHttpHelper okHttpHelper = new OkHttpHelper();

    }
}
