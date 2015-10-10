package com.junjunguo.tsag.model;

import java.util.Calendar;

public class Video {
    private static int stid = 0;
    private int id;
    private String title;
    private String tags;
    private String description;
    private long timeStamp;
    private String filePath;
    private String fileName;
    private String fileExtension;
    private String ownerEmail;
    //    private Location location;
    private double latitude, longitude, altitude;

    public Video(String title, String tags, String description, long timeStamp, String filePath, String fileName,
                 String fileExtension, String ownerEmail, double latitude, double longitude, double altitude) {
        this.title = title;
        this.tags = tags;
        this.description = description;
        this.timeStamp = timeStamp;
        this.filePath = filePath;
        this.fileName = fileName;
        this.fileExtension = fileExtension;
        this.ownerEmail = ownerEmail;
        //        this.location = location;
        stid += 1;
        id = stid;
    }

    public Video(String title, String tags, String description, String filePath, String fileName, String fileExtension,
                 String ownerEmail, double latitude, double longitude, double altitude) {
        this(title, tags, description, Calendar.getInstance().getTimeInMillis(), filePath, fileName, fileExtension,
             ownerEmail, latitude, longitude, altitude);
    }

    public Video(String filePath, String fileName, String fileExtension, String ownerEmail, double latitude,
                 double longitude, double altitude) {
        this("Video", "", ownerEmail + "'s video", Calendar.getInstance().getTimeInMillis(), filePath, fileName,
             fileExtension, ownerEmail, latitude, longitude, altitude);
    }

    public Video() {
        stid += 1;
        id = stid;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    //    public Location getLocation() {
    //        return location;
    //    }
    //
    //    public void setLocation(Location location) {
    //        this.location = location;
    //    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public void updateVideo(Video video) {
        setTitle(video.getTitle());
        setDescription(video.getDescription());
        setTags(video.getTags());
        setFilePath(video.getFilePath());
        setFileName(video.getFileName());
        setFileExtension(video.getFileExtension());
        setLatitude(video.getLatitude());
        setLongitude(video.getLongitude());
        setAltitude(video.getAltitude());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (id ^ (id >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) { return true; }
        if (obj == null) { return false; }
        if (getClass() != obj.getClass()) { return false; }
        Video other = (Video) obj;
        return (id == other.id);
    }

    @Override
    public String toString() {
        return "Video [" +
               "title='" + title +
               ", tags='" + tags +
               ", description='" + description +
               ", timeStamp=" + timeStamp +
               ", filePath='" + filePath +
               ", fileName='" + fileName +
               ", fileExtension='" + fileExtension +
               ", ownerEmail='" + ownerEmail +
               ", latitude=" + latitude +
               ", longitude=" + longitude +
               ", altitude=" + altitude +
               ']';
    }
}
