package com.example.laurent.velostation;

import android.app.Activity;
import android.content.Context;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

public class MapActivity extends Activity{

    private Context mContext;
    private View v;
    private MapView mapView;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private RequestQueue mRequestQueue;
    private Button returnButton;
    private String urlSearch = "https://nominatim.openstreetmap.org/search?q=";

    public void onCreate(Bundle savedInstanceState) {
        //setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        // https://github.com/osmdroid/osmdroid/wiki/How-to-use-the-osmdroid-library
        mapView = (org.osmdroid.views.MapView) v.findViewById(R.id.mapview);
        mapView.setTileSource(TileSourceFactory.MAPNIK);
        mapView.setBuiltInZoomControls(true);
        mapView.setMultiTouchControls(true);
        mapView.getController().setZoom(18);
        // default = meistraat
        mapView.getController().setCenter(new GeoPoint(51.23040, 4.41735));
        //mapView.invalidate();

        // http://code.tutsplus.com/tutorials/an-introduction-to-volley--cms-23800
        mRequestQueue = Volley.newRequestQueue(v.getContext());
        returnButton = (Button) findViewById(R.id.clear_button);
    }
}
