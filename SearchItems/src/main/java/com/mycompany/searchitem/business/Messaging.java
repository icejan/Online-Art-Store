
package com.mycompany.searchitem.business;

import io.grpc.stub.StreamObserver;
import io.kubemq.sdk.basic.ServerAddressNotSuppliedException;
import io.kubemq.sdk.event.EventReceive;
import io.kubemq.sdk.event.Subscriber;
import io.kubemq.sdk.subscription.EventsStoreType;
import io.kubemq.sdk.subscription.SubscribeRequest;
import io.kubemq.sdk.subscription.SubscribeType;
import io.kubemq.sdk.tools.Converter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLException;
import com.mycompany.searchitem.endpoint.MyAppServletContextListener;
import com.mycompany.searchitem.helper.Item;
import com.mycompany.searchitem.persistence.Item_CRUD;
import java.util.ArrayList;

import com.google.protobuf.GeneratedMessageV3.*;
import io.grpc.NameResolverProvider.*;

public class Messaging {
    public static void Receiving_Events_Store(String cname) throws SSLException, ServerAddressNotSuppliedException {
        
        String ChannelName = cname, ClientID = "add-cart-subscriber";
        String kubeMQAddress = System.getenv("kubeMQAddress");
        Subscriber subscriber = new Subscriber(kubeMQAddress);
        SubscribeRequest subscribeRequest = new SubscribeRequest();
        subscribeRequest.setChannel(ChannelName);
        subscribeRequest.setClientID(ClientID);
        subscribeRequest.setSubscribeType(SubscribeType.EventsStore);
        subscribeRequest.setEventsStoreType(EventsStoreType.StartAtSequence);
        subscribeRequest.setEventsStoreTypeValue(1);

        StreamObserver<EventReceive> streamObserver = new StreamObserver<EventReceive>() {

            @Override
            public void onNext(EventReceive value) {
                try {
                    String val= (String) Converter.FromByteArray(value.getBody());
                    System.out.printf("Event Received: EventID: %s, Channel: %s, Metadata: %s, Body: %s",
                            value.getEventId(), value.getChannel(), value.getMetadata(),
                            Converter.FromByteArray(value.getBody()));
                    String[] msgParts = val.split(":");
                    /*test
                    System.out.println("Message parts:");
                    for (int i=0; i<msgParts.length;i++){
                        System.out.println(i + " : " + msgParts[i]);
                    }
                    */
                    if(msgParts.length==3){
                        if(msgParts[0].equals("ADDCART")){
                            //ADDCART:2:1
                          String itemid=msgParts[1];
                          String quantity=msgParts[2];
                          
                          ArrayList <Item> items = Item_CRUD.searchForItems("itemid", itemid);
                          int total_stock = items.get(0).getItemStock() - Integer.valueOf(quantity);
                          System.out.println("Messaging received itemid: " + itemid+" | quantity: " + quantity + " | total stock: " + total_stock);
                          Item_CRUD.updateStock(itemid, total_stock);
                        }
                    }
                } catch (ClassNotFoundException e) {
                    System.out.printf("ClassNotFoundException: %s", e.getMessage());
                    e.printStackTrace();
                } catch (IOException e) {
                    System.out.printf("IOException: %s", e.getMessage());
                    e.printStackTrace();
                } catch (SQLException ex) {
                    Logger.getLogger(MyAppServletContextListener.class.getName()).log(Level.SEVERE, null, ex);
                }  
            }
            
            @Override
            public void onError(Throwable t) {
                System.out.printf("onError:  %s", t.getMessage());
            }

            @Override
            public void onCompleted() {

            }
            
        };
        subscriber.SubscribeToEvents(subscribeRequest, streamObserver);

    }
    
}

