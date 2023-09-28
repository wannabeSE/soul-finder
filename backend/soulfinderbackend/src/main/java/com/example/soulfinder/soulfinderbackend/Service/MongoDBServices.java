package com.example.soulfinder.soulfinderbackend.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.example.soulfinder.soulfinderbackend.Model.VectorImage;
import com.example.soulfinder.soulfinderbackend.Repository.VectorImageRepo;

@Service
public class MongoDBServices {
    
    @Autowired
    private VectorImageRepo vectorImageRepo;

    @Autowired
    private MongoTemplate mongoTemplate;

    public void setVectorImgToMongoService(VectorImage vectorImage){

        vectorImageRepo.save(vectorImage);
    }

    public Object returnImageMatches(String idToMatch){

        List<VectorImage> response = mongoTemplate.find(new Query()
            .addCriteria(Criteria.where("vectorDbId").is(idToMatch)),
            VectorImage.class);
        
        return response;
    }
}
