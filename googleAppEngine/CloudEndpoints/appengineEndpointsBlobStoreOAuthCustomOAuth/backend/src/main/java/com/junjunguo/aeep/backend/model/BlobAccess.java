package com.junjunguo.aeep.backend.model;

/**
 * This file is part of appengineEndpointsStorage
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on January 14, 2016.
 */
public class BlobAccess {
    private String blobStoreURL;


    /**
     * Instantiates a new Blob access.
     */
    public BlobAccess() {
    }

    /**
     * Instantiates a new Blob access.
     * @param blobStoreURL the blob store url
     */
    public BlobAccess(String blobStoreURL) {
        this.blobStoreURL = blobStoreURL;
    }

    /**
     * Gets blob store url.
     * @return the blob store url
     */
    public String getBlobStoreURL() {
        return blobStoreURL;
    }

    /**
     * Sets blob store url.
     * @param blobStoreURL the blob store url
     */
    public void setBlobStoreURL(String blobStoreURL) {
        this.blobStoreURL = blobStoreURL;
    }
}
