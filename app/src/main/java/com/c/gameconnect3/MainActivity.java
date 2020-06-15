package com.c.gameconnect3;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.SoundEffectConstants;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int activePlayer = 0,boxTapped = -1;
    boolean stillPlaying = true;
    int boxesLeft = 9 ;
    int[] filledBoxes = {-1,-1,-1,-1,-1,-1,-1,-1,-1};
    int[][] winnigPositions = {
                                  {1 , 2 , 3} ,
                                  {4 , 5 , 6} ,
                                  {7 , 8 , 9} ,
                                  {1 , 4 , 7} ,
                                  {2 , 5 , 8} ,
                                  {3 , 6 , 9} ,
                                  {1 , 5 , 9} ,
                                  {3 , 5 , 7} ,
                                                  }    ;
    public void clickedOnGrid(View view)
    {

            int flag = 0,won = -1 ;
            boxTapped = Integer.parseInt(view.getTag().toString());
            ImageView image = (ImageView) view;
            if(filledBoxes[boxTapped-1] == -1 && stillPlaying)
            {
                System.out.println("Again");
                if (activePlayer == 0)
                {
                    image.setImageResource(R.drawable.yellow);
                    image.setTranslationX(-1000f);
                    image.animate().rotationBy(3600).translationXBy(1000f).setDuration(500);
                    activePlayer = 1;
                    boxesLeft = boxesLeft - 1 ;
                    filledBoxes[boxTapped - 1] = 1;
                }
                else
                {
                    image.setImageResource(R.drawable.red);
                    activePlayer = 0;
                    image.setTranslationY(-1000f);
                    image.animate().rotationBy(7200).translationYBy(1000f).setDuration(500);
                    filledBoxes[boxTapped - 1] = 2;
                    boxesLeft = boxesLeft - 1 ;

                }

//                for (int i = 0; i < 9; i++) {
//                    System.out.print(filledBoxes[i] + "  ");
//                }
                System.out.println();
                for (int[] conditions : winnigPositions)
                {
                    if (filledBoxes[conditions[0] - 1] == filledBoxes[conditions[1] - 1] && filledBoxes[conditions[1] - 1] == filledBoxes[conditions[2] - 1])
                    {
                        if (filledBoxes[conditions[0] - 1] != -1)
                        {
                            flag = 1;
                            won = filledBoxes[conditions[0] - 1] ;
                           stillPlaying = false;
                            break;
                        }
                    }
                }
                if (flag == 1)
                {

                        if( won == 1 )
                        {
                            TextView textview = (TextView) findViewById(R.id.winnigMessage) ;
                            textview.setText("Player 1 Won");
                        }
                        else
                         {
                            TextView textview = (TextView) findViewById(R.id.winnigMessage) ;
                            textview.setText("Player 2 Won");
                        }
                    LinearLayout linearLayout = (LinearLayout) findViewById(R.id.postLayout) ;
                    linearLayout.setVisibility(linearLayout.VISIBLE);
                    linearLayout.setTranslationX(-1000f);
                    linearLayout.animate().translationXBy(1000f).rotationBy(1800f).setDuration(1000);




                }

                else
                {
                    System.out.println("Boxes Left = " + boxesLeft);
                    if(boxesLeft == 0)
                    {
                        System.out.println("Done");
                        TextView textview = (TextView) findViewById(R.id.winnigMessage);
                        textview.setText("Match Draw");
                        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.postLayout);
                        linearLayout.setVisibility(View.VISIBLE);
                        linearLayout.setTranslationX(-1000f);
                        linearLayout.animate().translationXBy(1000f).rotationBy(1800f).setDuration(1000);

                    }

                }


            }




    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void playAgain(View view)
    {
          LinearLayout linearLayout = (LinearLayout) findViewById(R.id.postLayout) ;
          linearLayout.setVisibility(View.INVISIBLE);
          GridLayout gridLayout = (GridLayout) findViewById(R.id.grid);
          for( int i = 0 ; i < gridLayout.getChildCount() ; i++)
           {
                ImageView imageView = (ImageView) gridLayout.getChildAt(i) ;
                imageView.setImageResource(0);
           }
           for(int i = 0 ; i < 9 ; i++)
           {
                filledBoxes[i] = -1;
           }
           stillPlaying = true;
           boxesLeft = 9;
           boxTapped = -1 ;
           activePlayer = 0;

    }
}
