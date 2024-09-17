package school.sptech.neosspringjava.domain.repository.imageRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import school.sptech.neosspringjava.domain.model.image.Image;

public interface ImageRepository extends JpaRepository<Image, Integer> {
    
}
