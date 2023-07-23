package tn1.popo.Hopfully.interfaces;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.cardview.widget.CardView;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.ViewPropertyAnimator;

import tn1.popo.Hopfully.R;

public class MainActivity extends AppCompatActivity {

    CardView matrice;
    CardView integral;
    CardView derive;
    CardView linear_system;
    CardView eigen;
    CardView plot;
    CardView limit;
    CardView diff;
    CardView dev_limit,complex;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//////////////////////////////////////////////////////////////////////////////////
        matrice=findViewById(R.id.matrice);
        matrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a ViewPropertyAnimator object
                ViewPropertyAnimator animator = matrice.animate();

                // Scale up the CardView to 1.2 times its original size
                animator.scaleX(1.4f).scaleY(1.4f);

                // Set the duration of the animation to 200 milliseconds
                animator.setDuration(1000);
                // Set an AnimatorListener to reset the scale back to 1.0 when the animation ends
                animator.setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        matrice.setScaleX(1.0f);
                        matrice.setScaleY(1.0f);




                    }



                });

                animator.start();
                Intent intent;
                intent =new Intent(MainActivity.this,MatrixOps.class);
                startActivity(intent);



            }
        });
        complex=findViewById(R.id.complex1);
        complex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a ViewPropertyAnimator object
                ViewPropertyAnimator animator = complex.animate();

                // Scale up the CardView to 1.2 times its original size
                animator.scaleX(1.4f).scaleY(1.4f);

                // Set the duration of the animation to 200 milliseconds
                animator.setDuration(1000);
                // Set an AnimatorListener to reset the scale back to 1.0 when the animation ends
                animator.setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        complex.setScaleX(1.0f);
                        complex.setScaleY(1.0f);




                    }



                });

                animator.start();
                Intent intent;
                intent =new Intent(MainActivity.this,Complex.class);
                startActivity(intent);



            }
        });
        //////////////////////////////////////////////////////////////////////////////////
        integral=findViewById(R.id.integral);
        integral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a ViewPropertyAnimator object
                ViewPropertyAnimator animator = integral.animate();

                // Scale up the CardView to 1.2 times its original size
                animator.scaleX(1.4f).scaleY(1.4f);

                // Set the duration of the animation to 200 milliseconds
                animator.setDuration(1000);
                // Set an AnimatorListener to reset the scale back to 1.0 when the animation ends
                animator.setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        integral.setScaleX(1.0f);
                        integral.setScaleY(1.0f);




                    }



                });

                animator.start();
                Intent intent;
                intent =new Intent(MainActivity.this,integral.class);
                startActivity(intent);



            }
        });
        //////////////////////////////////////////////////////////////////////////////////
        derive=findViewById(R.id.derivative);
        derive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a ViewPropertyAnimator object
                ViewPropertyAnimator animator = derive.animate();

                // Scale up the CardView to 1.2 times its original size
                animator.scaleX(1.4f).scaleY(1.4f);

                // Set the duration of the animation to 200 milliseconds
                animator.setDuration(1000);
                // Set an AnimatorListener to reset the scale back to 1.0 when the animation ends
                animator.setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        derive.setScaleX(1.0f);
                        derive.setScaleY(1.0f);




                    }



                });

                animator.start();
                Intent intent;
                intent =new Intent(MainActivity.this,derive.class);
                startActivity(intent);



            }
        });
        //////////////////////////////////////////////////////////////////////////////////
        linear_system =findViewById(R.id.linear_system);
        linear_system.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a ViewPropertyAnimator object
                ViewPropertyAnimator animator = linear_system.animate();

                // Scale up the CardView to 1.2 times its original size
                animator.scaleX(1.4f).scaleY(1.4f);

                // Set the duration of the animation to 200 milliseconds
                animator.setDuration(1000);
                // Set an AnimatorListener to reset the scale back to 1.0 when the animation ends
                animator.setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        linear_system.setScaleX(1.0f);
                        linear_system.setScaleY(1.0f);




                    }



                });

                animator.start();
                Intent intent;
                intent =new Intent(MainActivity.this,linear_system.class);
                startActivity(intent);



            }
        });
        ///////////////////////////////////////////////////////////////////////////////

        eigen =findViewById(R.id.eigen);
        eigen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a ViewPropertyAnimator object
                ViewPropertyAnimator animator = eigen.animate();

                // Scale up the CardView to 1.2 times its original size
                animator.scaleX(1.4f).scaleY(1.4f);

                // Set the duration of the animation to 200 milliseconds
                animator.setDuration(1000);
                // Set an AnimatorListener to reset the scale back to 1.0 when the animation ends
                animator.setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        eigen.setScaleX(1.0f);
                        eigen.setScaleY(1.0f);




                    }



                });

                animator.start();
                Intent intent;
                intent =new Intent(MainActivity.this,eignvalue_vector.class);
                startActivity(intent);



            }
        });
        ///////////////////////////////////////////////////////////////////////////////
        plot =findViewById(R.id.plot);
        plot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a ViewPropertyAnimator object
                ViewPropertyAnimator animator = plot.animate();

                // Scale up the CardView to 1.2 times its original size
                animator.scaleX(1.4f).scaleY(1.4f);

                // Set the duration of the animation to 200 milliseconds
                animator.setDuration(1000);
                // Set an AnimatorListener to reset the scale back to 1.0 when the animation ends
                animator.setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        plot.setScaleX(1.0f);
                        plot.setScaleY(1.0f);




                    }



                });

                animator.start();
                Intent intent;
                intent =new Intent(MainActivity.this,PlotGraph.class);
                startActivity(intent);



            }
        });
        ///////////////////////////////////////////////////////////////////////////////

        diff =findViewById(R.id.differ);
        diff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a ViewPropertyAnimator object
                ViewPropertyAnimator animator = diff.animate();

                // Scale up the CardView to 1.2 times its original size
                animator.scaleX(1.4f).scaleY(1.4f);

                // Set the duration of the animation to 200 milliseconds
                animator.setDuration(1000);
                // Set an AnimatorListener to reset the scale back to 1.0 when the animation ends
                animator.setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        diff.setScaleX(1.0f);
                        diff.setScaleY(1.0f);




                    }



                });

                animator.start();
                Intent intent;
                intent =new Intent(MainActivity.this,differentiel.class);
                startActivity(intent);



            }
        });
        dev_limit =findViewById(R.id.dev_limites);
        dev_limit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a ViewPropertyAnimator object
                ViewPropertyAnimator animator = dev_limit.animate();

                // Scale up the CardView to 1.2 times its original size
                animator.scaleX(1.4f).scaleY(1.4f);

                // Set the duration of the animation to 200 milliseconds
                animator.setDuration(1000);
                // Set an AnimatorListener to reset the scale back to 1.0 when the animation ends
                animator.setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        dev_limit.setScaleX(1.0f);
                        dev_limit.setScaleY(1.0f);

                    }
                });

                animator.start();
                Intent intent;
                intent =new Intent(MainActivity.this, dev_limits.class);
                startActivity(intent);
            }
        });
        ///////////////////////////////////////////////////////////////////////////////

        limit =findViewById(R.id.Limit);
        limit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a ViewPropertyAnimator object
                ViewPropertyAnimator animator = limit.animate();

                // Scale up the CardView to 1.2 times its original size
                animator.scaleX(1.4f).scaleY(1.4f);

                // Set the duration of the animation to 200 milliseconds
                animator.setDuration(1000);
                // Set an AnimatorListener to reset the scale back to 1.0 when the animation ends
                animator.setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        limit.setScaleX(1.0f);
                        limit.setScaleY(1.0f);




                    }



                });

                animator.start();
                Intent intent;
                intent =new Intent(MainActivity.this,limits.class);
                startActivity(intent);



            }
        });
        ///////////////////////////////////////////////////////////////////////////////////////



    }



}