package com.example.osamaarshad.deafassist;

import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.speech.RecognizerIntent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.Gravity;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import static android.app.Activity.RESULT_OK;

/**
 * Created by OsamaArshad on 15-Nov-16.
 */

public class MyNetwork extends BroadcastReceiver
{
    Context gContext;

    private static int lastState = TelephonyManager.CALL_STATE_IDLE;
    private static Date callStartTime;
    private static boolean isIncoming;
    private static String savedNumber;  //because the passed incoming is only valid in ringing


    @Override
    public void onReceive(Context context, Intent intent) {
        gContext=context;
        //We listen to two intents.  The new outgoing call only tells us of an outgoing call.  We use it to get the number.
        if (intent.getAction().equals("android.intent.action.NEW_OUTGOING_CALL")) {
            savedNumber = intent.getExtras().getString("android.intent.extra.PHONE_NUMBER");
        } else {
            String stateStr = intent.getExtras().getString(TelephonyManager.EXTRA_STATE);
            String number = intent.getExtras().getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
            int state = 0;
            if (stateStr.equals(TelephonyManager.EXTRA_STATE_IDLE)) {
                state = TelephonyManager.CALL_STATE_IDLE;
            } else if (stateStr.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)) {
                state = TelephonyManager.CALL_STATE_OFFHOOK;
            } else if (stateStr.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                state = TelephonyManager.CALL_STATE_RINGING;
            }


            onCallStateChanged(context, state, number);
        }
    }
    protected void onIncomingCallStarted(Context ctx, String number, Date start){}
    protected void onOutgoingCallStarted(Context ctx, String number, Date start){}
    protected void onIncomingCallEnded(Context ctx, String number, Date start, Date end){}
    protected void onOutgoingCallEnded(Context ctx, String number, Date start, Date end){}
    protected void onMissedCall(Context ctx, String number, Date start){}

    //Deals with actual events

    //Incoming call-  goes from IDLE to RINGING when it rings, to OFFHOOK when it's answered, to IDLE when its hung up
    //Outgoing call-  goes from IDLE to OFFHOOK when it dials out, to IDLE when hung up
    public void onCallStateChanged(Context context, int state, String incomingNumber) {
        Context pContext=context;

        Intent i = new Intent();
        if(lastState == state){
            //No change, debounce extras
            return;
        }
        switch (state) {
            case TelephonyManager.CALL_STATE_RINGING:
                isIncoming = true;
                callStartTime = new Date();
                savedNumber = incomingNumber;
                onIncomingCallStarted(context, incomingNumber, callStartTime);
/*
                i.setClassName("com.example.osamaarshad.deafassist", "com.example.osamaarshad.deafassist.PopUp");
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                gContext.startActivity(i);*/
                //   txtSpeechInput = (TextView) findViewById(R.id.txtSpeechInput);
                String msg1="Incoming Number: "+incomingNumber+callStartTime;
                // Toast.makeText(pContext,msg, Toast.LENGTH_LONG).show();

                Toast toast1 = Toast.makeText(pContext,msg1, Toast.LENGTH_LONG);
                toast1.setGravity(Gravity.CENTER, toast1.getXOffset() / 2, toast1.getYOffset() / 2);

                TextView textView1 = new TextView(pContext);
                textView1.setBackgroundColor(Color.DKGRAY);
                textView1.setTextColor(Color.WHITE);
                textView1.setTextSize(30);
                Typeface typeface1 = Typeface.create("serif", Typeface.BOLD);
                textView1.setTypeface(typeface1);
                textView1.setPadding(10, 10, 10, 10);
                textView1.setText(msg1);

                toast1.setView(textView1);
                toast1.show();
                break;
            case TelephonyManager.CALL_STATE_OFFHOOK:

                //Transition of ringing->offhook are pickups of incoming calls.  Nothing done on them
                //if(lastState != TelephonyManager.CALL_STATE_RINGING){
                    isIncoming = false;
                    callStartTime = new Date();
                   onOutgoingCallStarted(context, savedNumber, callStartTime);
/*

                    i.setClassName("com.example.osamaarshad.deafassist", "com.example.osamaarshad.deafassist.PopUp");
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    gContext.startActivity(i);*/
                    String msg2=" On Going Call: "+incomingNumber+callStartTime;
               /* AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
                audioManager.setSpeakerphoneOn(true);
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0);
*/

                    // Toast.makeText(pContext, msg, Toast.LENGTH_LONG).show();
                    Toast toast2 = Toast.makeText(pContext,msg2, Toast.LENGTH_LONG);
                    toast2.setGravity(Gravity.CENTER, toast2.getXOffset() / 2, toast2.getYOffset() / 2);

                    TextView textView2 = new TextView(pContext);
                    textView2.setBackgroundColor(Color.DKGRAY);
                    textView2.setTextColor(Color.WHITE);
                    textView2.setTextSize(30);
                    Typeface typeface2 = Typeface.create("serif", Typeface.BOLD);
                    textView2.setTypeface(typeface2);
                    textView2.setPadding(10, 10, 10, 10);
                    textView2.setText(msg2);

                    toast2.setView(textView2);
                    toast2.show();
               // }



                break;
            case TelephonyManager.CALL_STATE_IDLE:
                //Went to idle-  this is the end of a call.  What type depends on previous state(s)
              /*  if(lastState == TelephonyManager.CALL_STATE_RINGING){
                    //Ring but no pickup-  a miss
                    onMissedCall(context, savedNumber, callStartTime);
                }
                else if(isIncoming){
                    onIncomingCallEnded(context, savedNumber, callStartTime, new Date());
                }
                else{
                    onOutgoingCallEnded(context, savedNumber, callStartTime, new Date());
                }*/


                String msg3=" Call Ended: "+incomingNumber+callStartTime;
                // Toast.makeText(mContext, msg, Toast.LENGTH_LONG).show();
                Toast toast3 = Toast.makeText(pContext,msg3, Toast.LENGTH_LONG);
                toast3.setGravity(Gravity.CENTER, toast3.getXOffset() / 2, toast3.getYOffset() / 2);

                TextView textView3 = new TextView(pContext);
                textView3.setBackgroundColor(Color.DKGRAY);
                textView3.setTextColor(Color.WHITE);
                textView3.setTextSize(30);
                Typeface typeface3 = Typeface.create("serif", Typeface.BOLD);
                textView3.setTypeface(typeface3);
                textView3.setPadding(10, 10, 10, 10);
                textView3.setText(msg3);

                toast3.setView(textView3);
                toast3.show();
                break;
        }
        lastState = state;
    }

}



/*

public class MyNetwork extends BroadcastReceiver {

    private static int lastState = TelephonyManager.CALL_STATE_IDLE;
    private static Date callStartTime;
    private static boolean isIncoming;
    private static String savedNumber;  //because the passed incoming is only valid in ringing



    @Override
    public void onReceive(final Context mContext, Intent intent)
    {
        try
        {
            final TelephonyManager telephony=(TelephonyManager)mContext.getSystemService(Context.TELEPHONY_SERVICE);

            telephony.listen(new PhoneStateListener(){

                @Override
                public void onCallStateChanged(int state, String incomingNumber) {
                    //super.onCallStateChanged(state, incomingNumber);
                    Context pContext=mContext;
                    if(state==TelephonyManager.CALL_STATE_RINGING)//(state.equals(telephony.EXTRA_STATE_RINGING))
                    {

                     //   txtSpeechInput = (TextView) findViewById(R.id.txtSpeechInput);
                        String msg="Incoming Number: "+incomingNumber;
                       // Toast.makeText(pContext,msg, Toast.LENGTH_LONG).show();

                        Toast toast = Toast.makeText(pContext,msg, Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, toast.getXOffset() / 2, toast.getYOffset() / 2);

                        TextView textView = new TextView(pContext);
                        textView.setBackgroundColor(Color.DKGRAY);
                        textView.setTextColor(Color.WHITE);
                        textView.setTextSize(30);
                        Typeface typeface = Typeface.create("serif", Typeface.BOLD);
                        textView.setTypeface(typeface);
                        textView.setPadding(10, 10, 10, 10);
                        textView.setText(msg);

                        toast.setView(textView);
                        toast.show();

                    }
                    if(state==TelephonyManager.CALL_STATE_OFFHOOK)
                    {
                       // promptSpeechInput();
                        String msg=" On Going Call: "+incomingNumber;
                       // Toast.makeText(pContext, msg, Toast.LENGTH_LONG).show();
                        Toast toast = Toast.makeText(pContext,msg, Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, toast.getXOffset() / 2, toast.getYOffset() / 2);

                        TextView textView = new TextView(pContext);
                        textView.setBackgroundColor(Color.DKGRAY);
                        textView.setTextColor(Color.WHITE);
                        textView.setTextSize(30);
                        Typeface typeface = Typeface.create("serif", Typeface.BOLD);
                        textView.setTypeface(typeface);
                        textView.setPadding(10, 10, 10, 10);
                        textView.setText(msg);

                        toast.setView(textView);
                        toast.show();

                    }
                    if (state==TelephonyManager.CALL_STATE_IDLE)
                    {
                        String msg=" Call Ended: "+incomingNumber;
                       // Toast.makeText(mContext, msg, Toast.LENGTH_LONG).show();
                        Toast toast = Toast.makeText(pContext,msg, Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, toast.getXOffset() / 2, toast.getYOffset() / 2);

                        TextView textView = new TextView(pContext);
                        textView.setBackgroundColor(Color.DKGRAY);
                        textView.setTextColor(Color.WHITE);
                        textView.setTextSize(30);
                        Typeface typeface = Typeface.create("serif", Typeface.BOLD);
                        textView.setTypeface(typeface);
                        textView.setPadding(10, 10, 10, 10);
                        textView.setText(msg);

                        toast.setView(textView);
                        toast.show();

                    }

                }


        },PhoneStateListener.LISTEN_CALL_STATE);
    }

        catch(Exception e)
        {
            //your custom message
        }

    }


}
*/