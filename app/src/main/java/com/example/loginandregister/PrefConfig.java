package com.example.loginandregister;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

public class PrefConfig {
    private SharedPreferences mSharedPreferences;
    private Context mContext;

    public PrefConfig(Context context)
    {
        this.mContext = context;
        mSharedPreferences = context.getSharedPreferences(context.getString(R.string.pref_file),Context.MODE_PRIVATE);

    }
    public void writeLoginStatus(boolean status)
    {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putBoolean(mContext.getString(R.string.pref_login_status),status);
        editor.commit();
    }
    public  boolean readLoginStatus()
    {
        return mSharedPreferences.getBoolean(mContext.getString(R.string.pref_login_status),false);
    }

    public void writeEmail(String email){
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(mContext.getString(R.string.pref_email_status),email);
        editor.commit();
    }
    public String readEmail(){
        return mSharedPreferences.getString(mContext.getString(R.string.pref_email_status),"EmailUser");
    }
    public void displayToast(String message)
    {
        Toast.makeText(mContext,message,Toast.LENGTH_SHORT).show();
    }
}
