package com.example.speech_rec;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Date;
import java.text.SimpleDateFormat;





import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Sendimage extends Activity {

	Button b1, b2, b3;
	ImageView viewImage;
	String res;
	String filenameuploadPath;
	private String url = "http://192.168.43.114:8084/Speech_Recognization/RecieveAudio";
	String uploadFileName;
	boolean finalresult=false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sendimage);
		b1 = (Button) findViewById(R.id.upload2);
		b2 = (Button) findViewById(R.id.button1);
		viewImage = (ImageView) findViewById(R.id.viewImage1);
		
	
		
		b1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
		
			
				selectImage();
			}
		});
		
		b2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
			
				if(!TextUtils.isEmpty(filenameuploadPath)){
		    		SendHttpRequestTask t = new SendHttpRequestTask();
			        String[] params = new String[] { url };
			        t.execute(params);
		    	}else{
		    		Toast.makeText(getApplicationContext(), "Please choose photo",
		                    Toast.LENGTH_SHORT).show();
		    	}
				// TODO Auto-generated method stub
				
			}
		});

	}

	private void selectImage() {
        final CharSequence[] options = { "Gallery","Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(Sendimage.this);
        builder.setTitle("Add Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Camera")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File f = new File(android.os.Environment.getExternalStorageDirectory(), createCameraImageFileName());
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                    startActivityForResult(intent, 1);
                } else if (options[item].equals("Gallery")) {
                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 2);

                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }

            private String createCameraImageFileName() {

                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date(0));
                res = timeStamp + ".jpg";
                return res;
            }
        });
        builder.show();


}
	private class SendHttpRequestTask extends AsyncTask<String, Void , String>
    {

	@Override
	protected String doInBackground(String... params) {
		String url = params[0];


        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        File root = android.os.Environment.getExternalStorageDirectory();

        File file = new File(filenameuploadPath);

//        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), filename);
        try {
            FileInputStream fns = new FileInputStream(file);

            byte[] buf = new byte[500000];

            for (int readNum; (readNum = fns.read(buf)) != -1;) {
                bos.write(buf, 0, readNum);
            }
        } catch (IOException ex) {

        }
        byte[] dd = bos.toByteArray();
        
        try {
            HttpClient client = new HttpClient(url);
            client.connectForMultipart();	                
            client.addFormPart("userid",Property.id);
          
            client.addFilePart("file", uploadFileName , dd);
            client.finishMultipart();
            String data = client.getResponse();

        } catch (Throwable t) {
            t.printStackTrace();
        }

        return null;
	}
	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		 Toast.makeText(getApplicationContext(), "sent success",
                    Toast.LENGTH_SHORT).show();
//		 Intent intent = new Intent(Sendimage.this , Sendimage.class);
//         startActivity(intent);

	}
}
   
   
   
   @Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	// TODO Auto-generated method stub
	super.onActivityResult(requestCode, resultCode, data);
	 File picturefile = getOutputMediaFile();
        File filename = picturefile;
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                File f = new File(Environment.getExternalStorageDirectory().toString());
                for (File temp : f.listFiles()) {
                    if (temp.getName().equals("res")) {
                        f = temp;
                        break;
                    }
                }
                try {
                    Bitmap bitmap;
                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();

                    bitmap = BitmapFactory.decodeFile(f.getAbsolutePath(),
                            bitmapOptions);

                    viewImage.setImageBitmap(bitmap);

                    String path = android.os.Environment
                            .getExternalStorageDirectory()
                            + File.separator
                            + "Phoenix" + File.separator + "default";
                    f.delete();
                    OutputStream outFile = null;
                    File file = new File(path, String.valueOf(System.currentTimeMillis()) + ".jpg");
                    try {
                        outFile = new FileOutputStream(file);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 85, outFile);
                        outFile.flush();
                        outFile.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (requestCode == 2) {

                Uri selectedImage = data.getData();
                String[] filePath = { MediaStore.Images.Media.DATA };
                Cursor c = getContentResolver().query(selectedImage, filePath, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                String picturePath = c.getString(columnIndex);

                c.close();
                Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));
                Log.w("path ......", picturePath);
                filenameuploadPath = picturePath;



                viewImage.setImageBitmap(thumbnail);
            }
        }
    }

    private File getOutputMediaFile() {

        File mediaStorageDir = new File("/sdcard/DCIM");

        //if this "JCGCamera folder does not exist
        if (!mediaStorageDir.exists()) {
            //if you cannot make this folder return
            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        }
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date(0));
        File mediaFile;
        //and make a media file:
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_" + timeStamp + ".jpg");
        uploadFileName = "IMG_" + timeStamp + ".jpg";
        return mediaFile;
    }
}

