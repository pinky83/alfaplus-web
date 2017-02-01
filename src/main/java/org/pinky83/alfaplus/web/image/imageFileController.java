package org.pinky83.alfaplus.web.image;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

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

    @GetMapping(value = "/{id}")
    public void getImageFile(@PathVariable String id, HttpServletResponse response, HttpServletRequest request) throws IOException{

        String filename = "D:" + File.separatorChar + "alfaplus" + File.separatorChar + "Images" + File.separatorChar + id + ".jpg";

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
