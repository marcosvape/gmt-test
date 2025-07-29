package com.gmt.test.external;

import com.gmt.test.controller.dto.VideoImportDTO;
import com.gmt.test.external.dto.VideoItemDTO;
import com.gmt.test.external.dto.YouTubeVideoListResponseDTO;
import com.gmt.test.model.VideoMetadata;
import com.gmt.test.model.enuns.Source;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ExternalVideoSourceService {

    @Autowired
    private YoutubeFeignClient youtubeFeignClient;

    @Value("${youtube.api.key}")
    private String YOUTUBE_API_KEY;

    public VideoMetadata importVideo(VideoImportDTO videoImportDTO) {
        //TODO: implement other sources
        VideoMetadata newVideoMetadata = null;

        if (videoImportDTO.getSource().equals(Source.YOUTUBE)) {
            YouTubeVideoListResponseDTO youtubeResponse = youtubeFeignClient.getVideoDetails("snippet,contentDetails", videoImportDTO.getId(), YOUTUBE_API_KEY);

            VideoItemDTO videoItem = youtubeResponse.getItems().getFirst();

            if (videoItem != null) {
                newVideoMetadata = new VideoMetadata();

                newVideoMetadata.setSourceId(videoImportDTO.getId());
                newVideoMetadata.setSource(Source.YOUTUBE);
                newVideoMetadata.setTitle(videoItem.getSnippet().getTitle());
                newVideoMetadata.setDescription(videoItem.getSnippet().getDescription());
                newVideoMetadata.setDuration(videoItem.getContentDetails().getDuration());
                newVideoMetadata.setUploadedAt(videoItem.getSnippet().getPublishedAt());
            }
        } else {
            newVideoMetadata = null;
        }

        return newVideoMetadata;
    }
}