package com.s14990;

import com.basho.riak.client.api.RiakClient;
import com.basho.riak.client.api.cap.Quorum;
import com.basho.riak.client.api.cap.UnresolvedConflictException;
import com.basho.riak.client.api.commands.kv.DeleteValue;
import com.basho.riak.client.api.commands.kv.FetchValue;
import com.basho.riak.client.api.commands.kv.StoreValue;
import com.basho.riak.client.core.query.Location;
import com.basho.riak.client.*;
import com.basho.riak.client.core.query.Namespace;
import com.basho.riak.client.core.query.RiakObject;
import com.basho.riak.client.core.util.BinaryValue;

import java.net.UnknownHostException;
import java.util.concurrent.ExecutionException;

public class Main {

    public static void main(String[] args) {

        // Fetch object as String
        try {
            RiakClient client = RiakClient.newClient(8087, "127.0.0.1");
            //Create object
            Location boatKey = new Location(new Namespace("s14990"), "y3");
            RiakObject boatObject = new RiakObject()
                    .setContentType("application/json")
                    .setValue(BinaryValue.create("{'boat':true, 'name':'Y3'}"));
            StoreValue storeOp = new StoreValue.Builder(boatObject)
                    .withLocation(boatKey)
                    .build();
            client.execute(storeOp);
            //Read1
            FetchValue fetch = new FetchValue.Builder(boatKey)
                    .build();
            RiakObject fetchedObject = client.execute(fetch).getValue(RiakObject.class);
            System.out.println("After Insert");
            System.out.println(fetchedObject.getValue());
            //Edit
            fetchedObject.setValue(BinaryValue.create("{'boat':false, 'name':'Plane'}"));
            StoreValue updateOp = new StoreValue.Builder(fetchedObject)
                    .withLocation(boatKey)
                    .build();
            StoreValue.Response updateOpResp = client.execute(updateOp);
            //Read2
            System.out.println("After Update");
            fetchedObject = client.execute(fetch).getValue(RiakObject.class);
            System.out.println(fetchedObject.getValue());

            //Delete
            DeleteValue deleteOp = new DeleteValue.Builder(boatKey)
                    .build();
            client.execute(deleteOp);
            fetchedObject = client.execute(fetch).getValue(RiakObject.class);
            System.out.println(fetchedObject == null ? "Object Deleted" : "Delete Failed");

            client.shutdown();
        } catch (ExecutionException | InterruptedException | UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
