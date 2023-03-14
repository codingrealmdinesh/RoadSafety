package com.roadsafety;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.roadsafety.tememeticsProvider.ITelemeticsProvider;
import com.roadsafety.tememeticsProvider.TelemeticsFactory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {


    public MainActivity(){
        String deviceId = "1b213197-1ecd-4b1e-9a2c-71fe9a182ee9";

        //SharedPreferences pref = getPreferences(MODE_PRIVATE);
        //if((deviceId = pref.getString("deviceId", null)) == null){
            //deviceId = "1b213197-1ecd-4b1e-9a2c-71fe9a182ee9";
            //pref.edit().putString("deviceId", deviceId).apply();
        //}

        sTelemeticsProvider =  TelemeticsFactory.GetZenRoadProvider(this, deviceId);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home,R.id.navigation_rank, R.id.navigation_dashboard, R.id.navigation_Challan)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        getTelemetics().ValidatePermissons();
    }

    static ITelemeticsProvider sTelemeticsProvider = null;
    public static ITelemeticsProvider getTelemetics(){
        return sTelemeticsProvider;
    }
}
