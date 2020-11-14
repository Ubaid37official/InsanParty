package rehanfoundation.app.insanparty.member;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.annotation.Nullable;

import rehanfoundation.app.insanparty.R;

public class MemberRegisterActivity extends Activity {

    private EditText email,name,fatheName,cnic,phone,socialLink,qualification,city,address,constituency,politicalBackground,passion,abilities,changePakistan,password;
    private Spinner interestPolitics, shadowPosition;
    private RadioGroup workTime,task;
    private CheckBox abideLaw;
    private ProgressDialog progressDialog;
    private Button register;
    private RadioButton Worktime,Task;
    private DatePicker birthDate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memberregister);

        progressDialog = new ProgressDialog(this);
        //Edit Texts
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        name = (EditText)findViewById(R.id.name);
        fatheName = (EditText)findViewById(R.id.fatherName);
        cnic = (EditText)findViewById(R.id.cnic);
        phone = (EditText)findViewById(R.id.phone);
        socialLink = (EditText)findViewById(R.id.socialLink);
        birthDate = (DatePicker) findViewById(R.id.dateBirth);
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
    }
}
