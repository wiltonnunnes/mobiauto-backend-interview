package com.wilton.mobiauto_backend_interview.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wilton.mobiauto_backend_interview.dto.VideoDTO;
import com.wilton.mobiauto_backend_interview.entity.Video;
import com.wilton.mobiauto_backend_interview.repository.VideoRepository;

import jakarta.annotation.PostConstruct;

@RestController 
@RequestMapping("/videos")
public class VideoController {

    @Autowired 
    private ModelMapper modelMapper;

    @Autowired
    private VideoRepository repository;

    @PostConstruct 
    private void postConstruct() {
        TypeMap<Video, VideoDTO> propertyMapper = this.modelMapper.createTypeMap(Video.class, VideoDTO.class);
        Converter<Long, String> converter = c -> encodeId(c.getSource()); 
        propertyMapper.addMappings(
            mapper -> mapper.using(converter).map(Video::getId, VideoDTO::setVideoId)
        );
    }

    @GetMapping 
    public List<VideoDTO> getVideos() {
        return repository.findAll().stream().map(video -> modelMapper.map(video, VideoDTO.class)).collect(Collectors.toList());
    }

    private static String encodeId(Long id) {
        String out = "";
        String index = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int base = index.length();

        for(int t = (int) (id != 0 ? Math.floor(customLog(base, id)) : 0); t >= 0; t--) {
            double bcp = Math.pow(base, t);
            double a = Math.floor(id / bcp) % base;
            out += index.substring((int) a, (int) (a + 1));
            id -= (int) (a * bcp);
        }

        return out;
    }

    private static double customLog(double base, double logNumber) {
        return Math.log(logNumber) / Math.log(base);
    }

    private static Long decodeId(String id) {
        Long out = 0L;
        String index = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int base = index.length();

        int len = id.length() - 1;

        for (int t = len; t >= 0; t--) {
            double bcp = Math.pow(base, t - 1);
            out += (int) (index.indexOf(index.charAt(t)) * bcp);
        }

        return out;
    }
}
