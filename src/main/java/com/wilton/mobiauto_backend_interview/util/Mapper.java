package com.wilton.mobiauto_backend_interview.util;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wilton.mobiauto_backend_interview.dto.UserDTO;
import com.wilton.mobiauto_backend_interview.dto.VideoDTO;
import com.wilton.mobiauto_backend_interview.entity.Video;

@Component
public class Mapper {

    @Autowired
    private ModelMapper modelMapper;
    
    public VideoDTO toDto(Video video) {
        String id = encodeId(video.getId());
        UserDTO user = modelMapper.map(video.getUser(), UserDTO.class);

        return new VideoDTO(id, video.getTitle(), user);
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
}
