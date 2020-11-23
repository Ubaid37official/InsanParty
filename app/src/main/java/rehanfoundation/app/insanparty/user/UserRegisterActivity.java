package rehanfoundation.app.insanparty.user;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;



import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import rehanfoundation.app.insanparty.R;
import rehanfoundation.app.insanparty.member.MemberHomeActivity;
import rehanfoundation.app.insanparty.member.MemberRegisterActivity;
import rehanfoundation.app.insanparty.model.login.MDLogin;
import rehanfoundation.app.insanparty.model.member_login.MDMemberLogin;
import rehanfoundation.app.insanparty.retrofitpkg.RetroServices;
import rehanfoundation.app.insanparty.retrofitpkg.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRegisterActivity extends Activity {

    private EditText name,email,password,phone,education,profession,location;
    private RadioGroup gender;
    TextView age;
    private Button register,upload;
    private RadioButton Worktime,Task;
    private RadioButton male, female;
    String genderString;
    private ImageView image;
    final Calendar myCalendar = Calendar.getInstance();
    private static final int PICK_IMAGE = 1;
    Uri imageUri;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userregister);

        dialog = new ProgressDialog(this);
        dialog.setMessage("Please wait...");
        dialog.setCancelable(false);

        male = findViewById(R.id.male);
        female = findViewById(R.id.female);

        //Edit Texts
        email = (EditText)findViewById(R.id.email);
        name = (EditText)findViewById(R.id.name);
        password = (EditText)findViewById(R.id.password);
        phone = (EditText)findViewById(R.id.phone);
        age = findViewById(R.id.age);
        education = (EditText)findViewById(R.id.education);
        profession = (EditText)findViewById(R.id.profession);
        location = (EditText)findViewById(R.id.location);
        //Radio Groups
        gender = (RadioGroup)findViewById(R.id.radioGender);

        //Button
        register = (Button)findViewById(R.id.register);
        upload = (Button)findViewById(R.id.upload);
        // Image Views

        image = (ImageView)findViewById(R.id.image);

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
//                updateLabel();
                String myFormat = "dd-MM-yyyy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                age.setText(sdf.format(myCalendar.getTime()));
            }

        };

        age.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(UserRegisterActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (male.isSelected()){
                    genderString = "M";
                }else {
                    genderString = "F";
                }
                postData();
            }
        });


    }

    private void postData(){

        final String Name = name.getText().toString().trim();
        final String Email = email.getText().toString().trim();
        final String Password = password.getText().toString().trim();
        final String Phone = phone.getText().toString().trim();
        final String Age = age.getText().toString().trim();
        final String Education = education.getText().toString().trim();
        final String Profession = profession.getText().toString().trim();




        if(Name.isEmpty()){
            name.setError("Name is Required");
            name.requestFocus();
            dialog.dismiss();
            return;
        }

        if(Email.isEmpty()){
            email.setError("Email is Required");
            email.requestFocus();
            dialog.dismiss();
            return;
        }
        if(Password.isEmpty()){
            password.setError("Password is Required");
            password.requestFocus();
            dialog.dismiss();
            return;
        }
        if(Phone.isEmpty()){
            phone.setError("Phone is Required");
            phone.requestFocus();
            dialog.dismiss();
            return;
        }
        if(Age.isEmpty()){
            age.setError("Age is Required");
            age.requestFocus();
            dialog.dismiss();
            return;
        }
        if(Education.isEmpty()){
            education.setError("Education is Required");
            education.requestFocus();
            dialog.dismiss();
            return;
        }
        if(Profession.isEmpty()){
            profession.setError("Profession is Required");
            profession.requestFocus();
            dialog.dismiss();
            return;
        }

        dialog.show();
        RetroServices service = RetrofitClientInstance.getApiClient().create(RetroServices.class);
        Call<MDLogin> call = service.userRegister(
                name.getText().toString(),
                email.getText().toString(),
                password.getText().toString(),
                phone.getText().toString(),
                age.getText().toString(),
                genderString,
                education.getText().toString(),
                profession.getText().toString());
        call.enqueue(new Callback<MDLogin>() {

            @Override
            public void onResponse(Call<MDLogin> call, Response<MDLogin> response) {
                if (response.isSuccessful()){
                    MDLogin mdRegister = response.body();
                    boolean status = mdRegister.getStatus();
                    String message = mdRegister.getMessage();

                    dialog.dismiss();

                    if (status == true){
                        startActivity(new Intent(UserRegisterActivity.this, UserHomeActivity.class));
                    }
                    if (status == false){
                        Toast.makeText(UserRegisterActivity.this, ""+ message, Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<MDLogin> call, Throwable t) {
//                Toast.makeText(LoginActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                Log.e("exception", t.toString());
            }
        });
    }
}
