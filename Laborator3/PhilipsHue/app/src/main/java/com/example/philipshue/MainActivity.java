package com.example.philipshue;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private static final String CHANNEL_ID = "auto_mode_channel";
    private static final int NOTIFICATION_ID = 1;
    private static final int REQUEST_CODE_POST_NOTIFICATIONS = 101;
    private View background;
    private Button toggleButton;
    private Switch autoModeSwitch;
    private Button customModeButton;
    private TextView statusText;
    private boolean isLightOn = false;
    private boolean isAutoMode = false;
    private Handler autoModeHandler = new Handler();
    private String lastAutoMode = "";
    private Runnable autoModeRunnable = new Runnable() {
        @Override
        public void run() {
            updateAutoMode();
            autoModeHandler.postDelayed(this, 5000);
        }
    };
    private int customColorIndex = 0;
    private int[] customColors = {Color.WHITE, Color.BLUE, Color.RED, Color.GREEN};
    private String[] colorNames = {"White", "Blue", "Red", "Green"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        background = findViewById(R.id.main);
        toggleButton = findViewById(R.id.toggleButton);
        autoModeSwitch = findViewById(R.id.autoModeSwitch);
        customModeButton = findViewById(R.id.customModeButton);
        statusText = findViewById(R.id.statusText);
        toggleButton.setOnClickListener(v -> {
            if (!isAutoMode) {
                isLightOn = !isLightOn;
                updateManualMode();
            }
        });
        autoModeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            isAutoMode = isChecked;
            updateAutoMode();
        });
        customModeButton.setOnClickListener(v -> {
            if (!isAutoMode) {
                customColorIndex = (customColorIndex + 1) % customColors.length;
                isLightOn = true;
                background.setBackgroundColor(customColors[customColorIndex]);
                statusText.setText("Custom Mode: " + colorNames[customColorIndex]);
                toggleButton.setText("Turn Off");
                statusText.setTextColor(customColorIndex == 0 ? Color.BLACK : Color.WHITE);
            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.POST_NOTIFICATIONS},
                        REQUEST_CODE_POST_NOTIFICATIONS);
            }
        }
        createNotificationChannel();
        updateManualMode();
    }

    private void updateManualMode() {
        if (isLightOn) {
            background.setBackgroundColor(Color.YELLOW);
            statusText.setText("Light is ON (Manual Mode)");
            statusText.setTextColor(Color.BLACK);
            toggleButton.setText("Turn Off");
        } else {
            background.setBackgroundColor(Color.DKGRAY);
            statusText.setText("Light is OFF (Manual Mode)");
            statusText.setTextColor(Color.WHITE);
            toggleButton.setText("Turn On");
        }
    }

    private void updateAutoMode() {
        if (!isAutoMode) {
            autoModeHandler.removeCallbacks(autoModeRunnable);
            background.setBackgroundColor(Color.DKGRAY);
            statusText.setText("Auto Mode Disabled");
            statusText.setTextColor(Color.WHITE);
            lastAutoMode = "";
            return;
        }
        autoModeHandler.post(autoModeRunnable);
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        Log.d("MainActivity", "Current hour: " + hour);
        String currentMode;
        if (hour >= 7 && hour < 18) {
            background.setBackgroundColor(Color.WHITE);
            statusText.setText("Morning Mode: Light is WHITE");
            statusText.setTextColor(Color.BLACK);
            currentMode = "Morning Mode: Light is WHITE";
        } else if (hour >= 18 && hour < 22) {
            background.setBackgroundColor(Color.parseColor("#FFA500"));
            statusText.setText("Evening Mode: Light is ORANGE");
            statusText.setTextColor(Color.BLACK);
            currentMode = "Evening Mode: Light is ORANGE";
        } else {
            background.setBackgroundColor(Color.DKGRAY);
            statusText.setText("Night Mode: Light is OFF");
            statusText.setTextColor(Color.WHITE);
            currentMode = "Night Mode: Light is OFF";
        }
        if (!currentMode.equals(lastAutoMode)) {
            if (currentMode.contains("Night Mode")) {
                sendNotification("Good night! The light has turned off automatically.");
            } else if (currentMode.contains("Morning Mode")) {
                sendNotification("Good morning! Do you need more light?");
            } else {
                sendNotification(currentMode);
            }
            lastAutoMode = currentMode;
        }
    }

    private void sendNotification(String message) {
        Log.d("MainActivity", "Sending notification: " + message);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("Auto Mode Update")
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager != null) {
            notificationManager.notify(NOTIFICATION_ID, builder.build());
        }
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Auto Mode Channel";
            String description = "Channel for auto mode notifications";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        autoModeHandler.removeCallbacks(autoModeRunnable);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_POST_NOTIFICATIONS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d("MainActivity", "POST_NOTIFICATIONS permission granted");
            } else {
                Log.d("MainActivity", "POST_NOTIFICATIONS permission denied");
            }
        }
    }
}
