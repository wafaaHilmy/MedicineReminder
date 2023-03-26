package com.example.medicinereminder.extras;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

public class AlarmBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {


        Log.d(AlarmBroadcastReceiver.class.getSimpleName(), "onReceive:start alarm and launch AlarmDialog activity");

        if (android.os.Build.VERSION.SDK_INT >=  Build.VERSION_CODES.GINGERBREAD_MR1) {
            //higher than android 10 start notifications

        }/*else{
            //older version open alarm dialog activity
            Intent launchIntent = new Intent(context, AlarmDialogActivity.class);
           launchIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
           context.startActivity(launchIntent);
        }*/
    }
}
