package edu.csulb.android.photonotes;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewPhotoActivity extends Activity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.viewphotoactivity);
        Intent receiverIntent = getIntent();
        TextView txt =(TextView)findViewById(R.id.txtview);
        String caption=receiverIntent.getStringExtra("caption");
        txt.setText(caption);
        fetchAndDisplayImage(caption);
    }

    private void fetchAndDisplayImage(String caption) {
        helperDB hdb = new helperDB(ListActivity.dbinit);
        String path = hdb.getImagePath(caption);
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        ImageView img = (ImageView)findViewById(R.id.img);
        img.setImageBitmap(bitmap);

    }

    @Override
    public void onBackPressed() {
        ActivityCompat.finishAfterTransition(this);
    }
}
