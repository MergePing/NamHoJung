import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.net.URLConnection;

@Controller
public class StaticResourceController {

    private final ResourceLoader resourceLoader;

    public StaticResourceController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @GetMapping("/uploads/{date}/{postNo}/{fileName}")
    public ResponseEntity<Resource> getFile(
            @PathVariable String date,
            @PathVariable String postNo,
            @PathVariable String fileName) {

        Resource resource = resourceLoader.getResource("file:src/main/resources/static/uploads/" + date + "/" + postNo + "/" + fileName);

        if (!resource.exists()) {
            return ResponseEntity.notFound().build();
        }

        MediaType mediaType = getMediaTypeForFileName(fileName);

        return ResponseEntity.ok()
                .contentType(mediaType)
                .body(resource);
    }

    private MediaType getMediaTypeForFileName(String fileName) {
        String mimeType = URLConnection.guessContentTypeFromName(fileName);
        return mimeType != null ? MediaType.parseMediaType(mimeType) : MediaType.APPLICATION_OCTET_STREAM;
    }
}
