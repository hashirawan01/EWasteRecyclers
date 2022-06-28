package com.example.ewasterecyclers;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Models.CustomerDataModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class uploadsImageActivity extends AppCompatActivity {
    TextView timage;
    EditText ename, ephonenumber, eaddress;
    ImageView imageView, showimage;
    Button submitBtn;
    Uri filepath;
    String address;
    String text;
    String phoonenumber;
    Bitmap bitmap;
    ProgressDialog progressDialog;
    FirebaseStorage storage = FirebaseStorage.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploads_image);
        getSupportActionBar().hide();
        ename = findViewById(R.id.idNAme);
        progressDialog = new ProgressDialog(this);
        ephonenumber = findViewById(R.id.idPhoonenumber);
        eaddress = findViewById(R.id.idAddress);
        showimage = findViewById(R.id.showimage);
        timage = findViewById(R.id.idBrowseimage);
        imageView = findViewById(R.id.my_avatar);
        submitBtn = findViewById(R.id.submitBotton);
        Bitmap image = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        timage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getIamgeFromCamera();
            }
        });
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDataFromEditTexts();
                uploadimagetofirebase();


            }
        });


    }

    ///get data from edit texts and uploads users data to the firebase
    private void getDataFromEditTexts() {
        text = ename.getText().toString().trim();
        phoonenumber = ephonenumber.getText().toString().trim();
        address= eaddress.getText().toString().trim();
        ename.setText("");
        ephonenumber.setText("");
        eaddress.setText("");
        Toast.makeText(uploadsImageActivity.this, "Data is inserted Successfully", Toast.LENGTH_SHORT).show();

    }

    //////to show the pop up for selection of camera and select from gallery this function is used////
    private void getIamgeFromCamera() {
        PopupMenu popup = new PopupMenu(uploadsImageActivity.this, timage);
        popup.getMenuInflater().inflate(R.menu.threemenue, popup.getMenu());

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.menu_camera) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, 101);
                    Toast.makeText(uploadsImageActivity.this, "you click on select from camera", Toast.LENGTH_SHORT).show();

                } else if (id == R.id.menu_Gallery) {
                    Dexter.withActivity(uploadsImageActivity.this)
                            .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                            .withListener(new PermissionListener() {
                                @Override
                                public void onPermissionGranted(PermissionGrantedResponse response) {
                                    Intent intent = new Intent(Intent.ACTION_PICK);
                                    intent.setType("image/*");
                                    startActivityForResult(Intent.createChooser(intent, "Please select Image"), 1234);

                                }

                                @Override
                                public void onPermissionDenied(PermissionDeniedResponse response) {

                                }

                                @Override
                                public void onPermissionRationaleShouldBeShown(com.karumi.dexter.listener.PermissionRequest permission, PermissionToken token) {
                                    token.continuePermissionRequest();
                                }

                            }).check();


                } else {

                }
                return true;
            }
        });
        popup.show();

    }


    ////override on activity result to perform ooperation like image saving and geting path by using different function///
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //for show path of camera and click photo from camera
        InputStream inputStream = null;
        if (requestCode == 101) {
            Bitmap bitmap1 = (Bitmap) data.getExtras().get("data");
           filepath = getImageUri(getApplicationContext(), bitmap1);
            imageView.setImageBitmap(bitmap1);
            // CALL THIS METHOD TO GET THE ACTUAL PATh
            Toast.makeText(this, getRealPathFromURI(filepath), Toast.LENGTH_SHORT).show();

        }

        //for show path of gallery

        switch (requestCode) {
            case 1234:
                if (resultCode == RESULT_OK) {
                    filepath = data.getData();
                    try {
                        inputStream = getContentResolver().openInputStream(filepath);
                        bitmap = BitmapFactory.decodeStream(inputStream);
                        imageView.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};

                    Cursor cursor = getContentResolver().query(filepath, filePathColumn, null, null, null);
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String filePath = cursor.getString(columnIndex);
                    cursor.close();
                    Toast.makeText(this, filePath, Toast.LENGTH_SHORT).show();

                }
        }
    }
//        }


    //to convert the image to uri format this function is used
    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    //to get the Path of Image this function is used
    public String getRealPathFromURI(Uri uri) {
        String path = "";
        if (getContentResolver() != null) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                path = cursor.getString(idx);
                cursor.close();
            }
        }
        return path;
    }

    //to upload image to firebase cloud storage
    private void uploadimagetofirebase() {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setTitle("File Uploader");
        dialog.show();

///for updation of name of every file with a specific name use current time millsmethod
        StorageReference uploader = storage.getReference().child(System.currentTimeMillis()+".png");
        uploader.putFile(filepath)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        dialog.dismiss();
                        Toast.makeText(getApplicationContext(), "File Uploaded", Toast.LENGTH_LONG).show();
                        uploader.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                String imageUri=uri.toString();
                                CustomerDataModel customerDataModel = new CustomerDataModel(text, phoonenumber, address,imageUri);
                                FirebaseDatabase db = FirebaseDatabase.getInstance();
                                DatabaseReference node = db.getReference("Customer");
                                node.child(text).setValue(customerDataModel);
                            }
                        });
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        float percent = (100 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                        dialog.setMessage("Uploaded :" + (int) percent + " %");
                    }
                });
    }
}




