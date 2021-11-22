package y2019.aoc.alaa.myapplication;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.MediaStore;

public class MusicService extends Service {
    MediaPlayer mediaPlayer;

    public MusicService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    // the method is called when the service is started.
    @Override
    public void onCreate() {
        super.onCreate();
        //load the music file.
        mediaPlayer = MediaPlayer.create(this, R.raw.carlook);
        mediaPlayer.setLooping(true);// set the music file to loop.
        mediaPlayer.setVolume(100, 100);
    }
    // when service is started.
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mediaPlayer.start();//starts the music.
        return super.onStartCommand(intent, flags, startId);
    }
    // when the service is destroyed to stop playing the music.
    @Override
    public void onDestroy() {
        mediaPlayer.stop();
    }
}