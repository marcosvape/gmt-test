package com.gmt.test.controller;

import com.gmt.test.controller.dto.FilterDTO;
import com.gmt.test.controller.dto.VideoImportDTO;
import com.gmt.test.controller.dto.VideoMetadataStatsDTO;
import com.gmt.test.model.VideoMetadata;
import com.gmt.test.model.enuns.Source;
import com.gmt.test.service.VideoMetadataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/videos")
public class VideoMetadataController {

    @Autowired
    public VideoMetadataService videoMetadataService;

    @PostMapping("/import")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> importVideo(@RequestBody VideoImportDTO videoImportDTO) {

        VideoMetadata rtn = this.videoMetadataService.importVideo(videoImportDTO);

        return new ResponseEntity<>(rtn, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> get(@RequestBody(required = false) FilterDTO filterDTO) {

        List<VideoMetadata> rtn = this.videoMetadataService.getAll(filterDTO);

        return new ResponseEntity<>(rtn, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable(name = "id") Integer id) {

        VideoMetadata rtn = this.videoMetadataService.getById(id);

        return new ResponseEntity<>(rtn, HttpStatus.OK);
    }

    @GetMapping("/stats")
    public ResponseEntity<?> getVideoMetadataStatsById(@RequestParam(name = "source") String source) {

        VideoMetadataStatsDTO rtn = this.videoMetadataService.getVideoMetadataStatsBySource(Source.valueOf(source));

        return new ResponseEntity<>(rtn, HttpStatus.OK);
    }

}
