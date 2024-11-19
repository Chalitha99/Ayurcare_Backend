package com.project2.ayurcare.ayurcare_backend.buckets;

public enum BucketName {
    
    PROFILE_IMAGE("Add your bucket name here");

    private final String bucketName;

    BucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return bucketName;
    }
}
