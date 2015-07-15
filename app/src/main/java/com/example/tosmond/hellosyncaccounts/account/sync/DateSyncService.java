package com.example.tosmond.hellosyncaccounts.account.sync;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class DateSyncService extends Service {
    private static DateSyncAdapter syncAdapter = null;
    private static final Object LOCK = new Object();
    @Override
    public void onCreate() {
        synchronized (LOCK) {
            if (syncAdapter == null) {
                syncAdapter = new DateSyncAdapter(getApplicationContext(), true);
            }
        }
    }
    @Override
    public IBinder onBind(Intent intent) {
        return syncAdapter.getSyncAdapterBinder();
    }
}