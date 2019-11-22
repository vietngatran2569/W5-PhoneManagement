package controller;

import model.Phone;
import model.Category;
import service.PhoneService;
import service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class PhoneController {

    @Autowired
    PhoneService phoneService;
    @Autowired
    CategoryService categoryService;

    @ModelAttribute("categories")
    public Iterable<Category> categories(){
        return categoryService.findAll();
    }
    @GetMapping("/phone")
    public ModelAndView listEmplyee(@RequestParam("s") Optional<String> s, @PageableDefault(size = 5) Pageable pageable){
        Page<Phone> phones;;
        if (s.isPresent()){
            phones=phoneService.findAllByNameContaining(s.get(),pageable);
        }else {
            phones=phoneService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/phone/list");
        modelAndView.addObject("phones", phones);

        return modelAndView;
    }
    @GetMapping("/create-phone")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView=new ModelAndView("/phone/create");
        modelAndView.addObject("phone", new Phone());
        return modelAndView;
    }
    @PostMapping("/create-phone")
    public ModelAndView saveCustomer(@ModelAttribute("phone") Phone phone){
        phoneService.save(phone);
        ModelAndView modelAndView = new ModelAndView("/phone/create");
        modelAndView.addObject("phone", new Phone());
        modelAndView.addObject("message", "New PHONE created successfully");
        return modelAndView;
    }

    @GetMapping("/edit-phone/{id}")
    public ModelAndView showEditForm(@PathVariable Long id){
        Phone phone=phoneService.findById(id);
        if (phone!=null){
            ModelAndView modelAndView=new ModelAndView("/phone/edit");
            modelAndView.addObject("phone",phone);
            return modelAndView;
        }else{
            ModelAndView modelAndView=new ModelAndView("/error.404");
            return modelAndView;
        }
    }
    @PostMapping("/edit-phone")
    public ModelAndView updateEm(@ModelAttribute("phone") Phone phone){
        phoneService.save(phone);
        ModelAndView modelAndView=new ModelAndView("/phone/edit");
        modelAndView.addObject("phone",phone);
        modelAndView.addObject("message","Phone Update succeddful");
        return modelAndView;
    }
    @GetMapping("/delete-phone/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        Phone phone = phoneService.findById(id);
        if(phone != null) {
            ModelAndView modelAndView = new ModelAndView("/phone/delete");
            modelAndView.addObject("phone", phone);
            return modelAndView;

        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/delete-phone")
    public String deleteEm(@ModelAttribute("phone") Phone phone){
        phoneService.remove(phone.getId());
        return "redirect:phone";
    }
    @GetMapping("/searchByCategory")
    public ModelAndView getBookByCategory(@RequestParam("search") String search, Pageable pageable){
        Category category = categoryService.findById(Long.parseLong(search));
        Page<Phone> phones = phoneService.findAllByCategory(pageable,category);
        ModelAndView modelAndView = new ModelAndView("/phone/list");
        modelAndView.addObject("phones",phones);
        modelAndView.addObject("search",search);
        return modelAndView;
    }

    @GetMapping("/sortByPriceAsc")
    public ModelAndView getBookSortByPriceAsc(Pageable pageable){
        Page<Phone> phones = phoneService.findAllByOrderByPriceAsc(pageable);
        ModelAndView modelAndView = new ModelAndView("/phone/list");
        modelAndView.addObject("phones",phones);
        return modelAndView;
    }

    @GetMapping("/sortByPriceDesc")
    public ModelAndView getBookSortByPriceDesc(Pageable pageable){
        Page<Phone> phones = phoneService.findAllByOrderByPriceDesc(pageable);
        ModelAndView modelAndView = new ModelAndView("/phone/list");
        modelAndView.addObject("phones",phones);
        return modelAndView;
    }


}

//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//
//@Controller
//class PhoneControler {
//    @GetMapping("index")
//    public String index(){
//        return "/index";
//    }
//
//}
