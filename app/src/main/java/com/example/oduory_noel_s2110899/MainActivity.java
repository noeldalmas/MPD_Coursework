// Noel Dalmas Oduory S2110899
package com.example.oduory_noel_s2110899;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.oduory_noel_s2110899.databinding.ActivityMainBinding;
import com.example.oduory_noel_s2110899.ui.forecast.ForecastViewModel;
import com.example.oduory_noel_s2110899.ui.quick_observations.QuickObservationsViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences = getSharedPreferences("app_preferences", MODE_PRIVATE);
        boolean isDarkTheme = sharedPreferences.getBoolean("isDarkTheme", false);
        if (isDarkTheme) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_quick_observations, R.id.navigation_all_observations, R.id.navigation_3_day_forecast, R.id.navigation_about)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        QuickObservationsViewModel quickObservationsViewModel = new ViewModelProvider(this).get(QuickObservationsViewModel.class);
        List<String> locationIds = Arrays.asList("2648579", "2643743", "5128581", "287286", "934154", "1185241");
        if (isNetworkConnected()) {
            for (String locationId : locationIds) {
                quickObservationsViewModel.fetchData(locationId);
            }
        } else {
            Toast.makeText(this, "Please connect to the internet", Toast.LENGTH_SHORT).show();
        }

        ForecastViewModel forecastViewModel = new ViewModelProvider(this).get(ForecastViewModel.class);
        if (isNetworkConnected()) {
            for (String locationId : locationIds) {
                forecastViewModel.fetchData(locationId);
            }
        } else {
            Toast.makeText(this, "Please connect to the internet", Toast.LENGTH_SHORT).show();
        }
    }


    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                Network network = cm.getActiveNetwork();
                if (network == null) return false;
                NetworkCapabilities capabilities = cm.getNetworkCapabilities(network);
                return capabilities != null && (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR));
            } else {
                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                return activeNetwork != null && activeNetwork.isConnected();
            }
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.global_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_change_theme) {
            boolean isDarkTheme = sharedPreferences.getBoolean("isDarkTheme", false);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            if (isDarkTheme) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                editor.putBoolean("isDarkTheme", false);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                editor.putBoolean("isDarkTheme", true);
            }
            editor.apply();
            return true;
        } else if (id == R.id.action_update_frequency) {
            // Handle update frequency change
            // This is a placeholder. You'll need to implement this based on your application's requirements.
            return true;
        } else if (id == R.id.action_change_orientation) {
            int currentOrientation = getResources().getConfiguration().orientation;
            if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            } else {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}