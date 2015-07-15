package com.example.tosmond.hellosyncaccounts.account.sync;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class JokeSyncService extends Service {
    private static JokeSyncAdapter jokeSyncAdapter = null;
    private static final Object LOCK = new Object();
    @Override
    public void onCreate() {
        synchronized (LOCK) {
            if (jokeSyncAdapter == null) {
                jokeSyncAdapter = new JokeSyncAdapter(getApplicationContext(), true);
            }
        }
    }
    @Override
    public IBinder onBind(Intent intent) {
        return jokeSyncAdapter.getSyncAdapterBinder();
    }
}