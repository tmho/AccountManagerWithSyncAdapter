package com.example.tosmond.hellosyncaccounts.account.sync;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.Context;
import android.content.SyncResult;
import android.os.Bundle;
import android.util.Log;

import com.example.tosmond.hellosyncaccounts.db.model.Date;

public class DateSyncAdapter extends AbstractThreadedSyncAdapter {
    private Context context;

    public DateSyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);
        this.context = context;
    }

    @Override
    public void onPerformSync(Account account, Bundle extras, String authority, ContentProviderClient provider, SyncResult syncResult) {
        try {
            //do backend shit here that needs syncing
            AccountManager manager = AccountManager.get(this.context);
            String authToken = manager.peekAuthToken(account, SyncUtil.AUTH_TYPE);
            Log.e("", "username:  " + account.name);
            Log.e("", "authToken:  " + authToken);
            Log.e("", "password:  " + manager.getPassword(account));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Date date = new Date();
        date.setDate(new java.util.Date().toString());
        date.save();
    }
}
