package insan.app.insanparty.member;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import insan.app.insanparty.R;
import insan.app.insanparty.model.member_login.MDMemberLogin;
import insan.app.insanparty.retrofitpkg.RetroServices;
import insan.app.insanparty.retrofitpkg.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MemberRegisterActivity extends Activity {

    private EditText email,name,fatheName,cnic,phone,socialLink,qualification,city,address,constituency,politicalBackground,passion,abilities,changePakistan,password;
    private Spinner interestPolitics, shadowPosition;
    private RadioGroup workTime,task;
    private CheckBox abideLaw;
    private Button register,upload;
    private RadioButton Worktime,Task;
    RadioButton daily2, weekly2, monthly2, never;
    RadioButton no, yes;
    String work, task_political, abideYes;
    private TextView dateBirth;
    private ImageView image;
    final Calendar myCalendar = Calendar.getInstance();
    Uri imageUri;
    String imageString;
    private ProgressDialog dialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memberregister);

        dialog = new ProgressDialog(this);
        dialog.setMessage("Please wait...");
        dialog.setCancelable(false);

        never = findViewById(R.id.never);
        monthly2 = findViewById(R.id.monthly2);
        weekly2 = findViewById(R.id.weekly2);
        daily2 = findViewById(R.id.daily2);
        yes = findViewById(R.id.yes);
        no = findViewById(R.id.no);
        //Edit Texts
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        name = (EditText)findViewById(R.id.name);
        fatheName = (EditText)findViewById(R.id.fatherName);
        cnic = (EditText)findViewById(R.id.cnic);
        phone = (EditText)findViewById(R.id.phone);
        socialLink = (EditText)findViewById(R.id.socialLink);
        dateBirth = findViewById(R.id.dateBirth);
        qualification = (EditText)findViewById(R.id.qualification);
        city = (EditText)findViewById(R.id.city);
        address = (EditText)findViewById(R.id.address);
        constituency = (EditText)findViewById(R.id.constituencyNo);
        politicalBackground = (EditText)findViewById(R.id.politicalBackground);
        passion = (EditText)findViewById(R.id.yourPassion);
        abilities = (EditText)findViewById(R.id.yourAbilities);
        changePakistan = (EditText)findViewById(R.id.changePakistan);
        //Spinners
        interestPolitics = (Spinner) findViewById(R.id.interestPolitics);
        shadowPosition = (Spinner) findViewById(R.id.shadowPosition);
        //Radio Groups
        workTime = (RadioGroup)findViewById(R.id.radioWork);
        task = (RadioGroup)findViewById(R.id.radioTask);

        //Check Box
        abideLaw = (CheckBox)findViewById(R.id.abideYes);
        //Button
        register = (Button)findViewById(R.id.register);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.interestPolitics2, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        interestPolitics.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.shadowPosition2, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        shadowPosition.setAdapter(adapter2);

        upload = (Button)findViewById(R.id.upload);
        // Image Views

        image = (ImageView)findViewById(R.id.image);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (daily2.isChecked()){
                    work = "2 hr daily";
                }else if (never.isChecked()){
                    work = "never";
                }else if (monthly2.isChecked()){
                    work = "2 hr monthly";
                }else if (weekly2.isChecked()){
                    work = "2 hr weekly";
                }


                if (yes.isChecked()){
                    task_political = "yes";
                }else if (no.isChecked()){
                    task_political = "no";
                }


                if (abideLaw.isChecked()){
                    abideYes = "yes";
                }else {
                    abideYes = "no";
                }

                postData();
//                Toast.makeText(MemberRegisterActivity.this, ""+work, Toast.LENGTH_SHORT).show();
            }
        });


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

                dateBirth.setText(sdf.format(myCalendar.getTime()));
            }

        };

        dateBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(MemberRegisterActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


    }

    private void postData(){
        dialog.show();
        RetroServices service = RetrofitClientInstance.getApiClient().create(RetroServices.class);
        Call<MDMemberLogin> call = service.memberRegister(name.getText().toString(),
                email.getText().toString(),
                password.getText().toString(),
                fatheName.getText().toString(),
                cnic.getText().toString(),
                phone.getText().toString(),
                socialLink.getText().toString(),
                dateBirth.getText().toString(),
                qualification.getText().toString(),
                city.getText().toString(),
                address.getText().toString(),
                constituency.getText().toString(),
                interestPolitics.getSelectedItem().toString(),
                shadowPosition.getSelectedItem().toString(),
                work,
                task_political,
                abideYes,
                politicalBackground.getText().toString(),
                passion.getText().toString(),
                abilities.getText().toString(),
                changePakistan.getText().toString()
                );
        call.enqueue(new Callback<MDMemberLogin>() {

            @Override
            public void onResponse(Call<MDMemberLogin> call, Response<MDMemberLogin> response) {
                if (response.isSuccessful()){
                    MDMemberLogin mdRegister = response.body();
                    boolean status = mdRegister.getStatus();
                    String message = mdRegister.getMessage();

                    dialog.dismiss();

                    if (status == true){
                        startActivity(new Intent(MemberRegisterActivity.this, MemberHomeActivity.class));
                    }
                    if (status == false){
                        Toast.makeText(MemberRegisterActivity.this, ""+ message, Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<MDMemberLogin> call, Throwable t) {
//                Toast.makeText(LoginActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                Log.e("exception", t.toString());
            }
        });
    }


}
