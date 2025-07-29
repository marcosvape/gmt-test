package com.gmt.test.dao.mock;

import com.gmt.test.model.VideoMetadata;

import java.util.List;
import java.util.Objects;

public class VideoMetadataMockData {

    private final List<VideoMetadata> videos;

    private Integer sequence;

    public VideoMetadataMockData(List<VideoMetadata> videos, Integer sequence) {
        this.videos = videos;
        this.sequence = sequence;
    }

    public List<VideoMetadata> getVideoMetadatas() {
        return videos;
    }

    public VideoMetadata getVideoMetadata(Integer id) {
        VideoMetadata video = null;
        for (VideoMetadata o : videos) {
            if (Objects.equals(o.getId(), id)) {
                video = o;
            }
        }

        return video;
    }

    public VideoMetadata insert(VideoMetadata videoMetadata) {
        sequence++;
        videoMetadata.setId(sequence);

        this.videos.add(videoMetadata);

        return videoMetadata;
    }

}