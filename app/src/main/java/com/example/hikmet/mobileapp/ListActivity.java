package com.example.hikmet.mobileapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.hukuk.mobileapp.entity.GlobalVariables;
import com.hukuk.mobileapp.entity.TimeEntry;
import com.tjerkw.slideexpandable.library.SlideExpandableListAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    public List<TimeEntry> LoadData(){

      List<TimeEntry> datalist= new ArrayList<TimeEntry>();
        TimeEntry data = new TimeEntry();
        data.set_projeadi("proje1");
        datalist.add(data);
        return datalist;

    }
    private List<TimeEntry> values;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ListView list = (ListView)this.findViewById(R.id.list);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                GlobalVariables.GetInstance().timeEntry = (TimeEntry) values.get(position);
                Intent intent = new Intent(ListActivity.this, ListActivity.class);
                startActivity(intent);
            }

        });
        values=LoadData();
        OrderAdapter adapter = new OrderAdapter(
                this,
                R.layout.activity_list_child,
                values
        );
        list.setAdapter(
                new SlideExpandableListAdapter
                        (
                                adapter,
                                R.id.expandable_toggle_button,
                                R.id.expandable
                        )
        );
    }
    private class OrderAdapter extends ArrayAdapter<TimeEntry> {

        private List<TimeEntry> items;

        public OrderAdapter(Context context, int textViewResourceId,
                            List<TimeEntry> items) {
            super(context, textViewResourceId, items);
            this.items = items;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            if (v == null) {
                LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(R.layout.activity_list_child, null);
            }
            TimeEntry o = items.get(position);
            if (o != null) {
                TextView lblIsletmeKodu = (TextView) v.findViewById(R.id.lblProjeAdi);
                lblIsletmeKodu.setText(o.get_projeadi());
            }
            return v;
        }

    }
}
