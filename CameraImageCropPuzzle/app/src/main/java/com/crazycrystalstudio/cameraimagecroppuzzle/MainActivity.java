package com.crazycrystalstudio.cameraimagecroppuzzle;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import static android.R.attr.data;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btn;
    ImageView iv;
    Uri uri;

    public final int PIC_CROP = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.captureButton);
        iv = (ImageView) findViewById(R.id.imageView);

        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.captureButton){
            try {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 1);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            if(requestCode == 1) {
                uri = data.getData();
                performCrop(uri);
            }else if (requestCode == 2){
                try {
                    Bundle bundle = data.getExtras();
                    Bitmap bitmap = bundle.getParcelable("data");

                    iv.setImageBitmap(bitmap);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

        }
    }

    public void performCrop(Uri uri){
        try{
            Intent intent = new Intent("com.android.camera.action.CROP");
            intent.setDataAndType(uri, "image/*");
            intent.putExtra("crop", "true");
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 1);

            intent.putExtra("outputX", 256);
            intent.putExtra("outputY", 256);

            intent.putExtra("return-data", true);

            startActivityForResult(intent, PIC_CROP);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
