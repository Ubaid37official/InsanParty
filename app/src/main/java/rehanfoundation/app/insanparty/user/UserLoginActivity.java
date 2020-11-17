package rehanfoundation.app.insanparty.user;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import rehanfoundation.app.insanparty.R;
import rehanfoundation.app.insanparty.model.LoginResponse;
import rehanfoundation.app.insanparty.model.User;
import rehanfoundation.app.insanparty.retrofitUtil.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
                    validateInputs();
            }
        });

    }

    private void validateInputs(){
        final String Email = email.getText().toString().trim();
        final String Password = password.getText().toString().trim();


        if(Email.isEmpty()){
            email.setError("Email is Required");
            email.requestFocus();
            return;
        }
        if(Password.isEmpty()){
            password.setError("Password is Required");
            password.requestFocus();
            return;
        }

        Call<User> call = RetrofitClient.getInstance().getApi().userLogin(Email,Password);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                if (user.isStatus()==true){
                    Toast.makeText(getApplicationContext(),"Logged In",Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(),user.getUser_id(),Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(),user.getEducation(),Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(),user.getLocation(),Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(getApplicationContext(),user.getMessage(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }



}







