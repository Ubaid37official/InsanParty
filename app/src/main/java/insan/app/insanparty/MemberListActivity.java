package rehanfoundation.app.insanparty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

import rehanfoundation.app.insanparty.adapter.AdapterMemberList;
import rehanfoundation.app.insanparty.model.memberlist.MDMember;
import rehanfoundation.app.insanparty.model.memberlist.MemberList;
import rehanfoundation.app.insanparty.retrofitpkg.RetroServices;
import rehanfoundation.app.insanparty.retrofitpkg.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MemberListActivity extends AppCompatActivity {

    RecyclerView recycler;
    private ProgressDialog dialog;
    ImageView profile_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_list);

        dialog = new ProgressDialog(MemberListActivity.this);
        dialog.setMessage("Please wait...");
        dialog.setCancelable(false);
        dialog.show();

        recycler = findViewById(R.id.recycler);
        profile_image = findViewById(R.id.profile_image);

        profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MemberListActivity.this, MyActivity.class));
            }
        });

        getData();
    }

    private void getData() {
        final ArrayList<MemberList> data = new ArrayList<>();
        final AdapterMemberList adapter = new AdapterMemberList(MemberListActivity.this, data);
        recycler.setLayoutManager(new GridLayoutManager(MemberListActivity.this, 2));
        recycler.setItemAnimator(new DefaultItemAnimator());
        recycler.setAdapter(adapter);

        adapter.setOnClickListener(new AdapterMemberList.OnClickLitener() {
            @Override
            public void onClick(final int position, MemberList post) {
                startActivity(new Intent(MemberListActivity.this, MemberListProfileActivity.class)
                        .putExtra("user_id", post.getId()+""));
            }
        });


        RetroServices service = RetrofitClientInstance.getApiClient().create(RetroServices.class);
        Call<MDMember> call = service.memberList();
        call.enqueue(new Callback<MDMember>() {
            @Override
            public void onResponse(Call<MDMember> call, Response<MDMember> response) {
                if (response.isSuccessful()){
                    dialog.dismiss();
                    MDMember mdCourses = response.body();
                    boolean status = mdCourses.getStatus();

                    if (status == true) {
                        data.addAll(mdCourses.getMembers());

                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<MDMember> call, Throwable t) {
                dialog.dismiss();
            }
        });
    }
}