package com.example.shaunmesias.assignment_6_2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.shaunmesias.assignment_6_2.domain.driver.Driver;

import java.util.ArrayList;
/**
 * Created by Shaun Mesias on 2016/08/24.
 */
public class CustomListAdapter extends BaseAdapter {

    private ArrayList<Driver> listData;
    private LayoutInflater layoutInflater;

    public CustomListAdapter(Context context, ArrayList<Driver> listData) {
        this.listData = listData;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.new_list_layout, null);
            holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.txtName);
            holder.area = (TextView) convertView.findViewById(R.id.txtLocation);
            holder.email = (TextView) convertView.findViewById(R.id.txtEmail);
            holder.contact = (TextView) convertView.findViewById(R.id.txtContact);
            holder.owner = (TextView) convertView.findViewById(R.id.txtOwner);
            holder.car = (TextView) convertView.findViewById(R.id.txtCar);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

            holder.name.setText(listData.get(position).getName());
            holder.area.setText("By, " + listData.get(position).getArea());
            holder.email.setText(listData.get(position).getEmail());
            holder.contact.setText(listData.get(position).getDriverContact().getContactValue());
            holder.owner.setText(listData.get(position).getDriverDetails().getOwnerName());
            holder.car.setText(listData.get(position).getDriverDetails().getCarName());
            listData.iterator().next();

        return convertView;
    }

    static class ViewHolder {
        TextView name;
        TextView area;
        TextView email;
        TextView contact;
        TextView owner;
        TextView car;
    }
}
