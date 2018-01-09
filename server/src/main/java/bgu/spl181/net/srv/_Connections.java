package bgu.spl181.net.srv;

import bgu.spl181.net.api.bidi.ConnectionHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class _Connections<T> implements bgu.spl181.net.api.bidi.Connections<T> {
    private ConcurrentHashMap<Integer,ConnectionHandler<T>> connectedCientsCHMap;
    private AtomicInteger connectionId;

    public _Connections(){
        this.connectedCientsCHMap = new ConcurrentHashMap<>();
        this.connectionId = new AtomicInteger(0);
    }

    //send a message to a client represented by the given connectionId
    @Override
    public boolean send(int connectionId, T msg) {
        boolean output = false;
        ConnectionHandler<T> ch = connectedCientsCHMap.get(connectionId);

        if (ch != null){
            ch.send(msg);
            output = true;
        }
        return output;
    }

    /*
    sends a message T to all active clients. This
    includes clients that has not yet completed log-in by the User service text
    based protocol. Remember, Connections<T> belongs to the server pattern
    implemenration, not the protocol!.
     */

    //Send a message to all the logged in clients.
    @Override
    public void broadcast(T msg) {
        for (Map.Entry<Integer, ConnectionHandler<T>> entry : connectedCientsCHMap.entrySet()) {
            ConnectionHandler<T> ch = entry.getValue();
            ch.send(msg);
        }
    }


    //removes active client connId from map
    @Override
    public void disconnect(int connectionId) {
        this.connectedCientsCHMap.remove(connectionId);
    }



}