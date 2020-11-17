package rehanfoundation.app.insanparty.user;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.annotation.Nullable;



import java.io.IOException;

import rehanfoundation.app.insanparty.R;

public class UserRegisterActivity extends Activity {

    private EditText name,email,password,phone,age,education,profession,location;
    private RadioGroup gender;
    private ProgressDialog progressDialog;
    private Button register,upload;
    private RadioButton Worktime,Task;
    private ImageView image;
    private static final int PICK_IMAGE = 1;
    Uri imageUri;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userregister);

        progressDialog = new ProgressDialog(this);
        //Edit Texts
        email = (EditText)findViewById(R.id.email);
        name = (EditText)findViewById(R.id.name);
        password = (EditText)findViewById(R.id.password);
        phone = (EditText)findViewById(R.id.phone);
        age = (EditText)findViewById(R.id.age);
        education = (EditText)findViewById(R.id.education);
        profession = (EditText)findViewById(R.id.profession);
        location = (EditText)findViewById(R.id.location);
        //Radio Groups
        gender = (RadioGroup)findViewById(R.id.radioGender);

        //Button
        register = (Button)findViewById(R.id.register);
        upload = (Button)findViewById(R.id.upload);
        // Image Views

        image = (ImageView)findViewById(R.id.image);

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery = new Intent();
                gallery.setType("image/*");
                gallery.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(gallery,"Select Picture"),PICK_IMAGE);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==PICK_IMAGE &&  resultCode==RESULT_OK && data!=null){
            imageUri = data.getData();

            try{
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),imageUri);
                image.setImageBitmap(bitmap);

            }catch (IOException e){
                e.printStackTrace();
            }
        }



    }
}
