package com.example.ottot.mineseeker;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class UserMenu extends AppCompatActivity {//this is the actual main activity
    //check out table.java
    private int dim_code;//0= 4*6, 1 = 5*7, 2=6*8, 3=4*8, 4=5*12, 5= 6*15
    private int mine_code;//0 = 6, 1 = 10, 2 = 15, 3 = 25, 4 = 35
    private static int TimePlayed;
    private static int BestScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_menu);
        //initialize table data with default values

        TimePlayed=222;
        BestScore=120;
        startGame();
        optionMenu();
        helpMenu();
        showInfo();
        //------------------------------------
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                //111
                break;
            case 2:
                if (data.getIntExtra("resetTimeOrNot", 0) == 1) {
                    resetPlaytime();
                }
                if (data.getIntExtra("resetScoreOrNot", 0) == 1) {
                    resetBestScore();
                }
                dim_code = data.getIntExtra("dimension",0);
                mine_code = data.getIntExtra("mineNum",0);
                break;
        }
    }



    private void startGame() {
        final Button startGame = (Button)findViewById(R.id.Game_start);
        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent game_Start = main_Game.makeIntent(UserMenu.this);
                game_Start.putExtra("dimCode",dim_code);
                game_Start.putExtra("mineCode",mine_code);
                game_Start.putExtra("time",TimePlayed);
                game_Start.putExtra("scr",BestScore);
                startActivityForResult(game_Start,1);
            }
        });
    }
    private void optionMenu() {
        final Button options = (Button)findViewById(R.id.options_btn);
        options.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go_to_opt = options_page.makeIntent(UserMenu.this);
                go_to_opt.putExtra("TimePlay",TimePlayed);
                go_to_opt.putExtra("table_dim",dim_code);
                go_to_opt.putExtra("numberMine",mine_code);

                startActivityForResult(go_to_opt,2);
            }
        });
    }
    private void helpMenu() {
        final Button help = (Button)findViewById(R.id.help_btn);
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go_to_help = help_page.makeIntent(UserMenu.this);
                startActivity(go_to_help);
            }
        });
    }
    private void showInfo() {
        TextView timePlay = (TextView) findViewById(R.id.TimePlayed);
        timePlay.setText(""+TimePlayed);
        TextView bestScore = (TextView) findViewById(R.id.BestScore);
        bestScore.setText(""+ BestScore);
    }

    private void resetPlaytime(){
        TimePlayed = 0;
        showInfo();
    }
    private void resetBestScore() {
        BestScore = 0;
        showInfo();
    }

    public static Intent makeIntent(Context context){
        return new Intent(context, UserMenu.class);
    }


}
