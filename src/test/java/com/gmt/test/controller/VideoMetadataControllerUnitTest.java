package com.gmt.test.controller;

import com.gmt.test.controller.dto.VideoImportDTO;
import com.gmt.test.model.VideoMetadata;
import com.gmt.test.service.VideoMetadataService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class VideoMetadataControllerUnitTest {

    private VideoMetadataController controller;
    private VideoMetadataService videoMetadataService;

    @BeforeEach
    void setUp() {
        videoMetadataService = mock(VideoMetadataService.class);
        controller = new VideoMetadataController();
        controller.videoMetadataService = videoMetadataService; // field injection for test
    }

    @Test
    void shouldImportVideoAndReturnResponse() {
        // Arrange
        VideoImportDTO dto = new VideoImportDTO();
        dto.setId("abc123");

        VideoMetadata mockResponse = new VideoMetadata();
        mockResponse.setId(1);
        mockResponse.setTitle("Test Video");

        when(videoMetadataService.importVideo(dto)).thenReturn(mockResponse);

        // Act
        ResponseEntity<?> response = controller.importVideo(dto);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof VideoMetadata);

        VideoMetadata result = (VideoMetadata) response.getBody();
        assertEquals(1, result.getId());
        assertEquals("Test Video", result.getTitle());

        verify(videoMetadataService, times(1)).importVideo(dto);
    }
}
