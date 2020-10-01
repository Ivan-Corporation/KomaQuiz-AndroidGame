package komar.corporation.Quiz;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import static android.view.WindowManager.LayoutParams;

public class MainActivity extends AppCompatActivity {

    private long backPressedTime;
    private Toast backToast;
    private MediaPlayer clickSound;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button buttonStart = (Button)findViewById(R.id.buttonStart);

        clickSound = MediaPlayer.create(this, R.raw.click);

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPlay (clickSound);
            try{
                Intent intent = new Intent(MainActivity.this, GameLevels.class);
                startActivity(intent);finish();
            }catch (Exception e) {

            } // конец конструкции

            }

            public void soundPlay (MediaPlayer sound) {
                sound.start();
            }
        });

        // Скрытая строка состояния
        Window w = getWindow();
        w.setFlags(LayoutParams.FLAG_FULLSCREEN, LayoutParams.FLAG_FULLSCREEN);

    }


    // Системная кнопка "Назад" - начало

    @Override
    public void onBackPressed() {



        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            //Если текущее время больше то выполняется этот код
            backToast.cancel();
            super.onBackPressed();
            return;
        }

        else {
            backToast = Toast.makeText(getBaseContext(),"Нажмите еще раз чтобы выйти", Toast.LENGTH_SHORT);
            backToast.show();
        }


        backPressedTime = System.currentTimeMillis();
    }

    // Системная кнопка "Назад" - конец
}

