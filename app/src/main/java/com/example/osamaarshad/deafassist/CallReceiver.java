package com.example.osamaarshad.deafassist;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;

import java.util.Date;

/**
 * Created by OsamaArshad on 13-Feb-17.
 */

public class CallReceiver extends MyNetwork{

        Intent i = new Intent();
        @Override
        protected void onIncomingCallStarted(Context ctx, String number, Date start) {
                AudioManager audioManager = (AudioManager) ctx.getSystemService(Context.AUDIO_SERVICE);
                audioManager.setSpeakerphoneOn(true);
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0);

        }

        @Override
        protected void onOutgoingCallStarted(Context ctx, String number, Date start) {
             /*   i.setClassName("com.example.osamaarshad.deafassist", "com.example.osamaarshad.deafassist.PopUp");
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                gContext.startActivity(i);*/
                AudioManager audioManager = (AudioManager) ctx.getSystemService(Context.AUDIO_SERVICE);
                audioManager.setSpeakerphoneOn(true);
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0);
                i.setClassName("com.example.osamaarshad.deafassist", "com.example.osamaarshad.deafassist.PopUp2withoutGoogleIntent");
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                gContext.startActivity(i);
        }

        @Override
        protected void onIncomingCallEnded(Context ctx, String number, Date start, Date end) {


        }

        @Override
        protected void onOutgoingCallEnded(Context ctx, String number, Date start, Date end) {


        }

        @Override
        protected void onMissedCall(Context ctx, String number, Date start) {
        }

}
