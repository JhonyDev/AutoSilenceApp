package app.autosilenceapp.serviceexample;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;

public class SetToNormalService extends IntentService {
    public SetToNormalService(String name)
    { super(name); }
   
    public SetToNormalService()
    { 
        super(null); 
    }
    @Override
    protected void onHandleIntent(Intent intent) {
        // set the phone ringer to normal
        AudioManager mAudioManager = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
        mAudioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
    }
}