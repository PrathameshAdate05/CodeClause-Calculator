package com.prathamesh.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.udojava.evalex.Expression;

import java.util.Objects;

public class Home extends AppCompatActivity {

    GridLayout gridLayout;
    TextView TVOutput;
    String[] grid = {"AC","SQ","DEL","/",
                     "7","8","9","X",
                     "4","5","6","+",
                     "1","2","3","-",
                      ".","0","00","="};

    String memory = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        gridLayout = findViewById(R.id.gridlayout);
        TVOutput = findViewById(R.id.TV_Output);



        for (int i = 0; i < grid.length; i++) {
            Button button = new Button(this);
            if (i <= 3 || i == 7 || i == 11 || i == 15 || i == 19)
                button.setBackgroundResource(R.drawable.buttonstyle1);
            else
                button.setBackgroundResource(R.drawable.buttonstyle2);
            button.setText(grid[i]);
            button.setTextSize(40);
            button.setOnClickListener(getOnClick(i));
            gridLayout.addView(button);
        }
    }

    private View.OnClickListener getOnClick(final int i){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Objects.equals(grid[i], "AC"))
                   TVOutput.setText("");
                else if (i > 2 && i != 19)
                    TVOutput.setText(TVOutput.getText()+grid[i]);
                else if (i == 19){
                   if (TVOutput.getText().toString().equals("")){
                       Toast.makeText(Home.this, "Please enter some input...!!", Toast.LENGTH_SHORT).show();
                   }else {
                       try {
                           memory = TVOutput.getText().toString().replace('X','*');
                           Expression expression = new Expression(memory).setPrecision(5);
                           String temp = expression.eval().toString();
                           TVOutput.setText(temp);
                       }catch (Exception e){
                           TVOutput.setText("Syntax Error");
                       }
                   }
                }else if (i == 2){

                    if(TVOutput.getText().toString().equals("")){
                        Toast.makeText(Home.this, "Please enter some input...!!", Toast.LENGTH_SHORT).show();
                    }else{
                        memory = TVOutput.getText().toString();
                        TVOutput.setText(memory.substring(0,memory.length()-1));
                    }
                }else if (i == 1){
                    if(TVOutput.getText().toString().equals("")){
                        Toast.makeText(Home.this, "Please enter some input...!!", Toast.LENGTH_SHORT).show();
                    }else{
                        Integer temp = Integer.parseInt(TVOutput.getText().toString());
                        TVOutput.setText(String.valueOf(temp*temp));
                    }
                }
            }
        };
    }

}