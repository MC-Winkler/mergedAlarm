package edu.elon.cs.gamealarm;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.util.Calendar;


public class AlarmCheckService extends IntentService {
    public AlarmCheckService() {
        super("AlarmCheckService");
    }

    @Override
    protected void onHandleIntent (Intent intent){
        Calendar cal = Calendar.getInstance();
        System.out.println("in the alarmservice");
        int alarmIndex = intent.getIntExtra("alarmIndex",0);
        synchronized (this){
            try{
                wait((MainActivity.alarmArrayList.get(alarmIndex).getMinutes() - cal.MINUTE ) * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Alarm go off now");
    }
}
