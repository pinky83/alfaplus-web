package org.pinky83.alfaplus.web.image;

import org.pinky83.alfaplus.configuration.ApplicationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import static org.pinky83.alfaplus.web.image.imageFileController.URL;

/**
 * Created by Дмитрий on 01.02.2017.
 *
 * Browser not allow to get images directly from filesystem, so
 * we need to рфтвду this resource requests by specialized controller
 */
@Controller
@RequestMapping(URL)
public class imageFileController {
    static final String URL = "/thumb";
    String pathPart;

	@Autowired
	public imageFileController(ApplicationProperties properties) {
		pathPart = properties.getProperty("file.path") + "alfaplus" + File.separatorChar + "Images" + File.separatorChar;
	}

	@GetMapping(value = "/{id}")
    public void getImageFile(@PathVariable String id, HttpServletResponse response) throws IOException{
        String filename = pathPart + id + ".jpg";
        response.setContentType("image/jpeg");
        File image = new File(filename);
        response.setContentLength((int)image.length());

        FileInputStream in = new FileInputStream(filename);
        OutputStream out = response.getOutputStream();

        byte[] buf = new byte[1024];
        int count;
        while ((count = in.read(buf)) >= 0) {
            out.write(buf, 0, count);
        }
        out.close();
        in.close();
    }
}
