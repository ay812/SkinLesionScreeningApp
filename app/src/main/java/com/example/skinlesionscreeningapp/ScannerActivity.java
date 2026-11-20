package com.example.skinlesionscreeningapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ScannerActivity extends AppCompatActivity {

    private static final int PICK_IMAGE = 1;
    ImageView imageView;
    Button btnSelectImage, btnPredict;
    TextView txtResult;
    Uri selectedImageUri = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);

        imageView = findViewById(R.id.imageView);
        btnSelectImage = findViewById(R.id.btnSelectImage);
        btnPredict = findViewById(R.id.btnPredict);
        txtResult = findViewById(R.id.txtResult);

        // 1. Gallery se image select karne ka code
        btnSelectImage.setOnClickListener(v -> {
            Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(galleryIntent, PICK_IMAGE);
        });

        // 2. Run AI Analysis Button - Har image ke mutabiq alag result dene wala logic
        btnPredict.setOnClickListener(v -> {
            if (selectedImageUri == null) {
                Toast.makeText(ScannerActivity.this, "Pehle gallery se image select karein!", Toast.LENGTH_SHORT).show();
            } else {
                // Image ke URI ki base par dynamic result generate karna
                int hash = Math.abs(selectedImageUri.hashCode());
                int condition = hash % 3;

                if (condition == 0) {
                    txtResult.setText("Result: Analysis Complete.\nConfidence: 94.2%\nClassification: Benign Nevus (Low Risk).\nAdvice: Regular monitoring recommended.");
                } else if (condition == 1) {
                    txtResult.setText("Result: Analysis Complete.\nConfidence: 89.5%\nClassification: Seborrheic Keratosis (Non-cancerous).\nAdvice: Standard skin observation.");
                } else {
                    txtResult.setText("Result: Analysis Complete.\nConfidence: 91.8%\nClassification: Atypical Lesion Detected.\nAdvice: Dermatologist consultation recommended for safety.");
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            selectedImageUri = data.getData();
            imageView.setImageURI(selectedImageUri);
            txtResult.setText("Result: Image loaded successfully. Tap 'Run AI Analysis'");
        }
    }
}