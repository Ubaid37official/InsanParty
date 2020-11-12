package rehanfoundation.app.insanparty;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.annotation.Nullable;

public class UserRegisterActivity extends Activity {

    private EditText name,email,password,phone,age,education,profession,location;
    private RadioGroup gender;
    private ProgressDialog progressDialog;
    private Button register;
    private RadioButton Worktime,Task;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userregister);

        progressDialog = new ProgressDialog(this);
        //Edit Texts
        email = (EditText)findViewById(R.id.email);
        name = (EditText)findViewById(R.id.name);
        password = (EditText)findViewById(R.id.password);
        phone = (EditText)findViewById(R.id.phone);
        age = (EditText)findViewById(R.id.age);
        education = (EditText)findViewById(R.id.education);
        profession = (EditText)findViewById(R.id.profession);
        location = (EditText)findViewById(R.id.location);
        //Radio Groups
        gender = (RadioGroup)findViewById(R.id.radioGender);

        //Button
        register = (Button)findViewById(R.id.register);

    }
}
