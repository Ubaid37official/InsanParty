package rehanfoundation.app.insanparty.user;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import rehanfoundation.app.insanparty.R;

public class UserActivity extends Activity {

    private TextView userLogin,userRegister;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        userLogin = (TextView)findViewById(R.id.userLogin);
        userRegister = (TextView)findViewById(R.id.userRegister);

        userLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UserLoginActivity.class);
                startActivity(intent);
            }
        });

        userRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UserRegisterActivity.class);
                startActivity(intent);
            }
        });

    }
}
