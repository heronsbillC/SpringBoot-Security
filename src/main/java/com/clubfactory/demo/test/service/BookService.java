package com.clubfactory.demo.test.service;

import com.clubfactory.demo.test.mapper.BookMapper;
import com.clubfactory.demo.test.pojo.BookEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class BookService {

    @Autowired
    private BookMapper bookMapper;

    public void getBookById(int id){
        BookEntity book = bookMapper.selectByPrimaryKey(id);
        log.info("BookService.getBookById->book = " + book);
    }

    public void getBookByAuthor(double price){
        Map<String,Object> bookMap = new HashMap<>();
        bookMap.put("price",price);
        List<BookEntity> bookList = new ArrayList<>();
        bookList = bookMapper.selectListByParams(bookMap);
        if(bookList.size() != 0){
            bookList.stream().forEach(System.out::println);
        }
        log.info("BookService.getBookByAuthor->book = " + bookList);
    }

    public void addBook(BookEntity book){
        String message = null;
        int result = bookMapper.insertSelective(book);
        if(result > 0)
            message = "添加成功";
        else
            message = "添加失败";
        log.info("BookService.addBook->message = " + message );
    }

    public void deleteBook(int id){
        String message = null;
        int result = bookMapper.deleteByPrimaryKey(id);
        if(result > 0)
            message = "删除成功";
        else
            message = "删除失败";
        log.info("BookService.deleteBook->message = " + message );
    }

    public void updateBook(BookEntity book){
        String message = null;
        int result = bookMapper.updateByPrimaryKeySelective(book);
        if(result > 0)
            message = "修改成功";
        else
            message = "修改失败";
        log.info("BookService.deleteBook->message = " + message );
    }
}
