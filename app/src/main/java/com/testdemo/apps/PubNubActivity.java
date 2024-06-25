package com.testdemo.apps;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.pubnub.api.PNConfiguration;
import com.pubnub.api.PubNub;
import com.pubnub.api.PubNubException;
import com.pubnub.api.UserId;
import com.pubnub.api.callbacks.PNCallback;
import com.pubnub.api.callbacks.SubscribeCallback;
import com.pubnub.api.models.consumer.PNPublishResult;
import com.pubnub.api.models.consumer.PNStatus;
import com.pubnub.api.models.consumer.message_actions.PNMessageAction;
import com.pubnub.api.models.consumer.objects_api.channel.PNChannelMetadataResult;
import com.pubnub.api.models.consumer.objects_api.membership.PNMembershipResult;
import com.pubnub.api.models.consumer.objects_api.uuid.PNUUIDMetadataResult;
import com.pubnub.api.models.consumer.pubsub.PNMessageResult;
import com.pubnub.api.models.consumer.pubsub.PNPresenceEventResult;
import com.pubnub.api.models.consumer.pubsub.PNSignalResult;
import com.pubnub.api.models.consumer.pubsub.files.PNFileEventResult;
import com.pubnub.api.models.consumer.pubsub.message_actions.PNMessageActionResult;
import com.testdemo.apps.databinding.ActivityPubNubBinding;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PubNubActivity extends AppCompatActivity {
    private String TAG = "PubNubActivity";
    private ActivityPubNubBinding binding;
    private PubNub pubNub;
    private String secretKey="sec-c-NDBlMTJjOWQtOGE2OC00YTkzLWE2NjEtZTZlYmJlOTQ0Yjk3";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityPubNubBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        PNConfiguration pnConfiguration = null;
        try {
            pnConfiguration = new PNConfiguration(new UserId("eL1HL8rZLeHnGPU0H9XA"));
        } catch (PubNubException e) {
            throw new RuntimeException(e);
        }
        pnConfiguration.setPublishKey("pub-c-d31b18cc-0588-48d1-8a91-8b4beb3caa0e");
        pnConfiguration.setSubscribeKey("sub-c-de2dc53f-4e09-4ba9-be5d-b043a886f028");
        pubNub = new PubNub(pnConfiguration);

        pubNub.addListener(subscribeCallback);
        pubNub.publish().message("Hello PubSub").channel("my_channel")
                .async((result, status) -> {
                    if (!status.isError()) {
                        Log.d("PubNub", "Message Published");
                    } else {
                        Log.d("PubNub", "Error Publishing: " + status);
                    }
                });
    }

    SubscribeCallback subscribeCallback=new SubscribeCallback() {
        @Override
        public void status(@NotNull PubNub pubnub, @NotNull PNStatus status) {
            switch (status.getCategory()) {
                case PNConnectedCategory:
                    // Connected to PubNub
                    Log.d("PubNub", "Connected");
                    break;
                case PNReconnectedCategory:
                    // Reconnected after a disconnection
                    Log.d("PubNub", "Reconnected");
                    break;
                case PNDisconnectedCategory:
                    // Disconnected from PubNub
                    Log.d("PubNub", "Disconnected");
                    break;
                case PNUnexpectedDisconnectCategory:
                    // Unexpected disconnection, will automatically retry
                    Log.d("PubNub", "Unexpected Disconnect");
                    break;
                default:
                    Log.d("PubNub", "Status: " + status.getCategory());
                    break;
            }
        }

        @Override
        public void message(@NotNull PubNub pubnub, @NotNull PNMessageResult message) {
            String messagePublisher = message.getPublisher();
            System.out.println("Message publisher: " + messagePublisher);
            System.out.println("Message Payload: " + message.getMessage());
            System.out.println("Message Subscription: " + message.getSubscription());
            System.out.println("Message Channel: " + message.getChannel());
            System.out.println("Message timetoken: " + message.getTimetoken());
        }

        @Override
        public void presence(@NotNull PubNub pubnub, @NotNull PNPresenceEventResult presence) {
            System.out.println("Presence Event: " + presence.getEvent());
            System.out.println("Presence Channel: " + presence.getChannel());
            System.out.println("Presence Occupancy: " + presence.getOccupancy());
            System.out.println("Presence State: " + presence.getState());
            System.out.println("Presence UUID: " + presence.getUuid());
            presence.getJoin();
            presence.getLeave();
            presence.getTimeout();
            presence.getHereNowRefresh();
        }

        @Override
        public void signal(@NotNull PubNub pubnub, @NotNull PNSignalResult signal) {
            System.out.println("Signal publisher: " + signal.getPublisher());
            System.out.println("Signal payload: " + signal.getMessage());
            System.out.println("Signal subscription: " + signal.getSubscription());
            System.out.println("Signal channel: " + signal.getChannel());
            System.out.println("Signal timetoken: " + signal.getTimetoken());
        }

        @Override
        public void uuid(@NotNull PubNub pubnub, @NotNull PNUUIDMetadataResult pnUUIDMetadataResult) {

        }

        @Override
        public void channel(@NotNull PubNub pubnub, @NotNull PNChannelMetadataResult pnChannelMetadataResult) {

        }

        @Override
        public void membership(@NotNull PubNub pubnub, @NotNull PNMembershipResult pnMembershipResult) {

        }

        @Override
        public void messageAction(@NotNull PubNub pubnub, @NotNull PNMessageActionResult pnActionResult) {
            PNMessageAction pnMessageAction = pnActionResult.getMessageAction();
            System.out.println("Message action type: " + pnMessageAction.getType());
            System.out.println("Message action value: " + pnMessageAction.getValue());
            System.out.println("Message action uuid: " + pnMessageAction.getUuid());
            System.out.println("Message action actionTimetoken: " + pnMessageAction.getActionTimetoken());
            System.out.println("Message action messageTimetoken: " + pnMessageAction.getMessageTimetoken());
            System.out.println("Message action subscription: " + pnActionResult.getSubscription());
            System.out.println("Message action channel: " + pnActionResult.getChannel());
            System.out.println("Message action timetoken: " + pnActionResult.getTimetoken());
        }

        @Override
        public void file(@NotNull PubNub pubnub, @NotNull PNFileEventResult pnFileEventResult) {
            System.out.println("File channel: " + pnFileEventResult.getChannel());
            System.out.println("File publisher: " + pnFileEventResult.getPublisher());
            System.out.println("File message: " + pnFileEventResult.getMessage());
            System.out.println("File timetoken: " + pnFileEventResult.getTimetoken());
            System.out.println("File file.id: " + pnFileEventResult.getFile().getId());
            System.out.println("File file.name: " + pnFileEventResult.getFile().getName());
            System.out.println("File file.url: " + pnFileEventResult.getFile().getUrl());
        }
    };


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (pubNub != null) {
            pubNub.unsubscribeAll();
            pubNub.stop();
        }
    }
}