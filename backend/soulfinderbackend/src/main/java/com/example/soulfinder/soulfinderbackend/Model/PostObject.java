package com.example.soulfinder.soulfinderbackend.Model;

import java.util.List;


import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostObject {
   
    @JsonProperty("postData")
    public String postData;
    @JsonProperty("userId")
    public String userId;
    @JsonProperty("vecImgIds")
    public List<String> vecImgIds;
}
