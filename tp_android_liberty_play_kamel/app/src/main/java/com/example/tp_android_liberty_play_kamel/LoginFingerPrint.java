package com.example.tp_android_liberty_play_kamel;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.concurrent.Executor;
public class LoginFingerPrint extends FragmentActivity{
    public static final int BIOMETRIC_STRONG = 15;
    public static final int DEVICE_CREDENTIAL = 32768;
    Button btnAuthentification;
    Intent intent;
    Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_finger_print);
        ctx = this;
        btnAuthentification = findViewById(R.id.btn_authentification_finger);
        final MediaPlayer[] son_authentification = {MediaPlayer.create(ctx, R.raw.son_authentification)};


        // creation du BiometricManager et validation si le user peut utiliser le sensor ou non
        BiometricManager biometricManager = BiometricManager.from(this);

        switch (biometricManager.canAuthenticate()) {
            case BiometricManager.BIOMETRIC_SUCCESS:
                Log.d("validation_fingerPrint_sensor", "Vous pouvez utiliser le capteur d'empreinte pour vous connecter");
                Toast.makeText(getApplicationContext(), "Vous pouvez utiliser le capteur d'empreinte pour vous connecter", Toast.LENGTH_LONG).show();
                break;
            case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                Log.d("validation_fingerPrint_sensor", "Votre telephone n'a pas de capteur d'empreinte");
                Toast.makeText(getApplicationContext(), "Votre telephone n'a pas de capteur d'empreinte", Toast.LENGTH_LONG).show();
                break;
            case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                Log.d("validation_fingerPrint_sensor", "Le capteur d'empreinte n'est pas disponible");
                Toast.makeText(getApplicationContext(), "Le capteur d'empreinte n'est pas disponible", Toast.LENGTH_LONG).show();
                break;
            case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                // Si le user n'a aucune empreinte enregistrer dans le telephone on le redirige vers les parametre pour en cree une
                Toast.makeText(getApplicationContext(), "Aucune empreinte digitale enregistrer dans le telephone!", Toast.LENGTH_LONG).show();
                final Intent enrollIntent = new Intent(Settings.ACTION_BIOMETRIC_ENROLL);
                enrollIntent.putExtra(Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED, BIOMETRIC_STRONG | DEVICE_CREDENTIAL);
                startActivityForResult(enrollIntent, 12);
                break;
        }


        // Creation du dialog box fingerPrint
        Executor executor = ContextCompat.getMainExecutor(this);

        // Creation du Biometric prompt Callback qui me retourne le resultat de l'authentification donc si on peut se loger ou non
         final BiometricPrompt biometricPrompt = new BiometricPrompt(LoginFingerPrint.this, executor, new BiometricPrompt.AuthenticationCallback() {
             @Override // cette mehode est appeler quand il y a une erreur d'authentification
             public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                 super.onAuthenticationError(errorCode, errString);
             }
             @Override // cette mehode est appeler quand l'authentification est reussie
             public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                 super.onAuthenticationSucceeded(result);
                 son_authentification[0].start();
                 intent = new Intent(ctx, BottomNavigationBar.class);
                 startActivity(intent);
                 Toast.makeText(getApplicationContext(), "Authentification Reussie! Bienvenue!", Toast.LENGTH_LONG).show();
             }
             @Override // cette mehode est appeler quand l'authentification a echouee
             public void onAuthenticationFailed() {
                 super.onAuthenticationFailed();
                 Toast.makeText(getApplicationContext(), "Authentification non valide! Recommencez!", Toast.LENGTH_LONG).show();

             }
         });

         // creation du alertdialog biometric
        final BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Connexion")
                .setDescription("Utilise ton empreinte pour te connecter a l'application")
                .setNegativeButtonText("Annuler")
                .build();


        btnAuthentification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                biometricPrompt.authenticate(promptInfo);
            }
        });


    }





}