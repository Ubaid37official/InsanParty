package rehanfoundation.app.insanparty.user;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;
import rehanfoundation.app.insanparty.MemberListActivity;
import rehanfoundation.app.insanparty.MyActivity;
import rehanfoundation.app.insanparty.R;
import rehanfoundation.app.insanparty.UtilFunctions;
import rehanfoundation.app.insanparty.member.MemberEditProfileActivity;
import rehanfoundation.app.insanparty.model.login.MDLogin;
import rehanfoundation.app.insanparty.retrofitpkg.RetroServices;
import rehanfoundation.app.insanparty.retrofitpkg.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserEditProfileActivity extends AppCompatActivity {
    
    TextView txtDOB;
    final Calendar myCalendar = Calendar.getInstance();
    int resultImage;
    ImageView imgCamera, backImg;
    CircleImageView profile_image;
    String imageString = "", image;
    Button btnUpdate;
    private ProgressDialog dialog;
    SharedPreferences prefs;
    String id;
    EditText etDepartment, etProfession, etEmail, etName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_edit_profile);

        dialog = new ProgressDialog(this);
        dialog.setMessage("Please wait...");
        dialog.setCancelable(false);

        prefs = getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE);
        id = prefs.getString("id", "");

        image = getIntent().getStringExtra("image");

        txtDOB = findViewById(R.id.txtDOB);
        imgCamera = findViewById(R.id.imgCamera);
        profile_image = findViewById(R.id.profile_image);
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etProfession = findViewById(R.id.etProfession);
        etDepartment = findViewById(R.id.etDepartment);
        btnUpdate = findViewById(R.id.btnUpdate);
        backImg = findViewById(R.id.backImg);

        Picasso.with(UserEditProfileActivity.this)
                .load(UtilFunctions.urlUser + image)
                .into(profile_image);

        init();
    }

    private void init() {
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

                txtDOB.setText(sdf.format(myCalendar.getTime()));
            }

        };

        txtDOB.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(UserEditProfileActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        imgCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.Companion.with(UserEditProfileActivity.this)
                        .cropSquare()            //Crop image(Optional), Check Customization for more option
                        .compress(1024)            //Final image size will be less than 1 MB(Optional)
                        .maxResultSize(512, 512)    //Final image resolution will be less than 1080 x 1080(Optional)
                        .start();

                resultImage = 1;
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()){
                    sendData();
                }
            }
        });

        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultImage == 1 && resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            profile_image.setImageURI(uri);

//            Toast.makeText(this, "Uri : "+uri, Toast.LENGTH_SHORT).show();

            if (uri != null) {
                Bitmap bitmap = null;
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                byte[] byteArray = outputStream.toByteArray();

                //Use your Base64 String as you wish
                imageString = Base64.encodeToString(byteArray, Base64.DEFAULT);

                Log.e("base64 : ", imageString);
            }
        }
    }

    private void sendData(){
        dialog.show();
        RetroServices service = RetrofitClientInstance.getApiClient().create(RetroServices.class);
        Call<MDLogin> call = service.userUpdateProfile(
                etName.getText().toString(),
                etEmail.getText().toString(),
                txtDOB.getText().toString(),
                etDepartment.getText().toString(),
                etProfession.getText().toString(),
                id,
                imageString);
        call.enqueue(new Callback<MDLogin>() {

            @Override
            public void onResponse(Call<MDLogin> call, Response<MDLogin> response) {
                if (response.isSuccessful()){
                    MDLogin mdRegister = response.body();
                    boolean status = mdRegister.getStatus();
                    String message = mdRegister.getMessage();

                    dialog.dismiss();

                    if (status == true){
                        Toast.makeText(UserEditProfileActivity.this, ""+ message, Toast.LENGTH_SHORT).show();
                        onBackPressed();
                        finish();
                    }
                    if (status == false){
                        Toast.makeText(UserEditProfileActivity.this, ""+ message, Toast.LENGTH_SHORT).show();
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

    private boolean validate() {

        boolean valid = true;

        String emailS, department, name, profession;

        emailS = etEmail.getText().toString();
        name = etName.getText().toString();
        profession = etProfession.getText().toString();
        department = etDepartment.getText().toString();


        if(TextUtils.isEmpty(name)){
            etName.setError("Enter Name");
            valid = false;
        }
        else{
            etName.setError(null);
        }

        if(TextUtils.isEmpty(profession)){
            etProfession.setError("Enter Profession");
            valid = false;
        }
        else{
            etProfession.setError(null);
        }

        if(TextUtils.isEmpty(emailS)){
            etEmail.setError("Enter email first");
            valid = false;
            if (Patterns.EMAIL_ADDRESS.matcher( emailS ).matches()){
                etEmail.setError("Kindly enter valid email address");
                valid = false;
            }
        }
        else{
            etEmail.setError(null);
        }

        if(TextUtils.isEmpty(department)){
            etDepartment.setError("Enter Education");
            valid=false;
        }
        else{
            etDepartment.setError(null);
        }

        if(txtDOB.getText().toString().equals("DD-MM-YYYY")){
            Toast.makeText(this, "Enter your dob", Toast.LENGTH_SHORT).show();
            valid=false;
        }

        return valid;
    }
}