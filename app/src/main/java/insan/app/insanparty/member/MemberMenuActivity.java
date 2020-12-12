package insan.app.insanparty.member;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import insan.app.insanparty.R;
import insan.app.insanparty.RegisterationForm;

public class MemberMenuActivity extends Activity {

    Button fbPage,fbgroup,form,regForm;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_membermenu);

        fbPage = (Button)findViewById(R.id.fbpage);
        fbgroup = (Button)findViewById(R.id.fbgroup);
        form = (Button)findViewById(R.id.form);
        regForm = (Button)findViewById(R.id.regForm);

        regForm .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterationForm.class);
                startActivity(intent);
            }
        });
        fbPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.facebook.com/InsanDotPk/"));
                startActivity(intent);

            }
        });
        fbgroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.facebook.com/groups/insancandidate/"));
                startActivity(intent);
            }
        });

        form.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://insan.pk/Register/"));
                startActivity(intent);
            }
        });

    }

}
