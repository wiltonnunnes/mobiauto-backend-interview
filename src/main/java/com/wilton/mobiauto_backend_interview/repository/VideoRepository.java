import org.springframework.data.jpa.repository.JpaRepository;

import com.wilton.mobiauto_backend_interview.entity.Video;

public interface VideoRepository extends JpaRepository<Video, Long> {
    
}
