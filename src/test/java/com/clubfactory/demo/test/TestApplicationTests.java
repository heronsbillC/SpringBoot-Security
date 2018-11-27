package com.clubfactory.demo.test;

import com.clubfactory.demo.test.pojo.BookEntity;
import com.clubfactory.demo.test.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TestApplication.class)
@Slf4j
public class TestApplicationTests {

    @Resource
    private BookService bookService;

    @Test
    public void contextLoads() {
        bookService.getBookById(1);
    }

    @Test
    public void testGetBookByAuthor(){
        bookService.getBookByAuthor(99);
    }

    @Test
    public void testAddBook(){
        BookEntity book = BookEntity.builder()
                .name("matlab")
                .author("作者4")
                .price(45.3)
                .build();
        bookService.addBook(book);
    }

    @Test
    public void testDeleteBook(){
        bookService.deleteBook(3);
    }

    @Test
    public void testUpdateBook(){
        BookEntity book = BookEntity.builder()
                .id(1)
                .price(56.7)
                .build();
        bookService.updateBook(book);
    }
}
