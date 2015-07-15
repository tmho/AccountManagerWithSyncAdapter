package com.example.tosmond.hellosyncaccounts;

import android.accounts.Account;
import android.accounts.AccountAuthenticatorActivity;
import android.accounts.AccountManager;
import android.content.ContentResolver;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tosmond.hellosyncaccounts.account.sync.SyncUtil;

public class AuthenticationActivity extends AccountAuthenticatorActivity {
    public final static String ARG_ACCOUNT_TYPE = "ACCOUNT_TYPE";
    public final static String ARG_AUTH_TYPE = "AUTH_TYPE";
    public final static String ARG_IS_ADDING_NEW_ACCOUNT = "IS_ADDING_ACCOUNT";
    private AccountManager accountManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_account);

        accountManager = AccountManager.get(getBaseContext());
        final EditText usernameView = (EditText) findViewById(R.id.email);

        final EditText passwordView = (EditText) findViewById(R.id.password);
        passwordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    doLogin(usernameView.getText().toString(), passwordView.getText().toString());
                    return true;
                }
                return false;
            }
        });

        findViewById(R.id.sign_in_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doLogin(usernameView.getText().toString(), passwordView.getText().toString());
            }
        });
    }

    private void doLogin(String username, String password) {
        //ideally in here we hit some kind of backend that returns real data
        final Account account = new Account(username, SyncUtil.ACCOUNT_TYPE);

        if (getIntent().getBooleanExtra(ARG_IS_ADDING_NEW_ACCOUNT, false)) {
            String authToken = "random hash from the backend";
            String authTokenType = SyncUtil.AUTH_TYPE;
            if (accountManager.addAccountExplicitly(account, password, null)) {
                ContentResolver.setSyncAutomatically(account, SyncUtil.DATE_AUTHORITY, true);
                ContentResolver.addPeriodicSync(account, SyncUtil.DATE_AUTHORITY, new Bundle(), 1l);

                ContentResolver.setSyncAutomatically(account, SyncUtil.JOKE_AUTHORITY, true);
                ContentResolver.addPeriodicSync(account, SyncUtil.JOKE_AUTHORITY, new Bundle(), 1l);
            }
            accountManager.setAuthToken(account, authTokenType, authToken);

        } else {
            accountManager.setPassword(account, password);
        }

        setResult(RESULT_OK, null);
        finish();
    }
}
