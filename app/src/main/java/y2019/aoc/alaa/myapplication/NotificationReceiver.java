package y2019.aoc.alaa.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class NotificationReceiver extends BroadcastReceiver {
    // this class is initiated when broadcast is received from the OS(Operation System).
    @Override
    public void onReceive(Context context, Intent intent) {
    Intent intent1 = new Intent(context, NotificationIntentService.class);
    context.startService(intent1);
    }
}
