package com.example.demo.hello;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class HelloRepository{
 @Autowired
 private JdbcTemplate jdbcTemplate;
 public Map<String, Object> findById(String id){
  
  //select
  String  sql="SELECT * FROM employee WHERE id = ?";
  //検索結果
  Map<String,Object>employee = jdbcTemplate.queryForMap(sql,id);
  
  return employee;
 }
}