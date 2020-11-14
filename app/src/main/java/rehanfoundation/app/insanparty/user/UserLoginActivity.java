package rehanfoundation.app.insanparty.user;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import rehanfoundation.app.insanparty.R;

public class UserLoginActivity extends Activity {

    private Button login;
    private EditText email, password;
    private TextView forgotPassword;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlogin);

        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        forgotPassword = (TextView)findViewById(R.id.forgotPassword);

        login = (Button)findViewById(R.id.login);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),UserHomeActivity.class);
                startActivity(intent);
            }
        });

    }
}
