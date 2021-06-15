package app.autosilenceapp.serviceexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.util.Log;

public class MyReceiverexample extends BroadcastReceiver {

    @Override
    public void onReceive(Context arg0, Intent intent) {
        // TODO Auto-generated method stub
        AudioManager audio = (AudioManager)arg0.getSystemService(Context.AUDIO_SERVICE);
        switch(audio.getRingerMode()){
            case AudioManager.RINGER_MODE_NORMAL :
                audio.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                Log.d("MODE", "was normal");
                Log.d("MODE", "is silent");
                break;

            case AudioManager.RINGER_MODE_SILENT :
                audio.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                Log.d("MODE", "was silent");
                Log.d("MODE", "is vibrate");
                break;
        }
     /*   switch(audio.getRingerMode()){
            case AudioManager.RINGER_MODE_NORMAL :
                audio.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
                Log.d("MODE", "was normal");
                Log.d("MODE", "is vibrate");
                break;

            case AudioManager.RINGER_MODE_SILENT :
                audio.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
                Log.d("MODE", "was silent");
                Log.d("MODE", "is vibrate");
                break;
        }*/
    }}