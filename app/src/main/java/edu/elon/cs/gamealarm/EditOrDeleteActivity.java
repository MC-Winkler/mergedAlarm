package edu.elon.cs.gamealarm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.BaseAdapter;

public class EditOrDeleteActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_or_delete);
        Intent intent = getIntent();
    }

    public void onEditClick(View view){
        Intent intent = new Intent(this, AddAlarmActivity.class);
        intent.putExtra("action", "edit");
        startActivity(intent);
        finish();
    }

    public void onDeleteClick(View view){
        MainActivity.alarmArrayList.remove(MainActivity.relevantPosition);
        MainActivity.arrayAdapter.notifyDataSetChanged();
        finish();
    }

}
