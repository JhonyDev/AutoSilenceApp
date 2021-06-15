package app.autosilenceapp.serviceexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.util.Log;

public class MyRecieverexample2 extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        AudioManager audio=(AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
        // TODO Auto-generated method stub
        switch(audio.getRingerMode()){
            case AudioManager.RINGER_MODE_NORMAL:
                audio.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                Log.d("MODE", "was normal 2");
                Log.d("MODE", "is silent 2");
                break;

            case AudioManager.RINGER_MODE_VIBRATE :
                audio.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                Log.d("MODE", "was vibrate 2");
                Log.d("MODE", "is silent 2");
                break;
        }

       /* switch(audio.getRingerMode()){
            case AudioManager.RINGER_MODE_NORMAL :
                audio.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                Log.d("MODE", "was normal");
                Log.d("MODE", "is silent");
                break;

            case AudioManager.RINGER_MODE_VIBRATE :
        audio.setRingerMode(AudioManager.RINGER_MODE_SILENT);
        Log.d("MODE", "was vibrate");
        Log.d("MODE", "is silent");
        break;
    }*/
}
}