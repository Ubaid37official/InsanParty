package insan.app.insanparty;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterationForm<Work> extends Activity {

    private EditText email,name,fatheName,cnic,phone,socialLink,qualification,city,address,constituency,politicalBackground,passion,abilities,changePakistan;
    private Spinner interestPolitics, shadowPosition;
    private RadioGroup workTime,task;
    private CheckBox abideLaw;
    private ProgressDialog progressDialog;
    private Button register;
    private RadioButton Worktime,Task;
    private DatePicker birthDate;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        progressDialog = new ProgressDialog(this);
        //Edit Texts
        email = (EditText)findViewById(R.id.email);
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



        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.interestPolitics, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        interestPolitics.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.shadowPosition, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        shadowPosition.setAdapter(adapter2);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                if( email.getText().toString().trim().equals(""))
//                {
//                    email.setError( "Email Address is required!" );
//                }
                final String Email = email.getText().toString().trim();
//                if( name.getText().toString().trim().equals(""))
//                {
//                    name.setError( "Full Name is required!" );
//                }
                final String Name = name.getText().toString().trim();
//                if( fatheName.getText().toString().trim().equals(""))
//                {
//                    fatheName.setError( "Father Name is required!" );
//                }
                final String FatherName = fatheName.getText().toString().trim();
//                if( cnic.getText().toString().trim().equals(""))
//                {
//                    cnic.setError( "CNIC is required!" );
//                }
                final String Cnic = cnic.getText().toString().trim();
//                if( phone.getText().toString().trim().equals(""))
//                {
//                    phone.setError( "Phone or WhatsApp is required!" );
//                }
                final String Phone = phone.getText().toString().trim();
//                if( socialLink.getText().toString().trim().equals(""))
//                {
//                    socialLink.setError( "Social Media Link is required!" );
//                }
                final String SocialLink = socialLink.getText().toString().trim();
//                if( birthDate.getText().toString().trim().equals(""))
//                {
//                    birthDate.setError( "Date of Birth is required!" );
//                }
                String day = String.valueOf(birthDate.getDayOfMonth());
                String month = String.valueOf(birthDate.getMonth()+1);
                String year = String.valueOf(birthDate.getYear());
                final String BirthDate = year+"-"+month+"-"+day;

//                Toast.makeText(getApplicationContext(),BirthDate,Toast.LENGTH_LONG).show();
//                if( qualification.getText().toString().trim().equals(""))
//                {
//                    qualification.setError( "Qualification is required!" );
//                }
                final String Qualification = qualification.getText().toString().trim();
//                if( city.getText().toString().trim().equals(""))
//                {
//                    city.setError( "City and Country is required!" );
//                }
                final String City = city.getText().toString().trim();
//                if( address.getText().toString().trim().equals(""))
//                {
//                    address.setError( "Address is required!" );
//                }
                final String Address = address.getText().toString().trim();
//                if( constituency.getText().toString().trim().equals(""))
//                {
//                    constituency.setError( "Constituency Number is required!" );
//                }
                final String Constituency = constituency.getText().toString().trim();
//                if( politicalBackground.getText().toString().trim().equals(""))
//                {
//                    politicalBackground.setError( "Political Background is required!" );
//                }
                final String PoliticalBackground = politicalBackground.getText().toString().trim();
//                if( passion.getText().toString().trim().equals(""))
//                {
//                    passion.setError( "Passion is required!" );
//                }
                final String Passion = passion.getText().toString().trim();
//                if( abilities.getText().toString().trim().equals(""))
//                {
//                    abilities.setError( "Abilities field is required!" );
//                }
                final String Abilities = abilities.getText().toString().trim();
//                if( changePakistan.getText().toString().trim().equals(""))
//                {
//                    changePakistan.setError( "Change in Pakistan field is required!" );
//                }
                final String ChangePakistan = changePakistan.getText().toString().trim();
                //Spinners
//                if( email.getText().toString().trim().equals(""))
//                {
//                    email.setError( "Email Address is required!" );
//                }
                final String PoliticsInterest = interestPolitics.getSelectedItem().toString().trim();
//                if( email.getText().toString().trim().equals(""))
//                {
//                    email.setError( "Email Address is required!" );
//                }
                final String PositionShadow = shadowPosition.getSelectedItem().toString().trim();
                //Radio Buttons
                int selectedId = workTime.getCheckedRadioButtonId();
                Worktime = (RadioButton) findViewById(selectedId);
//                if( email.getText().toString().trim().equals(""))
//                {
//                    email.setError( "Email Address is required!" );
//                }
                final String WorkHours = Worktime.getText().toString().trim();
                int selectedId2 = task.getCheckedRadioButtonId();
                Task = (RadioButton) findViewById(selectedId2);
//                if( email.getText().toString().trim().equals(""))
//                {
//                    email.setError( "Email Address is required!" );
//                }
                final String PerformTask = Task.getText().toString().trim();
                //Check Box
                abideLaw.isChecked();
//                if( email.getText().toString().trim().equals(""))
//                {
//                    email.setError( "Email Address is required!" );
//                }
                final String AbideLaw = abideLaw.getText().toString().trim();


                progressDialog.setMessage("Registering Member...");
                progressDialog.show();

                StringRequest stringRequest = new StringRequest(Request.Method.GET,"https://insan.pk/api/members/registerApi.php?Email="+Email+
                        "&Name="+Name+"&FatherName="+FatherName+"&CNIC="+Cnic+"&Phone="+Phone+"&Social="+SocialLink+"&BirthDate="+BirthDate+"&Qualification="+Qualification+
                        "&City="+City+"&Address="+Address+ "&Constituency="+Constituency+"&PoliticalBackground="+PoliticalBackground+"&Passion="+Passion+
                        "&Abilities="+Abilities+"&PoliticsInterest="+PoliticsInterest+"&ShadowPosition="+PositionShadow+
                        "&ChangePakistan="+ChangePakistan+"&WorkTime="+WorkHours+"&Task="+PerformTask+"&AbideLaw="+AbideLaw,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                progressDialog.dismiss();

                                try {
                                    JSONObject jsonObject = new JSONObject(response);

                                    Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(getApplicationContext(),RegisterationForm.class);
                                    startActivity(intent);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                progressDialog.hide();
                                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                            }
//                        }) {
//                    @Override
//                    protected Map<String, String> getParams() throws AuthFailureError {
//                        Map<String, String> params = new HashMap<>();
//
//                        params.put("Email", Email);
//                        params.put("Name", Name);
//                        params.put("FatherName", FatherName);
//                        params.put("CNIC", Cnic);
//                        params.put("Phone", Phone);
//                        params.put("Social", SocialLink);
//                        params.put("BirthDate", BirthDate);
//                        params.put("Qualification", Qualification);
//                        params.put("City", City);
//                        params.put("Address", Address);
//                        params.put("Constituency", Constituency);
//                        params.put("PoliticalBackground", PoliticalBackground);
//                        params.put("Passion", Passion);
//                        params.put("Abilities", Abilities);
//                        params.put("PoliticsInterest", PoliticsInterest);
//                        params.put("ShadowPosition", PositionShadow);
//                        params.put("ChangePakistan", ChangePakistan);
//                        params.put("WorkTime", WorkHours);
//                        params.put("Task", PerformTask);
//                        params.put("AbideLaw", AbideLaw);
//
//                        return params;
//
//                    }
                });

                RequestQueue requestQueue = Volley.newRequestQueue(RegisterationForm.this);
                requestQueue.add(stringRequest);


            }
        });
    }
}
