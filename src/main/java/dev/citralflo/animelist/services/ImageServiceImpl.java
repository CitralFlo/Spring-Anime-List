package dev.citralflo.animelist.services;

import dev.citralflo.animelist.model.Series;
import dev.citralflo.animelist.repositories.SeriesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

    SeriesRepository seriesRepository;

    public ImageServiceImpl(SeriesRepository seriesRepository) {
        this.seriesRepository = seriesRepository;
    }


    @Override
    @Transactional
    public void saveImageFile(Long id, MultipartFile file) {

        try {
            Series series = seriesRepository.findById(id).get();

            Byte[] byteObjects = new Byte[file.getBytes().length];

            int i = 0;

            for (byte b : file.getBytes()) {
                byteObjects[i++] = b;
            }

            series.setImage(byteObjects);

            seriesRepository.save(series);
        } catch (Exception e) {
            log.error("Error occurred", e);

            e.printStackTrace();
        }
    }
}
