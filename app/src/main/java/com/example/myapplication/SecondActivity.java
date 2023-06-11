package com.example.myapplication;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import com.example.myapplication.databinding.ActivitySecondBinding;

public class SecondActivity extends AppCompatActivity {

    ActivitySecondBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySecondBinding.inflate(getLayoutInflater());
        // Loads the XML file /res/layout/activity_second.xml
        setContentView(binding.getRoot());

        Intent nextPage = getIntent();  //return the Intent that got us here
        //should be variables in nextPage:

        //null if EMAIL IS NOT FOUND
        String EMAIL = nextPage.getStringExtra("EMAIL"); //this will get the value of email
        String DAY = nextPage.getStringExtra("DAY");
        int AGE = nextPage.getIntExtra("AGE", 0);


        //int something = nextPage.getIntExtra("something", 0);


        binding.textView4.setText("You Passed " + EMAIL + " and " + DAY + " and: " + AGE);

        binding.goBackButton.setOnClickListener((v) ->
        {

            Intent intent = new Intent(Intent.ACTION_DIAL);
            nextPage.putExtra("EmailAddress", binding.editTextPhone.getText().toString());
            String whatIsTyped = binding.editTextPhone.getText().toString();
            intent.setData(Uri.parse("tel: " + whatIsTyped));
            startActivity(intent);

        });


        ActivityResultLauncher<Intent> cameraResult = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),

                new ActivityResultCallback<ActivityResult>() {

                    @Override

                    public void onActivityResult(ActivityResult result) {

                        if (result.getResultCode() == Activity.RESULT_OK) {

                            Intent data = result.getData();
                        Bitmap thumbnail = data.getParcelableExtra("data");
                            binding.imageView.setImageBitmap(thumbnail);

                        }
                        ;
                    }
                });

        binding.button.setOnClickListener(cl -> {


            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            if (checkSelfPermission(android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED)
                cameraResult.launch(cameraIntent);
            else
                requestPermissions(new String[]{Manifest.permission.CAMERA}, 20);


//        binding.goBackButton.setOnClickListener((v) ->
//        {
//            //opposite of startActivity();
//            finish(); //go back to previous
        });
    }
}


