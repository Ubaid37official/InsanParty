package insan.app.insanparty.member;


import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import insan.app.insanparty.R;

public class MemberHomeActivity extends Activity {

    private TextView name,joinDate;
    private Button fullMember;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memberhome);


        name = (TextView)findViewById(R.id.name);
        joinDate = (TextView)findViewById(R.id.joinDate);
        fullMember = (Button) findViewById(R.id.fullMember);

    }
}

