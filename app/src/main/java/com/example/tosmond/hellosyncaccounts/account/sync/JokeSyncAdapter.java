package com.example.tosmond.hellosyncaccounts.account.sync;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.Context;
import android.content.SyncResult;
import android.os.Bundle;
import android.util.Log;

import com.example.tosmond.hellosyncaccounts.db.model.Joke;

import java.util.Random;

public class JokeSyncAdapter extends AbstractThreadedSyncAdapter {
    private Context context;
    private static final String[] JOKES = new String[]{
            "Q: Why do Farts stink? A: So that Deaf people can enjoy them too.",
            "Q: Why did the Hedgehog cross the road? A: To see his Flat Mate.",
            "Q: What do you call a Fish without an eye? A: A 'Fsh'!",
            "Q: Why does it take 1 million sperm to fertilize one egg? A: They won't stop to ask directions.",
            "Q: What's the difference between Big Foot and an intelligent man? A: Big Foot's been spotted several times.",
            "Q: What do you call a handcuffed man? A: Trustworthy.",
            "Q: What did the fish say when he hit a concrete wall? A: \"Dam\".",
            "Q: Why is divorce so expensive? A: Because it's worth it."
    };
    private static final Random RANDOM = new Random();

    public JokeSyncAdapter(Context context, boolean autoInitialize) {
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
        Joke joke = new Joke();
        joke.setAuthor("http://www.funny.com/cgi-bin/WebObjects/Funny.woa/wa/funny?fn=CI3NJ");
        joke.setJoke(JOKES[RANDOM.nextInt(JOKES.length)]);
        joke.save();
    }
}
