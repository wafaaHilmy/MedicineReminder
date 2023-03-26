package com.example.medicinereminder.extras;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.work.Data;
import androidx.work.ListenableWorker;
import androidx.work.ListenableWorker.Result;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.medicinereminder.Application.AppApplication;
import com.example.medicinereminder.MainActivity;
import com.example.medicinereminder.R;
import com.example.medicinereminder.database.Medicine;

public class AlarmWork extends Worker {

    MediaPlayer mp;
    Context context;

    Data inputData =getInputData();

    public AlarmWork(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        this.context=  context;
    }

    @NonNull
    @Override
    public Result doWork() {

/*
        AlarmManager alarmManager=context.getSystemService(AlarmManager.class);
        Intent receiverIntent=new Intent(context,AlarmBroadcastReceiver.class);
        PendingIntent receiverPendingIntent=PendingIntent.getBroadcast(context,012,receiverIntent,0);
        alarmManager.set(AlarmManager.RTC_WAKEUP,System.currentTimeMillis(),receiverPendingIntent);
        */


        mp= MediaPlayer.create(context, R.raw.chooka_woo_woo);
        mp.start();

        SendNotification();

        Log.d(AlarmWork.class.getSimpleName(), "doWork: start and set alarm "
        +inputData.getString(Medicine.TREATMENT_NAME)+" / "
                +inputData.getInt(Medicine.DOSE_AMOUNT,0)+" "
                +inputData.getString(Medicine.TREATMENT_TYPE));



        return Result.success();
    }
/*---------------------------------------------------------------------------------------------------------------*/
    private void SendNotification() {
        int notificationId=12;

        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE);


        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,AppApplication.CHANNEL_ID)
               .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(inputData.getString(Medicine.TREATMENT_NAME)+" dose is now")
                .setContentText("Time of your medicine ,please take  "+inputData.getInt(Medicine.DOSE_AMOUNT,0)+" "
                        +inputData.getString(Medicine.TREATMENT_TYPE))

                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Much longer text that cannot fit one line..."))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

// notificationId is a unique int for each notification that you must define
        notificationManager.notify(notificationId, builder.build());




    }


}
