package rehanfoundation.app.insanparty;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import rehanfoundation.app.insanparty.model.member_detail.MDMemberDetail;
import rehanfoundation.app.insanparty.model.profile.MDProfile;
import rehanfoundation.app.insanparty.retrofitpkg.RetroServices;
import rehanfoundation.app.insanparty.retrofitpkg.RetrofitClientInstance;
import rehanfoundation.app.insanparty.user.UserEditProfileActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyActivity extends AppCompatActivity {

    ImageView backImg, imgEdit;
    TextView userName, gender, age, qualification, profession, etAddress, shadowMinister;
    Button btnLogout;
    CircleImageView profile_image;

    String user_id;
    private ProgressDialog dialog;

    String image = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        dialog = new ProgressDialog(MyActivity.this);
        dialog.setMessage("Please wait...");
        dialog.setCancelable(false);

        SharedPreferences prefs = getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE);
        user_id = prefs.getString("id", ""); //0 is the default value.

        backImg = findViewById(R.id.backImg);
        imgEdit = findViewById(R.id.imgEdit);
        btnLogout = findViewById(R.id.btnLogout);
        shadowMinister = findViewById(R.id.shadowMinister);
        etAddress = findViewById(R.id.etAddress);
        profession = findViewById(R.id.profession);
        qualification = findViewById(R.id.qualification);
        age = findViewById(R.id.age);
        gender = findViewById(R.id.gender);
        userName = findViewById(R.id.userName);
        profile_image = findViewById(R.id.profile_image);

        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finish();
            }
        });

        imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MyActivity.this, UserEditProfileActivity.class)
                        .putExtra("image", image));
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE).edit();
                editor.clear();
                editor.apply();
                startActivity(new Intent(MyActivity.this, MainActivity.class));
                finishAffinity();
            }
        });

        if (!user_id.equals("")){
//            Toast.makeText(this, ""+user_id, Toast.LENGTH_SHORT).show();
            getData();
        }
    }

    private void getData() {
        dialog.show();
        RetroServices service = RetrofitClientInstance.getApiClient().create(RetroServices.class);
        Call<MDProfile> call = service.profile(user_id);
        call.enqueue(new Callback<MDProfile>() {
            @Override
            public void onResponse(Call<MDProfile> call, Response<MDProfile> response) {
                if (response.isSuccessful()){
                    MDProfile mdCourses = response.body();
                    boolean status = mdCourses.getStatus();

                    dialog.dismiss();

                    if (status == true) {
                        if (mdCourses.getUser().getProfile() != null){
                            image = mdCourses.getUser().getImage();

                            Picasso.with(MyActivity.this)
                                    .load(UtilFunctions.urlUser + mdCourses.getUser().getImage())
                                    .into(profile_image);

                            shadowMinister.setText(mdCourses.getUser().getEmail());
                            userName.setText(mdCourses.getUser().getName());
                            gender.setText(mdCourses.getUser().getProfile().getGender());
                            age.setText(mdCourses.getUser().getProfile().getDob());
                            qualification.setText(mdCourses.getUser().getProfile().getEducation());
                            profession.setText(mdCourses.getUser().getProfile().getProfession());
//                            etAddress.setText(mdCourses.getUser().getProfile().get);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<MDProfile> call, Throwable t) {
                dialog.dismiss();
            }
        });
    }
}