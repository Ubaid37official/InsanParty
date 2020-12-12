package insan.app.insanparty;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import insan.app.insanparty.model.member_detail.MDMemberDetail;
import insan.app.insanparty.retrofitpkg.RetroServices;
import insan.app.insanparty.retrofitpkg.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MemberListProfileActivity extends AppCompatActivity {

    String user_id;
    private ProgressDialog dialog;
    CircleImageView profile_image;
    ImageView imgBack;
    TextView name, designation, description;
    Button complainBtn, msgBtn, callBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        user_id = getIntent().getStringExtra("user_id");

        profile_image = findViewById(R.id.profile_image);
        imgBack = findViewById(R.id.imgBack);
        name = findViewById(R.id.name);
        designation = findViewById(R.id.designation);
        description = findViewById(R.id.description);
        complainBtn = findViewById(R.id.complainBtn);
        msgBtn = findViewById(R.id.msgBtn);
        callBtn = findViewById(R.id.callBtn);

        dialog = new ProgressDialog(MemberListProfileActivity.this);
        dialog.setMessage("Please wait...");
        dialog.setCancelable(false);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finish();
            }
        });

        getData();
    }

    private void getData() {
        dialog.show();
        RetroServices service = RetrofitClientInstance.getApiClient().create(RetroServices.class);
        Call<MDMemberDetail> call = service.memberDetail(user_id);
        call.enqueue(new Callback<MDMemberDetail>() {
            @Override
            public void onResponse(Call<MDMemberDetail> call, Response<MDMemberDetail> response) {
                if (response.isSuccessful()){
                    dialog.dismiss();
                    MDMemberDetail mdCourses = response.body();
                    boolean status = mdCourses.getStatus();

                    if (status == true) {
                        name.setText(mdCourses.getMember().getName());
                        designation.setText(mdCourses.getMember().getMprofile().getQualification());
                        description.setText(mdCourses.getMember().getMprofile().getAbilities());

                        Picasso.with(MemberListProfileActivity.this)
                                .load(UtilFunctions.urlUser + mdCourses.getMember().getImage())
                                .into(profile_image);

                        msgBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String x = "sms:" + mdCourses.getMember().getMprofile().getPhone();
                                Intent smsIntent = new Intent(Intent.ACTION_SENDTO);
                                smsIntent.addCategory(Intent.CATEGORY_DEFAULT);
                                smsIntent.setType("vnd.android-dir/mms-sms");
                                smsIntent.setData(Uri.parse(x));
                                startActivity(smsIntent);
                            }
                        });

                        callBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String x = "tel:" + mdCourses.getMember().getMprofile().getPhone();
                                Intent intent = new Intent(Intent.ACTION_DIAL);
                                intent.setData(Uri.parse(x));
                                startActivity(intent);
                            }
                        });
                    }
                }
            }

            @Override
            public void onFailure(Call<MDMemberDetail> call, Throwable t) {
                dialog.dismiss();
            }
        });
    }
}