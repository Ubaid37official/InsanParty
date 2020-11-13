package rehanfoundation.app.insanparty;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class UserHomeActivity extends Activity {

    private TextView name,location,gender,age,education,profession,currentLocation,shadowMinister;
    private Button registerComplain,aboutArea;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userhome);

        name = (TextView)findViewById(R.id.name);
        location = (TextView)findViewById(R.id.location);
        gender = (TextView)findViewById(R.id.gender);
        education = (TextView)findViewById(R.id.education);
        profession = (TextView)findViewById(R.id.profession);
        currentLocation = (TextView)findViewById(R.id.currentLocation);
        shadowMinister = (TextView)findViewById(R.id.shadowMinister);
        registerComplain = (Button)findViewById(R.id.registerComplain);
        aboutArea = (Button)findViewById(R.id.aboutArea);

    }
}
