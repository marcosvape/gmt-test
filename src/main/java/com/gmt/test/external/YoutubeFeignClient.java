package com.gmt.test.external;

import com.gmt.test.external.dto.YouTubeVideoListResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "youtubeClient", url = "https://www.googleapis.com/youtube/v3")
public interface YoutubeFeignClient {
    @GetMapping("/videos")
    YouTubeVideoListResponseDTO getVideoDetails(@RequestParam("part") String part, @RequestParam("id") String videoId, @RequestParam("key") String apiKey);
}