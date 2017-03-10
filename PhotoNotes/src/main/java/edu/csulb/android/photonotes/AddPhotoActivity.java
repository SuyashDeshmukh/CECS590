package edu.csulb.android.photonotes;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddPhotoActivity extends Activity {

    private Button takephoto ;
    private Button save;
    private EditText caption;
    String currentPhotoPath=null;
    String captiontxt;
    static final int REQUEST_TAKE_PHOTO=1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.addphotoactivity);
        caption=(EditText)findViewById(R.id.captiontext);
        takephoto=(Button)findViewById(R.id.takephotobutton);
        takephoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takephotos();
            }
        });

        save=(Button)findViewById(R.id.savebutton);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                captiontxt= caption.getText().toString();
                saveFileAndCaption();
                finish();
            }
        });



    }

    private void saveFileAndCaption() {

        helperDB dbhelp = new helperDB(ListActivity.dbinit);
        dbhelp.insertData(captiontxt,currentPhotoPath);

    }

    private void takephotos() {
        Intent photointent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(photointent.resolveActivity(getPackageManager())!=null)
        {
            File myfile =null;
            try{
                myfile=createFile();

            }catch (IOException E)
            {
                Log.d("EX","IOE");
            }
            if(myfile!=null)
            {
                Uri photoURI = FileProvider.getUriForFile(AddPhotoActivity.this,
                        BuildConfig.APPLICATION_ID + ".provider",
                        myfile);
               photointent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(photointent,REQUEST_TAKE_PHOTO);

            }
        }

    }

    private File createFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        Log.d("filename",imageFileName);
        File saveDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image=File.createTempFile(imageFileName,".jpg",saveDir);
        currentPhotoPath=image.getAbsolutePath();
        return image;

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(currentPhotoPath!=null)
        {
            File file =new File(currentPhotoPath);
            file.delete();
        }
    }
}
