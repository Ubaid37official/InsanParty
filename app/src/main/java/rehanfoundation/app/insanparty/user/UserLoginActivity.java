package rehanfoundation.app.insanparty.user;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.RequestHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import rehanfoundation.app.insanparty.R;
import rehanfoundation.app.insanparty.model.ApiUserResponse;
import rehanfoundation.app.insanparty.volleyUtil.Constants;
import rehanfoundation.app.insanparty.volleyUtil.VolleySingleton;

public class UserLoginActivity extends Activity {

    Button login;
    EditText email, password;
    TextView forgotPassword;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlogin);

        progressDialog = new ProgressDialog(getApplicationContext());
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        forgotPassword = (TextView) findViewById(R.id.forgotPassword);

        login = (Button) findViewById(R.id.login);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String Email = email.getText().toString();
                final String Password = password.getText().toString();

                Intent intent = new Intent(getApplicationContext(),UserHomeActivity.class);
                startActivity(intent);



            }
        });






    }
}







