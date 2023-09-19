package com.example.soulfinder.soulfinderbackend.Model;

// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.Map;

import io.weaviate.client.Config;
import io.weaviate.client.WeaviateClient;
// import io.weaviate.client.base.Result;
// import io.weaviate.client.v1.schema.model.WeaviateClass;


public class WeaviateSchema {
    
    public static WeaviateClient retConfig(){
        Config config = new Config("http","localhost:8080");
        WeaviateClient client = new WeaviateClient(config);
        return client;
    }



    
}
