package com.cg.module_4_exam.controller;

import com.cg.module_4_exam.model.ThanhPho;
import com.cg.module_4_exam.service.IThanhPhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("")
public class ThanhPhoController {

    @Autowired
    private IThanhPhoService thanhPhoService;


    @GetMapping("")
    public String index(Model model) {
        List<ThanhPho> thanhPhos = thanhPhoService.findAll();
        model.addAttribute("thanhPhos", thanhPhos);
        return "index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("thanhpho", new ThanhPho());
        return "/create";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Optional<ThanhPho> thanhPhoOptional = thanhPhoService.findById(id);
        if (!thanhPhoOptional.isPresent()) {
            return "error/404";
        }
        model.addAttribute("thanhpho", thanhPhoService.findById(id));
        return "/edit";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Model model) {

        Optional<ThanhPho> thanhPhoOptional = thanhPhoService.findById(id);
        if (!thanhPhoOptional.isPresent()) {
            return "error/404";
        }

        model.addAttribute("thanhpho", thanhPhoService.findById(id));
        return "/delete";
    }

    @GetMapping("/view/{id}")
    public String view(@PathVariable Long id, Model model) {
        model.addAttribute("thanhpho", thanhPhoService.findById(id));
        return "/view";
    }

    @PostMapping("/create")
    public String save(RedirectAttributes redirect,
                       Model model,
                       @Validated @ModelAttribute ThanhPho thanhpho,
                       BindingResult bindingResult
                       ) {



        if (bindingResult.hasFieldErrors()) {
            model.addAttribute("errors", true);
            model.addAttribute("thanhpho", thanhpho);
            model.addAttribute("fail", "Thêm thanh phố thất bại");
            return "/create";
        }

        thanhpho.setId((long) (Math.random() * 10000));
        thanhPhoService.save(thanhpho);
        redirect.addFlashAttribute("success", "Them thanh pho thanh cong!");
        return "redirect:/";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable Long id,
                         @ModelAttribute ThanhPho thanhpho,
                         Model model,
                         RedirectAttributes redirect,
                         BindingResult bindingResult
                         ) {

        Optional<ThanhPho> thanhPhoOptional = thanhPhoService.findById(id);

        if (!thanhPhoOptional.isPresent()) {
            return "error/404";
        }

        if (bindingResult.hasFieldErrors()) {
            model.addAttribute("errors", true);
            model.addAttribute("thanhpho", thanhpho);
            model.addAttribute("fail", "Sua thanh phố thất bại");
            return "/edit";
        }

        thanhpho.setId(id);
        thanhPhoService.save(thanhpho);
        redirect.addFlashAttribute("success", "Sửa thanh pho thanh cong!");
        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String delete(ThanhPho thanhpho, RedirectAttributes redirect) {
        thanhPhoService.deleteById(thanhpho.getId());
        redirect.addFlashAttribute("success", "Xoa thanh pho thanh cong!");
        return "redirect:/";
    }




}
