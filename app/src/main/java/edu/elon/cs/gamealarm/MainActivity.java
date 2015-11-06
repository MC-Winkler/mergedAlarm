package edu.elon.cs.gamealarm;

import android.app.Activity;
import android.app.AlarmManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private ListView listView;
    protected static List<Alarm> alarmArrayList;
    protected static int relevantPosition;
    protected static ArrayAdapter<Alarm> arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.alarmList);

        alarmArrayList = new ArrayList<Alarm>();


        alarmArrayList.add(new Alarm (16,50));
        alarmArrayList.add(new Alarm(2, 4));

        arrayAdapter = new ArrayAdapter<Alarm>(this,
                android.R.layout.simple_list_item_1,
                alarmArrayList);


        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(itemClickListener);
        listView.setOnItemLongClickListener(itemLongClickListener);

                    //add logic for re-ordering the list
        System.out.println("about to start the alarm check service");
        Intent intent = new Intent (this, AlarmCheckService.class);
        startService(intent);


    }

    AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Alarm current = alarmArrayList.get(position);
            relevantPosition = position;
            if (current.isOn()) {
                current.setOn(false);
            } else {
                current.setOn(true);
                System.out.println("turned alarm on");
                callService();
            }
        }
    };

    private void callService (){
        Intent intent = new Intent (this, AlarmCheckService.class);
        intent.putExtra("alarmIndex", relevantPosition);
        startService(intent);
    }

    AdapterView.OnItemLongClickListener itemLongClickListener = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            relevantPosition = position;
            startEditOrDelete();
            return true;
        }
    };

    private void startEditOrDelete(){
        Intent intent = new Intent (this, EditOrDeleteActivity.class);
        startActivity(intent);
        arrayAdapter.notifyDataSetChanged();
    }

    public void onAddClick(View view){
        Intent intent = new Intent (this, AddAlarmActivity.class);
        intent.putExtra("action", "add");
        startActivity(intent);
    }
}


