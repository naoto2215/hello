package com.example.demo.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

 @Controller
 
 public class HelloController {
  
  @Autowired
  public HelloService service;
  
   @GetMapping("/hello")
   public String gethello() {
    //hello.htmlに画面移動
    return "hello";
   }
   
   @PostMapping("/hello")
   public String postText1Request(@RequestParam("text1") String text1, Model model) {
 
       model.addAttribute("sample", text1);
       return "hello/response"; // text1に対応するテンプレートに遷移
   }
   
   @PostMapping("hello/db")
   
   public String postDbRequestid(@RequestParam("text2")String id,Model model) {
    Employee employee =service.getEmployee(id);
    model.addAttribute("employee",employee);
    return "hello/db";
   }
     @Autowired
     private JdbcTemplate jdbcTemplate;

     // ... (他のメソッドは省略)

     @PostMapping("/create_table")
     public String createTable(@RequestParam("tableName") String tableName,
                               @RequestParam("column1") String column1,
                               @RequestParam("column2") String column2,
                               Model model) {
      try {
             // カラム名とデータ型を適切に指定する
             String sql = "CREATE TABLE " + tableName + " (" + column1 + " varchar(255), " + column2 + " varchar(255))";
             jdbcTemplate.execute(sql);
             model.addAttribute("message", "テーブルを作成しました");
         }finally {
         
         }
         return "/hello/table";
     }
 }