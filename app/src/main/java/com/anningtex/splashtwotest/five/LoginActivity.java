package com.anningtex.splashtwotest.five;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.anningtex.splashtwotest.R;
import com.anningtex.splashtwotest.act.FiveActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;

/**
 * @author Administrator
 */
public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.email_sign_in_button)
    void textClick() {
        startActivity(new Intent(LoginActivity.this, FiveActivity.class));
        finish();
    }

    @OnEditorAction(R.id.password)
    boolean EditTextAction(TextView textView, int id, KeyEvent keyEvent) {
        if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
            startActivity(new Intent(LoginActivity.this, FiveActivity.class));
            finish();
            return true;
        }
        return false;
    }
}