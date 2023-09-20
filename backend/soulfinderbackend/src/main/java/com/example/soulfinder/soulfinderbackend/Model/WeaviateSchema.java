package com.example.soulfinder.soulfinderbackend.Model;

import io.weaviate.client.Config;
import io.weaviate.client.WeaviateClient;



public class WeaviateSchema {
    
    public static WeaviateClient retConfig(){
        Config config = new Config("http","localhost:8080");
        WeaviateClient client = new WeaviateClient(config);
        return client;
    }

}
