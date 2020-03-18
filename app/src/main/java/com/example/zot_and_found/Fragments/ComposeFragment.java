package com.example.zot_and_found.Fragments;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;


import com.example.zot_and_found.Models.Post;
import com.example.zot_and_found.Models.User;
import com.example.zot_and_found.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class ComposeFragment extends Fragment {


    private final String TAG = "ComposeFragment";
    private EditText etDescription;
    private Button btnCatptureImage;
    private ImageView ivPostImage;
    private EditText etName;
    private EditText etQuestion;

    private String key;

    private Button btnSubmit;

    FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    private StorageReference storageRef = storage.getReferenceFromUrl("gs://zot-and-found.appspot.com");

    private StorageReference imageRef = storageRef.child("images/tester.jpg");

    public final static int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1034;
    public String photoFileName = "photo.jpg";
    private File photoFile;
    private Bitmap targetImageBM;

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("user");


    public ComposeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_compose, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etDescription = view.findViewById(R.id.etDescription);
        btnCatptureImage = view.findViewById(R.id.btnCaptureImage);
        ivPostImage = view.findViewById(R.id.ivPostImage);
        btnSubmit = view.findViewById(R.id.btnSubmit);
        etName = view.findViewById(R.id.etName);
        etQuestion = view.findViewById(R.id.etQuestion);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String description = etDescription.getText().toString();
                String name = etName.getText().toString();
                String question = etQuestion.getText().toString();

                if(photoFile == null || ivPostImage.getDrawable() == null)
                {
                    Toast.makeText(getContext(), "better take a photo first", Toast.LENGTH_SHORT);
                    return;
                }
                savePost(description, question, name); // also save to my post
            }
        });

        btnCatptureImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchCamera();
            }
        });
    }



    private void savePost(String description, String question, String name) // user
    {
        //I have no idea how to save image to cloud firestore
        //Right now my solution is uploading the image to storage and then save the reference to my posts
        //Update to how I'm saving the reference, I'm just saving the path as a String

        //uploads the image and save the name.
        String imageName = uploadImage(targetImageBM);

        //I was gonna use map, but apprently firebase can convert objects directly
        //Map<String, Object> docData = new HashMap<>();
        Post newPost = new Post(description, question, name, imageRef.getPath());

        //I read up on the firebase documents, but everything keeps referencing a "db", I'm assuming it's an instance of firestore
        firestore.collection("posts")
                .add(newPost)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                        Log.d(TAG, "DocumentSnapshot written with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });

        FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
        String emailName = mFirebaseUser.getEmail();

        //ArrayList<Post> posts = new ArrayList<>();
        //posts.add(newPost);
        //User user = new User(emailName,posts);

        firestore.collection(emailName)
                .add(newPost)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                        Log.d(TAG, "DocumentSnapshot written with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });

        Toast.makeText(getContext(), "Upload successfully", Toast.LENGTH_SHORT).show();

        etDescription.setText("");
        ivPostImage.setImageResource(android.R.color.transparent);
        etName.setText("");
        etQuestion.setText("");


    }

    private void launchCamera()
    {
        // create Intent to take a picture and return control to the calling application
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        // Create a File reference to access to future access
        photoFile = getPhotoFileUri(photoFileName);

        // wrap File object into a content provider
        // required for API >= 24
        // See https://guides.codepath.com/android/Sharing-Content-with-Intents#sharing-files-with-api-24-or-higher
        Uri fileProvider = FileProvider.getUriForFile(getContext(), "com.codepathgroupsmal.fileprovider", photoFile);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileProvider);

        // If you call startActivityForResult() using an intent that no app can handle, your app will crash.
        // So as long as the result is not null, it's safe to use the intent.
        if (intent.resolveActivity(getContext().getPackageManager()) != null) {
            // Start the image capture intent to take photo
            startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
        }
    }

    // Returns the File for a photo stored on disk given the fileName
    public File getPhotoFileUri(String fileName) {
        // Get safe storage directory for photos
        // Use `getExternalFilesDir` on Context to access package-specific directories.
        // This way, we don't need to request external read/write runtime permissions.
        File mediaStorageDir = new File(getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES), TAG);

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists() && !mediaStorageDir.mkdirs()){
            Log.d(TAG, "failed to create directory");
        }

        // Return the file target for the photo based on filename
        File file = new File(mediaStorageDir.getPath() + File.separator + fileName);

        return file;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // by this point we have the camera photo on disk
                targetImageBM = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
                // RESIZE BITMAP, see section below
                // Load the taken image into a preview\
                ivPostImage.setImageBitmap(targetImageBM);

            } else { // Result was a failure
                Toast.makeText(getContext(), "Picture wasn't taken!", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private String uploadImage(Bitmap image)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] bytes = baos.toByteArray();
        String randomName = UUID.randomUUID().toString();

        //Generate random UUID for the image
        imageRef = storageRef.child("images/" + randomName + ".jpg");
        Log.i(TAG, "path of imageRef is " + imageRef.getPath());

        UploadTask uploadTask = imageRef.putBytes(bytes);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Log.i(TAG, "successfulUpload");
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                //Uri downloadUrl = taskSnapshot.getDownloadUrl();
            }
        });
        return randomName;
    }

}
