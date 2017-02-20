package com.example.osamaarshad.deafassist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ToggleButton;

//import com.firebase.client.Firebase;



import java.util.ArrayList;
import java.util.Locale;

public class PopUp2withoutGoogleIntent extends Activity implements
        RecognitionListener {
    SharedPreferences sharedPreferences;
    StringBuilder strBuilder = new StringBuilder("");
    private TextView returnedText;
    static int count=-1;
    private ToggleButton toggleButton;
    private ProgressBar progressBar;
    private SpeechRecognizer speech = null;
    private Intent recognizerIntent;
    private boolean speechRecognizerOn=false;
    /*private DatabaseReference nRef;
    DatabaseReference mostafa;*/
    private String LOG_TAG = "VoiceRecognitionActivity";
    ArrayList<String> matches;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup2layout);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

       // Firebase.setAndroidContext(this);
       // nRef= FirebaseDatabase.getInstance().getReferenceFromUrl("https://deafassist-3920d.firebaseio.com/");
     /*   nRef=FirebaseDatabase.getInstance().getReference();
         mostafa = nRef.child("deafassist-3920d").child("_email");*/

        returnedText = (TextView) findViewById(R.id.textView1);
        progressBar = (ProgressBar) findViewById(R.id.progressBar1);
        toggleButton = (ToggleButton) findViewById(R.id.toggleButton1);
        returnedText.setMovementMethod(new ScrollingMovementMethod());
        progressBar.setVisibility(View.INVISIBLE);
        speech = SpeechRecognizer.createSpeechRecognizer(this);
        speech.setRecognitionListener(this);
        context=this;
        recognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        //recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE,    "en");
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault().toString());
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,
                this.getPackageName());
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_POSSIBLY_COMPLETE_SILENCE_LENGTH_MILLIS, new Long(10000));
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_COMPLETE_SILENCE_LENGTH_MILLIS, 2000000);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_MINIMUM_LENGTH_MILLIS, 20000000);
         recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
        // RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH);
       // recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        //  recognizerIntent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 3);

        recognizerIntent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1);
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {
                    speechRecognizerOn=false;
                    speech.startListening(recognizerIntent);
                    progressBar.setVisibility(View.VISIBLE);
                    progressBar.setIndeterminate(true);
                } else {

                        speech.stopListening();
                        speech.cancel();
                        progressBar.setIndeterminate(false);
                        progressBar.setVisibility(View.INVISIBLE);

                }
            }
        });

//changes being made

        ImageView view=(ImageView)findViewById(R.id.popuplogo);
        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent i= new Intent("package.homescreenactivity");//homescreen of your app.
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NO_HISTORY);
                //startActivity(i);
                finish();

            }
        });
        Button b1=(Button) findViewById(R.id.Button1);
        b1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                count++;
                sharedPreferences=getSharedPreferences("DeafAssist",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString(String.valueOf(count),returnedText.getText().toString());
                editor.commit();
               /* Intent i= new Intent("package.homescreenactivity");//homescreen of your app.
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NO_HISTORY);
                //startActivity(i);
                finish();*/




            }
        });


    }
    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
       /* if (speech != null) {
            speech.destroy();*/
            // Log.i(LOG_TAG, "destroy");


    }

    @Override
    public void onBeginningOfSpeech() {
        // Log.i(LOG_TAG, "onBeg");
        speechRecognizerOn=true;
        progressBar.setIndeterminate(false);
        progressBar.setMax(10);
    }

    @Override
    public void onBufferReceived(byte[] buffer) {
        // Log.i(LOG_TAG, "onBufferReceived: " + buffer);
    }

    @Override
    public void onEndOfSpeech() {
        //Log.i(LOG_TAG, "onEndOfSpeech");
    }

    @Override
    public void onError(int errorCode) {
      //  String errorMessage = getErrorText(errorCode);
        // Log.d(LOG_TAG, "FAILED " + errorMessage);
       // returnedText.setText(errorMessage);
       // toggleButton.setChecked(false);
        if (errorCode == SpeechRecognizer.ERROR_SPEECH_TIMEOUT){

            progressBar.setVisibility(View.INVISIBLE);
            toggleButton.setChecked(false);

        }
    }

    @Override
    public void onEvent(int arg0, Bundle arg1) {
        // Log.i(LOG_TAG, "onEvent");
    }

    @Override
    public void onPartialResults(Bundle arg0) {
        // Log.i(LOG_TAG, "onPartialResults");
    }

    @Override
    public void onReadyForSpeech(Bundle arg0) {
        //Log.i(LOG_TAG, "onReadyForSpeech");
    }

    @Override
    public void onResults(Bundle results) {
        // Log.i(LOG_TAG, "onResults");

        matches = results
                .getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        String text = "";
        for (String result : matches)
            text += result + "\n";

        // returnedText.setText(matches.get(0));
        strBuilder.append(" "+matches.get(0));
        returnedText.setText(strBuilder);
        speech.startListening(recognizerIntent);

        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onRmsChanged(float rmsdB) {
        //Log.i(LOG_TAG, "onRmsChanged: " + rmsdB);
        progressBar.setProgress((int) rmsdB);
    }
    public static String getErrorText(int errorCode) {
        String message;
        switch (errorCode) {
           /* case SpeechRecognizer.ERROR_AUDIO:
                message = "Audio recording error";
                break;
            case SpeechRecognizer.ERROR_CLIENT:
                message = "Client side error";
                break;
            case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:
                message = "Insufficient permissions";
                break;
            case SpeechRecognizer.ERROR_NETWORK:
                message = "Network error";
                break;
            case SpeechRecognizer.ERROR_NETWORK_TIMEOUT:
                message = "Network timeout";
                break;
            case SpeechRecognizer.ERROR_NO_MATCH:
                message = "No match";
                break;
            case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
                message = "RecognitionService busy";
                break;
            case SpeechRecognizer.ERROR_SERVER:
                message = "error from server";
                break;
            case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:
                message = "No speech input";
                break;*/
            default:
                message = "";//"Didn't understand, please try again.";
                break;
        }
        return message;
    }





}
