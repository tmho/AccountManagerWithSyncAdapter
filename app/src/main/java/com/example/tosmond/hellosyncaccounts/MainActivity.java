package com.example.tosmond.hellosyncaccounts;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.ContentResolver;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.tosmond.hellosyncaccounts.account.sync.SyncUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.add_date).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestSync(SyncUtil.DATE_AUTHORITY);
            }
        });

        findViewById(R.id.add_joke).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestSync(SyncUtil.JOKE_AUTHORITY);
            }
        });
    }

    private void requestSync(String authority) {
        AccountManager manager = AccountManager.get(getApplicationContext());
        Account[] accounts = manager.getAccountsByType(SyncUtil.ACCOUNT_TYPE);

        if (accounts.length == 0) {
            Toast.makeText(getApplicationContext(), "Go into settings and add an account", Toast.LENGTH_SHORT).show();
        } else {
            Bundle settingsBundle = new Bundle();
            settingsBundle.putBoolean(ContentResolver.SYNC_EXTRAS_MANUAL, true);
            settingsBundle.putBoolean(ContentResolver.SYNC_EXTRAS_EXPEDITED, true);
            ContentResolver.requestSync(accounts[0], authority, settingsBundle);
        }
    }
}
