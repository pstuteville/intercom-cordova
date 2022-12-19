package io.intercom.android.sdk;

import android.os.Bundle;

import com.google.firebase.messaging.RemoteMessage;

import org.apache.cordova.firebase.FirebasePluginMessageReceiver;

import io.intercom.android.sdk.push.IntercomPushClient;

public class IntercomMessageReceiver extends FirebasePluginMessageReceiver {

  private final IntercomPushClient intercomPushClient = new IntercomPushClient();

  @Override
  public boolean onMessageReceived(RemoteMessage remoteMessage) {
    if(intercomPushClient.isIntercomPush(remoteMessage.getData())) {
      intercomPushClient.handlePush(IntercomBridge.application, remoteMessage.getData());
      return false; // This is so that the notification bubbles to ionic as well in foreground
    }

    return false;
  }

  @Override
  public boolean sendMessage(Bundle bundle) {
    return false;
  }
}
