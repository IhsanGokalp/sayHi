package kg.zeroone.java.sayHi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
public class TheController {

    @Value("${welcome.message}")
    String message;

    @Value("${title}")
    String title;

    public Map<Integer, Student> studentMap = new HashMap<Integer, Student>() {{
        put(1, new Student(1,"John", "Doe", 20));
        put(2, new Student(2,"Asan", "Uson", 18));
    }};

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("students", studentMap);
        model.addAttribute("message", message);
        model.addAttribute("title", title);
        return "index";
    }

    @GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("message", message);
        model.addAttribute("title", title);
        return "contact";
    }

    @GetMapping("/hello")
    public String about(@RequestParam("name") String name, @RequestParam("age") int age, Model model) {
        model.addAttribute("name", name);
        model.addAttribute("age", age);
        model.addAttribute("message", message);
        model.addAttribute("title", title);
        return "hello";
    }

    @GetMapping("/detail")
    public String details(Model model, @RequestParam("id") int id) {
        Student std = studentMap.get(id);
        model.addAttribute("student", std);
        model.addAttribute("title", title);
        return "detail";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("title",title);
        return "add";
    }

    @PostMapping("/add")
    public String addStdPosted(@ModelAttribute Student student, Model model) {
        studentMap.put(student.getId(), student);
        model.addAttribute("title", title);
        model.addAttribute("student", student);
        model.addAttribute("message", "Added Student Details:");
        return "detail";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") int id, Model model) {
        Student std = studentMap.get(id);
        studentMap.remove(id);
        model.addAttribute("student", std);
        model.addAttribute("title", title);
        model.addAttribute("message", "Deleted Student Details:");
        return "detail";
    }

    @GetMapping("/update")
    public String update(Model model, @RequestParam("id") int id) {
        model.addAttribute("student", studentMap.get(id));
        model.addAttribute("title", title);
        model.addAttribute("message", "Student Updater:");
        return "update";
    }

    @PostMapping("/update")
    public String updateStdPosted(Model model, @ModelAttribute Student student){
        studentMap.remove(student.getId());
        studentMap.put(student.getId(),student);
        model.addAttribute("title", title);
        model.addAttribute("student", student);
        model.addAttribute("message", "Updated Student Details:");
        return "detail";
    }

}
