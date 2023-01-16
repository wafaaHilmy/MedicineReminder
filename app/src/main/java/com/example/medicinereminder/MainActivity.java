package com.example.medicinereminder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
  private   BottomNavigationView bottomNavView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         bottomNavView=findViewById(R.id.bottomNavigation);

        bottomNavView.setOnItemSelectedListener(itemSelectedListener);
       getSupportFragmentManager().beginTransaction().replace(R.id.mainActivity_container,new MedicationFragment()) .commit();
        getSupportActionBar().setTitle(getString(R.string.medications));
    }
/*-------------------------------------------------------------------------------------------------------------*/
/*-------------------------------------------------------------------------------------------------------------*/

    BottomNavigationView.OnItemSelectedListener itemSelectedListener=new   BottomNavigationView.OnItemSelectedListener(){

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment selectedFragment=null;
            switch (item.getItemId()){
                case R.id.nav_medications:
                    selectedFragment=new MedicationFragment();
                    getSupportActionBar().setTitle(item.getTitle());
                    break;
                case R.id.nav_all_list:
                    selectedFragment=new ListFragment(); 
                    // change title as name of bottomtab
                    getSupportActionBar().setTitle(item.getTitle());
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.mainActivity_container,selectedFragment).commit();
            return true;
        }
    };
/*-------------------------------------------------------------------------------------------------------------*/



}