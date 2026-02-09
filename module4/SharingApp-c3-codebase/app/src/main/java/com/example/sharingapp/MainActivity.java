package com.example.sharingapp;

import android.content.Intent;
import com.google.android.material.tabs.TabLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

/**
 * Home Activity of the App
 */
public class MainActivity extends AppCompatActivity {

    private String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent(); // Get intent from LoginActivity
        user_id = intent.getStringExtra("user_id");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SectionsPagerAdapter mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), user_id);

        ViewPager mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setOffscreenPageLimit(0);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.search) {
            Intent searchIntent = new Intent(this, SearchActivity.class);
            searchIntent.putExtra("user_id", user_id);
            startActivity(searchIntent);
            return true;
        } else if (id == R.id.borrowed_items) {
            Intent borrowedIntent = new Intent(this, BorrowedItemsActivity.class);
            borrowedIntent.putExtra("user_id", user_id);
            startActivity(borrowedIntent);
            return true;
        } else if (id == R.id.edit_profile) {
            Intent profileIntent = new Intent(this, EditUserActivity.class);
            profileIntent.putExtra("user_id", user_id);
            startActivity(profileIntent);
            return true;
        } else if (id == R.id.logout) {
            Intent logoutIntent = new Intent(this, LoginActivity.class);
            Toast.makeText(getApplicationContext(), "Goodbye", Toast.LENGTH_SHORT).show();
            startActivity(logoutIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent logoutIntent = new Intent(this, LoginActivity.class);
        startActivity(logoutIntent);
    }

    public void addItemActivity(View view) {
        Intent intent = new Intent(this, AddItemActivity.class);
        intent.putExtra("user_id", user_id);
        startActivity(intent);
    }
}
