package com.example.jo_shinichi1.bisasewa.Adapter;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.jo_shinichi1.bisasewa.Activity.ContactDetails;
import com.example.jo_shinichi1.bisasewa.CircularNetworkImageView;
import com.example.jo_shinichi1.bisasewa.Contact;
import com.example.jo_shinichi1.bisasewa.R;
import com.example.jo_shinichi1.bisasewa.fragments.OneFragment;
import com.example.jo_shinichi1.bisasewa.fragments.TwoFragment;
import com.example.jo_shinichi1.bisasewa.testActivity;

import java.util.ArrayList;

/**
 * Created by jo_shinichi1 on 8/25/2016.
 */
public class UserAdapterFeed extends RecyclerView.Adapter<UserAdapterFeed.UserAdapterHolder> {
   ArrayList<Contact> contacts = new ArrayList<Contact>();
    TwoFragment twoFragment;
    Fragment fragment;
    public ImageLoader imageLoader;

    public UserAdapterFeed(ArrayList<Contact> contacts, Fragment fragment){
        this.contacts = contacts;
        this.fragment = fragment;
        twoFragment = (TwoFragment) fragment;
        setHasStableIds(true);
    }

    @Override
    public UserAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_layout_tab_two,parent,false);
        UserAdapterHolder userAdapterHolder = new UserAdapterHolder(view,twoFragment,contacts);
        return userAdapterHolder;
    }

    @Override
    public void onBindViewHolder(UserAdapterHolder holder, int position) {
        Contact CON = contacts.get(position);
        imageLoader = Mysingleton.getMysingleton(twoFragment).getImageLoader();
        holder.tvName.setText(CON.getName());
        holder.tvEmail.setText(CON.getEmail());
        holder.thumbnail.setImageUrl(contacts.get(position).getImg_url(),imageLoader);

    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public static class UserAdapterHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        NetworkImageView thumbnail;
        TwoFragment twoFragment;
        CardView cardView;
        TextView tvName,tvEmail;
        ArrayList<Contact> getDataArrayList = new ArrayList<Contact>();
        public UserAdapterHolder(View itemView, final TwoFragment twoFragment, ArrayList<Contact> contactArrayList) {
            super(itemView);
            this.getDataArrayList = contactArrayList;
            this.twoFragment = twoFragment;
            thumbnail = (NetworkImageView) itemView.findViewById(R.id.thumbnail);
            tvName = (TextView) itemView.findViewById(R.id.nama);
            tvEmail = (TextView) itemView.findViewById(R.id.email);
            cardView = (CardView) itemView.findViewById(R.id.cardView);
            thumbnail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    Contact con = getDataArrayList.get(position);
                    Intent intent = new Intent(twoFragment.getActivity(),ContactDetails.class);
                    intent.putExtra("key_img_id",con.getImg_url());
                    intent.putExtra("key_name_id",con.getName());
                    intent.putExtra("key_email_id",con.getEmail());
                    twoFragment.getActivity().startActivity(intent);
                }
            });
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Contact con = this.getDataArrayList.get(position);
            Intent intent = new Intent(twoFragment.getActivity(), ContactDetails.class);
            intent.putExtra("key_img_id",con.getImg_url());
            intent.putExtra("key_name_id",con.getName());
            intent.putExtra("key_email_id",con.getEmail());
            twoFragment.getActivity().startActivity(intent);

        }
    }
}
