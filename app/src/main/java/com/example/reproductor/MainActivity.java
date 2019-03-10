package com.example.reproductor;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    Button play_pause, btn_repetir;
    MediaPlayer mp;
    ImageView iv;
    int repetir = 2, posicion = 0;

    MediaPlayer vectormp[] = new MediaPlayer[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        play_pause = (Button) findViewById(R.id.btn_play);
        btn_repetir = (Button) findViewById(R.id.btnrepetir);
        iv = (ImageView) findViewById(R.id.imageView);

        vectormp[0] = MediaPlayer.create(this, R.raw.savethatshit);
        vectormp[1] = MediaPlayer.create(this, R.raw.thebrightside);


    }

    public void PlayPause(View view) {

        if (vectormp[posicion].isPlaying()) {
            vectormp[posicion].pause();
            play_pause.setBackgroundResource(R.drawable.reproducir);
            Toast.makeText(this, "pausa", Toast.LENGTH_SHORT).show();

        } else {
            vectormp[posicion].start();
            play_pause.setBackgroundResource(R.drawable.pausa);
            Toast.makeText(this, "play", Toast.LENGTH_SHORT).show();
        }
    }

    //metodo para repetir una pista
    public void Repetir(View view) {
        if (repetir == 1) {
            btn_repetir.setBackgroundResource(R.drawable.no_repetir);
            Toast.makeText(this, "No repetir", Toast.LENGTH_SHORT).show();
            vectormp[posicion].setLooping(false);
            repetir = 2;
        } else {
            btn_repetir.setBackgroundResource(R.drawable.repetir);
            Toast.makeText(this, "Repetir", Toast.LENGTH_SHORT).show();
            vectormp[posicion].setLooping(true);
            repetir = 1;

        }
    }

    //metodo para pasar a la siguiente cancion
    public void Siguente(View view) {
        if (posicion < vectormp.length - 1) {

            if (vectormp[posicion].isPlaying()) {
                vectormp[posicion].stop();
                posicion++;
                vectormp[posicion].start();

                if (posicion==0){
                    iv.setImageResource(R.drawable.portada1);
                }else if (posicion==1) {
                    iv.setImageResource(R.drawable.portada2);
                }
            } else {
                posicion++;

                if (posicion==0){
                    iv.setImageResource(R.drawable.portada1);
                }else if (posicion==1) {
                    iv.setImageResource(R.drawable.portada2);
                }
            }


        } else {
            Toast.makeText(this, "No hay mas canciones", Toast.LENGTH_SHORT).show();
        }

    }

    public void atras (View view){
        if(posicion>=1){
            if(vectormp[posicion].isPlaying()){
                vectormp[posicion].stop();
                vectormp[0] = MediaPlayer.create(this, R.raw.savethatshit);
                vectormp[1] = MediaPlayer.create(this, R.raw.thebrightside);
                posicion--;

                if (posicion==0){
                    iv.setImageResource(R.drawable.portada1);
                }else if (posicion==1) {
                    iv.setImageResource(R.drawable.portada2);
                }

                vectormp[posicion].start();

            }else{
                posicion--;

                if (posicion==0){
                    iv.setImageResource(R.drawable.portada1);
                }else if (posicion==1) {
                    iv.setImageResource(R.drawable.portada2);
                }
            }

        }else{
            Toast.makeText(this, "No hay mas canciones", Toast.LENGTH_SHORT).show();
        }

    }
}

