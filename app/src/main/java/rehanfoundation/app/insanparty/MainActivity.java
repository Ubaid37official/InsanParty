package rehanfoundation.app.insanparty;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import rehanfoundation.app.insanparty.member.MemberActivity;
import rehanfoundation.app.insanparty.user.UserActivity;

public class MainActivity extends Activity {

    Button user,member;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

     user = (Button)findViewById(R.id.user);
     member = (Button)findViewById(R.id.member);


       member .setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(getApplicationContext(), MemberActivity.class);
               startActivity(intent);
           }
       });
     user.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {

             Intent intent = new Intent(getApplicationContext(), UserActivity.class);
             startActivity(intent);

         }
     });


    }

}
