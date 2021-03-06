package com.example.discgolfapp;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.test.platform.app.InstrumentationRegistry;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = Build.VERSION_CODES.P)
public class LoginActivityTests {
    private LoginActivity activity;
    private FirebaseAuth auth;
    private Context appContext;

    @Before
    public void setup() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        FirebaseApp fapp = FirebaseApp.initializeApp(appContext);
        auth = FirebaseAuth.getInstance(fapp);
        activity = Robolectric.buildActivity(LoginActivity.class).create().get();
    }

    @Test
    public void validLogin() {
        final String email = "cscholz2016@gmail.com";
        final String pass = "testing123";

        EditText uname = activity.findViewById(R.id.username);
        uname.setText(email);

        EditText password = activity.findViewById(R.id.password);
        password.setText(pass);

        activity.findViewById(R.id.login).performClick();

        // assertNotNull(auth.getCurrentUser());


    }

    @Test
    public void invalidLogin() {
        final String email = "cscholz2016@gmail.com";
        final String pass = "badpass";

        EditText uname = activity.findViewById(R.id.username);
        uname.setText(email);

        EditText password = activity.findViewById(R.id.password);
        password.setText(pass);

        activity.findViewById(R.id.login).performClick();
        assertNull(auth.getCurrentUser());
    }

    @Test
    public void forgotPass() {
        final String email = "cscholz2016@gmail.com";
        activity.findViewById(R.id.btnForgot).performClick();
        AlertDialog dialog = activity.dialog;

        assertNotNull(dialog);

        EditText uname = dialog.findViewById(R.id.editForgotEmail);
        uname.setText(email);

        dialog.getButton(Dialog.BUTTON_POSITIVE).performClick();


    }
}
