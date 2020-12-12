package insan.app.insanparty;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import insan.app.insanparty.member.MemberHomeActivity;


public class SplashActivity extends Activity {

    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences prefs = getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE);
                String cat = prefs.getString("cat", "");//"No name defined" is the default value.
                String id = prefs.getString("id", ""); //0 is the default value.

                if (id != ""){
                    if (cat.equals("user")){
                        startActivity(new Intent(SplashActivity.this, MemberListActivity.class));
                        finishAffinity();
                    }else if (cat.equals("member")){
                        startActivity(new Intent(SplashActivity.this, MemberHomeActivity.class));
                        finishAffinity();
                    }
                }else if (id.equals("")){
                    Intent intent=new Intent(SplashActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        },4000);


    }
}
