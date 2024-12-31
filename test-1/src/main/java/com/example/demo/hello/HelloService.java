package com.example.demo.hello;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;




@Service
public class HelloService{
 @Autowired
 private HelloRepository repository;
 
 public Employee getEmployee(String id) {
  Map<String, Object>map = repository.findById(id);
  String employeeId =(String)map.get("id");
  String employeeName =(String)map.get("name");
  int age =(Integer)map.get("age");
  
  Employee employee = new Employee();
  employee.setEmployeeid(employeeId);
  employee.setEmployeename(employeeName);
  employee.setEmployeeAge(age);
  
  return employee;
  
 }
 
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insertData(String tableName, Map<String, Object> data) {
        // テーブルが存在するか確認する
        String checkTableSql = "SELECT COUNT(*) FROM information_schema.tables WHERE table_name = ?";
        int count = jdbcTemplate.queryForObject(checkTableSql, Integer.class, tableName);
        if (count == 0) {
            throw new IllegalArgumentException("テーブルが存在しません");
        }

        // INSERT文を動的に生成
        StringBuilder sql = new StringBuilder("INSERT INTO ");
        sql.append(tableName).append(" (");
        List<String> columnNames = new ArrayList<>(data.keySet());
        sql.append(String.join(",", columnNames)).append(") VALUES (");
        sql.append(Collections.nCopies(columnNames.size(), "?").stream().collect(Collectors.joining(",")));
        sql.append(")");

        // データを挿入
        jdbcTemplate.update(sql.toString(), data.values().toArray());
    }
 
}