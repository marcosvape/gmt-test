package com.gmt.test.dao;

import com.gmt.test.controller.dto.FilterDTO;
import com.gmt.test.controller.dto.VideoMetadataStatsDTO;
import com.gmt.test.dao.mock.VideoMetadataMockData;
import com.gmt.test.model.VideoMetadata;
import com.gmt.test.model.enuns.Source;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
public class VideoMetadataDAO {

    private static final Logger logger = LoggerFactory.getLogger(VideoMetadataDAO.class);

    private final VideoMetadataMockData videoMock = new VideoMetadataMockData(new ArrayList<VideoMetadata>(), 0);

    public VideoMetadata createVideoMetadata(VideoMetadata videoMetadata) {
        return this.videoMock.insert(videoMetadata);
    }

    public List<VideoMetadata> fetchVideosMetadata(FilterDTO filterDTO) {
        List<VideoMetadata> videos = this.videoMock.getVideoMetadatas();

        if (filterDTO != null && filterDTO.getSource() != null) {
            videos = videos.stream()
                    .filter(video -> video.getSource().equals(filterDTO.getSource()))
                    .toList();
        }

        return videos;
    }

    public VideoMetadata fetchVideoMetadata(Integer videoMetadataId) {
        return this.videoMock.getVideoMetadata(videoMetadataId);
    }

    public VideoMetadataStatsDTO fetchVideosMetadataStatsBySource(Source source) {
        List<VideoMetadata> filteredVideos = this.videoMock.getVideoMetadatas().stream()
                .filter(video -> video.getSource().equals(source))
                .toList();

        int totalVideos = filteredVideos.size();

        long totalDurationSeconds = filteredVideos.stream()
                .mapToLong(video -> {
                    String isoDuration = video.getDuration();
                    if (isoDuration != null && !isoDuration.isBlank()) {
                        try {
                            return Duration.parse(isoDuration).getSeconds();
                        } catch (Exception e) {
                            System.err.println("Failed to parse ISO duration: " + isoDuration);
                        }
                    }
                    return 0;
                })
                .sum();

        long averageDuration = totalVideos > 0 ? totalDurationSeconds / totalVideos : 0;

        VideoMetadataStatsDTO stats = new VideoMetadataStatsDTO();
        stats.setSource(source.name());
        stats.setTotalVideos(String.valueOf(totalVideos));
        stats.setAverageDuration(String.valueOf(averageDuration));

        return stats;
    }

}