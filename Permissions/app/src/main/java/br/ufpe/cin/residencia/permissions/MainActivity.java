package br.ufpe.cin.residencia.permissions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final int CAMERA_PERMISSION_REQUEST_CODE = 1;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 2;
    private static final int INTERNET_PERMISSION_REQUEST_CODE = 3;
    private static final int CONTACTS_PERMISSION_REQUEST_CODE = 4;
    private static final int STORAGE_PERMISSION_REQUEST_CODE = 5;
    private static final int CALENDAR_PERMISSION_REQUEST_CODE = 6;
    private static final int SMS_PERMISSION_REQUEST_CODE = 7;
    private static final int MICROPHONE_PERMISSION_REQUEST_CODE = 8;

    private Button btn_location;
    private Button btn_camera;
    private Button btn_internet;
    private Button btn_contacts;
    private Button btn_storage;
    private Button btn_calendar;
    private Button btn_sms;
    private Button btn_microphone;
    private TextView msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_location = findViewById(R.id.btn_location);
        btn_camera = findViewById(R.id.btn_camera);
        btn_internet = findViewById(R.id.btn_internet);
        btn_contacts = findViewById(R.id.btn_contacts);
        btn_storage = findViewById(R.id.btn_storage);
        btn_calendar = findViewById(R.id.btn_calendar);
        btn_sms = findViewById(R.id.btn_sms);
        btn_microphone = findViewById(R.id.btn_microphone);

        msg = findViewById(R.id.mensagem);

        btn_camera.setOnClickListener(v -> {
            checkAndRequestPermission(Manifest.permission.CAMERA, CAMERA_PERMISSION_REQUEST_CODE);
        });

        btn_location.setOnClickListener(v -> {
            checkAndRequestPermission(Manifest.permission.ACCESS_FINE_LOCATION, LOCATION_PERMISSION_REQUEST_CODE);
        });

        btn_internet.setOnClickListener(v -> {
            checkAndRequestPermission(Manifest.permission.INTERNET, INTERNET_PERMISSION_REQUEST_CODE);
        });

        btn_contacts.setOnClickListener(v -> {
            checkAndRequestPermission(Manifest.permission.READ_CONTACTS, CONTACTS_PERMISSION_REQUEST_CODE);
        });

        btn_storage.setOnClickListener(v -> {
            checkAndRequestPermission(Manifest.permission.READ_EXTERNAL_STORAGE, STORAGE_PERMISSION_REQUEST_CODE);
        });

        btn_calendar.setOnClickListener(v -> {
            checkAndRequestPermission(Manifest.permission.READ_CALENDAR, CALENDAR_PERMISSION_REQUEST_CODE);
        });

        btn_sms.setOnClickListener(v -> {
            checkAndRequestPermission(Manifest.permission.READ_SMS, SMS_PERMISSION_REQUEST_CODE);
        });

        btn_microphone.setOnClickListener(v -> {
            checkAndRequestPermission(Manifest.permission.RECORD_AUDIO, MICROPHONE_PERMISSION_REQUEST_CODE);
        });
    }

    private void checkAndRequestPermission(String permission, int requestCode) {
        // Check if the permission is granted or not.
        if (ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED) {
            // The permission is already granted.
            showToast("Permission already granted: " + permission);
        } else {
            // The permission is not granted. Request it.
            ActivityCompat.requestPermissions(this, new String[]{permission}, requestCode);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showToast("Permission granted: " + permissions[0]);
            } else {
                showToast("Permission denied: " + permissions[0]);
            }
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
