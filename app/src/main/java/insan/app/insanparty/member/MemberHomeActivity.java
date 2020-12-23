package rehanfoundation.app.insanparty.member;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import rehanfoundation.app.insanparty.R;

public class MemberHomeActivity extends Activity {

    private TextView name,joinDate;
    private Button fullMember, btnEdit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memberhome);


        name = (TextView)findViewById(R.id.name);
        joinDate = (TextView)findViewById(R.id.joinDate);
        fullMember = (Button) findViewById(R.id.fullMember);
        btnEdit = (Button) findViewById(R.id.btnEdit);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MemberHomeActivity.this, MemberEditProfileActivity.class));
            }
        });

    }
}

