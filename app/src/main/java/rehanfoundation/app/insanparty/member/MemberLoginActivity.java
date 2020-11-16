package rehanfoundation.app.insanparty.member;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import rehanfoundation.app.insanparty.R;
import rehanfoundation.app.insanparty.user.UserHomeActivity;

public class MemberLoginActivity extends Activity {

    private EditText email, password;
    private  TextView forgotPassword;
    private Button login;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memberlogin);

        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        forgotPassword = (TextView)findViewById(R.id.forgotPassword);

        login = (Button)findViewById(R.id.login);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String Email = email.getText().toString();
                final String Password = password.getText().toString();

                Intent intent = new Intent(getApplicationContext(),MemberHomeActivity.class);
                startActivity(intent);



            }
        });

    }
}
