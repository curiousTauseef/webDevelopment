package com.junjunguo.shr.model;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * This file is part of spring_hibernate_relation.
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on 25/10/15.
 */
@Entity
@Table(name = "VIDEO")
public class Video {
    /**
     * video id
     */
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private int    id;
    @Column(name = "TITLE",
            nullable = true,
            columnDefinition = "VARCHAR(255)")
    private String title;

    @Column(name = "DESCRIPTION",
            nullable = true,
            columnDefinition = "TEXT")
    private String    description;
    @Column(name = "UPLOADTIME",
            nullable = false,
            columnDefinition = "DATETIME")
    private Date      uploadTime;
    @Column(name = "HASVIDEO",
            nullable = true,
            columnDefinition = "BOOLEAN")
    private boolean   hasVideo;
    @Column(name = "FILEPATH",
            nullable = true,
            columnDefinition = "VARCHAR(2083)")
    private String    filePath;
    @Column(name = "FILENAME",
            nullable = true,
            columnDefinition = "VARCHAR(255)")
    private String    fileName;
    @Column(name = "FILEEXTENSION",
            nullable = true,
            columnDefinition = "VARCHAR(64)")
    private String    fileExtension;

    @ManyToMany(fetch = FetchType.LAZY,
                targetEntity = Tag.class,
                cascade = {CascadeType.ALL})
    @JoinTable(name = "VIDEO_TAG",
               joinColumns = {@JoinColumn(name = "VIDEO_ID")},
               inverseJoinColumns = {@JoinColumn(name = "TAG_ID")})
    private List<Tag> tags;


    @ManyToOne(fetch = FetchType.LAZY,
               targetEntity = Location.class,
               cascade = CascadeType.ALL
    )
    @JoinColumn(name = "LOCATION_ID")
    private Location location;

    @ManyToOne(
            fetch = FetchType.LAZY,
            targetEntity = User.class,
            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "OWNER_EMAIL")
    private User      owner;

    public Video(String title, String description, String filePath, String fileName, String fileExtension, User owner,
            Location location) {
        this(title, null, description, Calendar.getInstance().getTime(), filePath, fileName, fileExtension, owner,
                location);
    }

    public Video(String title, List<Tag> tags, String description, String filePath, String fileName,
            String fileExtension, User owner, Location location) {
        this(title, tags, description, Calendar.getInstance().getTime(), filePath, fileName, fileExtension, owner,
                location);
    }

    public Video(String title, List<Tag> tags, String description, Date uploadTime, String filePath, String fileName,
            String fileExtension, User owner, Location location) {
        this.title = title;
        this.tags = tags;
        this.description = description;
        this.uploadTime = uploadTime;
        this.filePath = filePath;
        this.fileName = fileName;
        this.fileExtension = fileExtension;
        this.owner = owner;
        this.location = location;
    }

    public Video() {

    }

    public void addTag(String tag) {
        Tag t = new Tag(tag);
        tags.add(t);
    }

    //    /**
    //     * @param stags List of string tag
    //     * @return List of Tag object
    //     */
    //    public List<Tag> getTags(List<String> stags) {
    //        List<Tag> t = new ArrayList<Tag>();
    //        for (String stag : stags) {
    //            t.add(new Tag(stag));
    //        }
    //        return t;
    //    }

    @Override
    public String toString() {
        return "Video [" +
               "title=" + title +
               ", tags=" + tags +
               ", id=" + id +
               ", description=" + description +
               ", uploadTime=" + uploadTime +
               ", filePath=" + filePath +
               ", fileName=" + fileName +
               ", fileExtension=" + fileExtension +
               ", owner=" + owner +
               ", Location=" + location +
               ']';
    }

    /**
     * Gets description.
     *
     * @return Value of description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets fileExtension.
     *
     * @return Value of fileExtension.
     */
    public String getFileExtension() {
        return fileExtension;
    }

    /**
     * Gets video id.
     *
     * @return Value of video id.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets new tags.
     *
     * @param tags New value of tags.
     */
    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    /**
     * Sets new description.
     *
     * @param description New value of description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets fileName.
     *
     * @return Value of fileName.
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Sets new owner.
     *
     * @param owner New value of owner.
     */
    public void setOwner(User owner) {
        this.owner = owner;
    }

    /**
     * Gets uploadTime.
     *
     * @return Value of uploadTime.
     */
    public Date getUploadTime() {
        return uploadTime;
    }

    /**
     * Gets location.
     *
     * @return Value of location.
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Gets tags.
     *
     * @return Value of tags.
     */
    public List<Tag> getTags() {
        return tags;
    }

    /**
     * Gets owner.
     *
     * @return Value of owner.
     */
    public User getOwner() {
        return owner;
    }

    /**
     * Sets new video id.
     *
     * @param id New value of video id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets title.
     *
     * @return Value of title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets new fileName.
     *
     * @param fileName New value of fileName.
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Sets new uploadTime.
     *
     * @param uploadTime New value of uploadTime.
     */
    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    /**
     * Sets new location.
     *
     * @param location New value of location.
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * Sets new title.
     *
     * @param title New value of title.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Sets new fileExtension.
     *
     * @param fileExtension New value of fileExtension.
     */
    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    /**
     * Gets filePath.
     *
     * @return Value of filePath.
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * Sets new filePath.
     *
     * @param filePath New value of filePath.
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }


    /**
     * Sets new hasVideo.
     *
     * @param hasVideo New value of hasVideo.
     */
    public void setHasVideo(boolean hasVideo) {
        this.hasVideo = hasVideo;
    }

    /**
     * Gets hasVideo.
     *
     * @return Value of hasVideo.
     */
    public boolean isHasVideo() {
        return hasVideo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Video)) return false;

        Video video = (Video) o;

        if (getId() != video.getId()) return false;
        if (isHasVideo() != video.isHasVideo()) return false;
        if (getTitle() != null ? !getTitle().equals(video.getTitle()) : video.getTitle() != null) return false;
        if (getDescription() != null ? !getDescription().equals(video.getDescription()) :
                video.getDescription() != null)
            return false;
        if (!getUploadTime().equals(video.getUploadTime())) return false;
        if (getFilePath() != null ? !getFilePath().equals(video.getFilePath()) : video.getFilePath() != null)
            return false;
        if (getFileName() != null ? !getFileName().equals(video.getFileName()) : video.getFileName() != null)
            return false;
        if (getFileExtension() != null ? !getFileExtension().equals(video.getFileExtension()) :
                video.getFileExtension() != null) return false;
        if (!getOwner().equals(video.getOwner())) return false;
        if (getTags() != null ? !getTags().equals(video.getTags()) : video.getTags() != null) return false;
        return getLocation().equals(video.getLocation());

    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + getUploadTime().hashCode();
        result = 31 * result + (isHasVideo() ? 1 : 0);
        result = 31 * result + (getFilePath() != null ? getFilePath().hashCode() : 0);
        result = 31 * result + (getFileName() != null ? getFileName().hashCode() : 0);
        result = 31 * result + (getFileExtension() != null ? getFileExtension().hashCode() : 0);
        result = 31 * result + getOwner().hashCode();
        result = 31 * result + (getTags() != null ? getTags().hashCode() : 0);
        result = 31 * result + getLocation().hashCode();
        return result;
    }
}