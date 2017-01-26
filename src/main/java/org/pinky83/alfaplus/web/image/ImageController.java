package org.pinky83.alfaplus.web.image;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Дмитрий on 26.01.2017.
 *
 */
@Controller
@RequestMapping(value = ImageController.URL)
public class ImageController extends AbstractImageController{
    static final String URL = "/images";

    @GetMapping(value = "/last")
    public String getLastDay(Model model) {
        model.addAttribute("imageList", super.getLastDay());
        return "imageList";
    }

    @GetMapping(value = "/next/{id}")
    public String getNextPage(Model model,@PathVariable("id") int lastId) {
        model.addAttribute("imageList", super.getNextPage(lastId));
        return "imageList";
    }

    @GetMapping(value = "/previous/{id}")
    public String getPreviousPage(Model model, @PathVariable("id") int firstId) {
        model.addAttribute("imageList", super.getPreviousPage(firstId));
        return "imageList";
    }
}
