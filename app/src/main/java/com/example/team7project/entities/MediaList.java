package com.example.team7project.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.team7project.R;

import java.util.List;

public class MediaList implements Parcelable {

    private int id;
    private String title;
    private long upvotes;
    private int imageResource = R.drawable.img_badminton;
    private List<MediaItem> mediaItems;

    public MediaList(int id, String title, long upvotes, List<MediaItem> mediaItems) {
        this.id = id;
        this.title = title;
        this.upvotes = upvotes;
        this.mediaItems = mediaItems;
    }

    protected MediaList(Parcel in) {
        id = in.readInt();
        title = in.readString();
        upvotes = in.readLong();
        imageResource = in.readInt();
    }

    public static final Creator<MediaList> CREATOR = new Creator<MediaList>() {
        @Override
        public MediaList createFromParcel(Parcel in) {
            return new MediaList(in);
        }

        @Override
        public MediaList[] newArray(int size) {
            return new MediaList[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(long upvotes) {
        this.upvotes = upvotes;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public List<MediaItem> getMediaItems() {
        return mediaItems;
    }

    public void setMediaItems(List<MediaItem> mediaItems) {
        this.mediaItems = mediaItems;
    }

    @Override
    public String toString() {
        return "MediaList{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", upvotes=" + upvotes +
            ", imageResource=" + imageResource +
            ", mediaitems=" + mediaItems +
            '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeLong(upvotes);
        dest.writeInt(imageResource);
    }
}


