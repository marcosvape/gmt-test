package com.gmt.test.service;

import com.gmt.test.controller.dto.FilterDTO;
import com.gmt.test.controller.dto.VideoImportDTO;
import com.gmt.test.controller.dto.VideoMetadataStatsDTO;
import com.gmt.test.dao.VideoMetadataDAO;
import com.gmt.test.external.ExternalVideoSourceService;
import com.gmt.test.model.VideoMetadata;
import com.gmt.test.model.enuns.Source;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoMetadataService {

    @Autowired
    public ExternalVideoSourceService externalVideoSourceService;

    @Autowired
    public VideoMetadataDAO videoMetadataDAO;

    @Value("${youtube.api.key}")
    private String YOUTUBE_API_KEY;

    public VideoMetadata importVideo(VideoImportDTO videoImportDTO) {
        VideoMetadata newVideoMetadata = externalVideoSourceService.importVideo(videoImportDTO);

        return this.videoMetadataDAO.createVideoMetadata(newVideoMetadata);
    }

    public List<VideoMetadata> getAll(FilterDTO filterDTO) {
        return this.videoMetadataDAO.fetchVideosMetadata(filterDTO);
    }

    public VideoMetadata getById(Integer videoMetadataId) {
        return this.videoMetadataDAO.fetchVideoMetadata(videoMetadataId);
    }

    public VideoMetadataStatsDTO getVideoMetadataStatsBySource(Source source) {
        return this.videoMetadataDAO.fetchVideosMetadataStatsBySource(source);
    }
}