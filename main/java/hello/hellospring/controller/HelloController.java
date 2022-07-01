package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "spring");
        return "hello"; //templates 파일의 hello.html을 찾아서 실행
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name);
        return "hello-tamplate";
    }

    @GetMapping("hello-string")
    @ResponseBody //템플릿 파일의 body에 return의 내용을 직접 넣음
    public String helloString(@RequestParam("name") String name){
        return "hello " + name;
    }

    @GetMapping("hello-API")
    @ResponseBody
    public hello helloApi(@RequestParam("name") String name) {
        hello hello = new hello();
        hello.setName(name);
        return hello;
    }

    static class hello {
        private String name;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }
}