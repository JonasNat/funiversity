package com.example.funiversity.security.domain;


import com.example.funiversity.security.domain.Feature;

import java.util.List;

public enum Role {
    MEMBER(Feature.READ),
    MANAGER(Feature.READ, Feature.CHANGE),
    ADMIN(Feature.READ, Feature.CHANGE, Feature.CREATE, Feature.DELETE);

    private List<Feature> featureList;

    Role(Feature... featureList) {
        this.featureList = List.of(featureList);
    }

    public boolean containsFeature(Feature feature) {
        return featureList.contains(feature);
    }
}
