package rehanfoundation.app.insanparty.member;

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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;
import rehanfoundation.app.insanparty.R;
import insan.app.insanparty.model.login.MDLogin;
import rehanfoundation.app.insanparty.retrofitpkg.RetroServices;
import rehanfoundation.app.insanparty.retrofitpkg.RetrofitClientInstance;
import rehanfoundation.app.insanparty.user.UserEditProfileActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MemberEditProfileActivity extends AppCompatActivity {

    TextView txtDOB;
    final Calendar myCalendar = Calendar.getInstance();
    int resultImage;
    ImageView imgCamera, backImg;
    CircleImageView profile_image;
    String imageString = "";
    Button btnUpdate;
    private ProgressDialog dialog;
    SharedPreferences prefs;
    String id;
    EditText etName, etFatherName, etEmail, etAddress, etCNIC, etQualification, etNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_edit_profile);

        dialog = new ProgressDialog(this);
        dialog.setMessage("Please wait...");
        dialog.setCancelable(false);

        prefs = getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE);
        id = prefs.getString("id", "");

        etName = findViewById(R.id.etName);
        etFatherName = findViewById(R.id.etFatherName);
        etEmail = findViewById(R.id.etEmail);
        etAddress = findViewById(R.id.etAddress);
        etCNIC = findViewById(R.id.etCNIC);
        etQualification = findViewById(R.id.etQualification);
        etNumber = findViewById(R.id.etNumber);

        txtDOB = findViewById(R.id.txtDOB);
        imgCamera = findViewById(R.id.imgCamera);
        profile_image = findViewById(R.id.profile_image);
        btnUpdate = findViewById(R.id.btnUpdate);
        backImg = findViewById(R.id.backImg);

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
                new DatePickerDialog(MemberEditProfileActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        imgCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.Companion.with(MemberEditProfileActivity.this)
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
        Call<MDLogin> call = service.memberUpdateProfile(
                etName.getText().toString(),
                etEmail.getText().toString(),
                etFatherName.getText().toString(),
                etCNIC.getText().toString(),
                etNumber.getText().toString(),
                txtDOB.getText().toString(),
                etQualification.getText().toString(),
                etAddress.getText().toString(),
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
                        Toast.makeText(MemberEditProfileActivity.this, ""+ message, Toast.LENGTH_SHORT).show();
                        onBackPressed();
                        finish();
                    }
                    if (status == false){
                        Toast.makeText(MemberEditProfileActivity.this, ""+ message, Toast.LENGTH_SHORT).show();
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

        String emailS, fatherName, address, cnic, qualification, name, number;

        emailS = etEmail.getText().toString();
        name = etName.getText().toString();
        fatherName = etFatherName.getText().toString();
        address = etAddress.getText().toString();
        cnic = etCNIC.getText().toString();
        qualification = etQualification.getText().toString();
        number = etNumber.getText().toString();


        if(TextUtils.isEmpty(name)){
            etName.setError("Enter Name");
            valid = false;
        }
        else{
            etName.setError(null);
        }

        if(TextUtils.isEmpty(fatherName)){
            etFatherName.setError("Enter Father Name");
            valid = false;
        }
        else{
            etFatherName.setError(null);
        }

        if(TextUtils.isEmpty(address)){
            etAddress.setError("Enter Address");
            valid = false;
        }
        else{
            etAddress.setError(null);
        }

        if(TextUtils.isEmpty(cnic)){
            etCNIC.setError("Enter CNIC");
            valid = false;
        }
        else{
            etCNIC.setError(null);
        }

        if(TextUtils.isEmpty(number)){
            etNumber.setError("Enter Number");
            valid = false;
        }
        else{
            etNumber.setError(null);
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

        if(TextUtils.isEmpty(qualification)){
            etQualification.setError("Enter Education");
            valid=false;
        }
        else{
            etQualification.setError(null);
        }

        if(txtDOB.getText().toString().equals("DD-MM-YYYY")){
            Toast.makeText(this, "Enter your dob", Toast.LENGTH_SHORT).show();
            valid=false;
        }

        return valid;
    }
}