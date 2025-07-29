package com.gmt.test.service;

import com.gmt.test.controller.dto.FilterDTO;
import com.gmt.test.controller.dto.VideoImportDTO;
import com.gmt.test.controller.dto.VideoMetadataStatsDTO;
import com.gmt.test.dao.VideoMetadataDAO;
import com.gmt.test.external.ExternalVideoSourceService;
import com.gmt.test.model.VideoMetadata;
import com.gmt.test.model.enuns.Source;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class VideoMetadataServiceTest {

    private ExternalVideoSourceService externalVideoSourceService;
    private VideoMetadataDAO videoMetadataDAO;
    private VideoMetadataService service;

    @BeforeEach
    void setUp() {
        externalVideoSourceService = mock(ExternalVideoSourceService.class);
        videoMetadataDAO = mock(VideoMetadataDAO.class);
        service = new VideoMetadataService();

        // Inject mocks manually (field injection)
        service.externalVideoSourceService = externalVideoSourceService;
        service.videoMetadataDAO = videoMetadataDAO;
    }

    @Test
    void shouldImportVideoSuccessfully() {
        VideoImportDTO dto = new VideoImportDTO();
        dto.setId("abc");

        VideoMetadata imported = new VideoMetadata();
        VideoMetadata saved = new VideoMetadata();
        saved.setId(10);

        when(externalVideoSourceService.importVideo(dto)).thenReturn(imported);
        when(videoMetadataDAO.createVideoMetadata(imported)).thenReturn(saved);

        VideoMetadata result = service.importVideo(dto);

        assertEquals(10, result.getId());
        verify(externalVideoSourceService).importVideo(dto);
        verify(videoMetadataDAO).createVideoMetadata(imported);
    }

    @Test
    void shouldFetchAllVideos() {
        FilterDTO filter = new FilterDTO();
        List<VideoMetadata> list = Collections.singletonList(new VideoMetadata());

        when(videoMetadataDAO.fetchVideosMetadata(filter)).thenReturn(list);

        List<VideoMetadata> result = service.getAll(filter);

        assertEquals(1, result.size());
        verify(videoMetadataDAO).fetchVideosMetadata(filter);
    }

    @Test
    void shouldFetchVideoById() {
        int id = 42;
        VideoMetadata video = new VideoMetadata();
        video.setId(id);

        when(videoMetadataDAO.fetchVideoMetadata(id)).thenReturn(video);

        VideoMetadata result = service.getById(id);

        assertEquals(42, result.getId());
        verify(videoMetadataDAO).fetchVideoMetadata(id);
    }

    @Test
    void shouldFetchVideoStatsBySource() {
        Source source = Source.YOUTUBE;
        VideoMetadataStatsDTO stats = new VideoMetadataStatsDTO();
        stats.setSource(String.valueOf(source));

        when(videoMetadataDAO.fetchVideosMetadataStatsBySource(source)).thenReturn(stats);

        VideoMetadataStatsDTO result = service.getVideoMetadataStatsBySource(source);

        assertEquals("YOUTUBE", result.getSource());
        verify(videoMetadataDAO).fetchVideosMetadataStatsBySource(source);
    }
}
