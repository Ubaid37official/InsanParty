package insan.app.insanparty.member;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import insan.app.insanparty.member.MemberLoginActivity;
import rehanfoundation.app.insanparty.R;

public class MemberActivity extends Activity {

    private TextView memberLogin,memberRegister;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_members);

        memberLogin = (TextView)findViewById(R.id.memberLogin);
        memberRegister = (TextView)findViewById(R.id.memberRegister);

        memberLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MemberLoginActivity.class);
                startActivity(intent);
            }
        });

        memberRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), rehanfoundation.app.insanparty.member.MemberRegisterActivity.class);
                startActivity(intent);
            }
        });

    }

}
