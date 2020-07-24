package dev.ksick.coderdojo.mapreactions;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

public class SecondFragment extends Fragment {

    private TextView textViewInfo;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textViewInfo = view.findViewById(R.id.textview_info);

        String phrase = getArguments().getString("phrase");
        textViewInfo.setText(phrase);

        loadRoute(phrase);
    }

    private void loadRoute(String phrase) {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());

        String url = null;
        try {
            url = "https://api.map-reactions.ksick.dev/v0-1/route?phrase=" + URLEncoder.encode(phrase, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            textViewInfo.setText(e.getMessage());
            return;
        }

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        textViewInfo.setText(response);

                        List<Place> places = new Gson().fromJson(response, new TypeToken<List<Place>>() {}.getType());
                        textViewInfo.setText(places.get(0).toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        textViewInfo.setText(error.getMessage());
                    }
                }
        );

        requestQueue.add(stringRequest);
    }
}