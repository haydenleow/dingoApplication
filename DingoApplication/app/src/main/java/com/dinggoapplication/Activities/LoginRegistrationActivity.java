/*
 * Copyright (C) Technify Pte Ltd - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Lee Quee Leong <leequeeleong@technify.com.sg> and
 * Seah Siu Ngee <seahsiungee@techinify.com.sg, May 2015
 */

package com.dinggoapplication.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.dinggoapplication.R;
import com.dinggoapplication.Utility.DAOUtil;
import com.parse.ParseObject;

import java.util.HashMap;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Activity class that executes activities within login page
 * <p>
 * Inflated layout that requires user authentication in order to gain access to the application for usage
 *
 * @author Lee Quee Leong & Seah Siu Ngee
 * @version 2.1
 * Created by leon on 10/2/2015.
 */
public class LoginRegistrationActivity extends Activity implements View.OnClickListener {

    /**
     * Called when the activity is starting.  This is where most initializationboss
     * should go: calling {@link #setContentView(int)} to inflate the
     * activity's UI, using {@link #findViewById} to programmatically interact
     * with widgets in the UI, calling
     * {@link #managedQuery(Uri, String[], String, String[], String)} to retrieve
     * cursors for data being displayed, etc.
     * <p/>
     * <p>You can call {@link #finish} from within this function, in
     * which case onDestroy() will be immediately called without any of the rest
     * of the activity lifecycle ({@link #onStart}, {@link #onResume},
     * {@link #onPause}, etc) executing.
     * <p/>
     * <p><em>Derived classes must call through to the super class's
     * implementation of this method.  If they do not, an exception will be
     * thrown.</em></p>
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *                           previously being shut down then this Bundle contains the data it most
     *                           recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     * @see #onStart
     * @see #onSaveInstanceState
     * @see #onRestoreInstanceState
     * @see #onPostCreate
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_registration);

        // When login button is clicked
        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            /**
             * Called when a view has been clicked.
             *
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                EditText usernameControl = (EditText) findViewById(R.id.username);
                String username = usernameControl.getText().toString();

                // Determine customer or merchant activity to access
                //if (username.equalsIgnoreCase("customer")) {

                    // Start activity for customer view
                    Intent intent = new Intent(LoginRegistrationActivity.this, EatDrinkActivity.class);
                    startActivity(intent);

                /*} else {
                    // Toast box appear for invalid input
                    Toast.makeText(LoginRegistrationActivity.this, "Invalid username/password.\nPlease try again",
                            Toast.LENGTH_LONG).show();
                }*/
            }
        });

    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.register) {
        /*
            Create an intent that ask user to pick a photo, but using
            FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET, ensures that relaunching
            the application from the device home screen does not return
            to the external activity
        */
            /*
            Intent externalActivityIntent = new Intent(Intent.ACTION_PICK);
            externalActivityIntent.setType("image/*");
            externalActivityIntent.addFlags(
                    Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(externalActivityIntent);
            */
            register();

        } else if (v.getId() == R.id.facebookText) {
            facebookLogin();
        }
    }

    /**
     * Customization for the UI settings
     * @param newBase New context object for the UI
     */
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    /**
     * Register a new account
     */
    public void register() {
        try {
            ParseObject parseObject = DAOUtil.getObject("user", "PxTkuFmMAT");
            Toast.makeText(this, "Register an account " + parseObject.getString("username"), Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Skip registration, allow user to trial use app with dinging related features blocked
     */
    public void skipRegistration() {
        Toast.makeText(this, "Skip Registration & use App", Toast.LENGTH_LONG).show();
    }

    /**
     * Facebook integration to cater for facebook login
     */
    public void facebookLogin() {
        Toast.makeText(this, "Facebook Login", Toast.LENGTH_LONG).show();
    }
}