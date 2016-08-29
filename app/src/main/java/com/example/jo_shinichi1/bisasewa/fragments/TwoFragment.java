package com.example.jo_shinichi1.bisasewa.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.jo_shinichi1.bisasewa.Adapter.Mysingleton;
import com.example.jo_shinichi1.bisasewa.Adapter.RecyclerItemClickListener;
import com.example.jo_shinichi1.bisasewa.Adapter.UserAdapter;
import com.example.jo_shinichi1.bisasewa.Adapter.UserAdapterFeed;
import com.example.jo_shinichi1.bisasewa.Contact;
import com.example.jo_shinichi1.bisasewa.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TwoFragment extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    public String josn_url = "http://192.168.0.29/testjsontwo.php";


    public TwoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        getData();
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View V = inflater.inflate(R.layout.fragment_two, container, false);
        recyclerView = (RecyclerView) V.findViewById(R.id.recyclerviewtwo);
        layoutManager = new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // TODO Handle item click
                        Toast.makeText(getActivity(), "this is position "+position, Toast.LENGTH_SHORT).show();
                    }
                })
        );
        return V;
    }

    public void getData(){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, josn_url, (String) null,
                new Response.Listener<JSONArray>() {
                    ArrayList<Contact> contacts = new ArrayList<Contact>();
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.e(this.getClass().getName(),response.toString());
                        for (int i = 0; i <= response.length(); i++){
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                Contact contact = new Contact();
                                contact.setName(jsonObject.getString("nama"));
                                contact.setEmail(jsonObject.getString("email"));
                                contact.setImg_url(jsonObject.getString("foto"));
                                contacts.add(i,contact);
                                adapter = new UserAdapterFeed(contacts,TwoFragment.this);
                                recyclerView.setAdapter(adapter);
                                adapter.notifyDataSetChanged();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(),error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        Mysingleton.getMysingleton(TwoFragment.this).addToRequestQue(jsonArrayRequest);
    }


}
