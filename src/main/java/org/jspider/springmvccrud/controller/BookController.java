package org.jspider.springmvccrud.controller;

import domain.Book;
import domain.Login;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
@Controller
public class BookController {
    boolean status;
    List<Login> logins = new ArrayList<>();

    {
        logins.add(new Login("juned1997@gmail.com","1234"));
    }
    List<Book> bookList = new ArrayList<>();

    {
        bookList.add(new Book(1, "SQL", 2500.01, "EDUCATION"));
        bookList.add(new Book(2, "JAVA", 3500.01, "EDUCATION"));
        bookList.add(new Book(3, "WEBTECH", 4500.01, "EDUCATION"));
        bookList.add(new Book(4, "MONGODB", 1500.01, "EDUCATION"));
        bookList.add(new Book(5, "API", 5240.01, "EDUCATION"));
        bookList.add(new Book(4, "SELENIUM", 3421.01, "EDUCATION"));

    }

    @GetMapping("books")
    public String showBook(Model model) {
        model.addAttribute("books", bookList);
        return "books";
    }

    @GetMapping("/addbook")
    public String viewAddBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "addbook.html";
    }

    @PostMapping("/savebook")
    public String addbook(Book b) {
        bookList.add(b);
        return "redirect:books";
    }
    @GetMapping("/updateform/{id}")
    public String showUpdateForm(@PathVariable int id, Model model)
    {
       for(Book b : bookList)
       {
           if(b.getBookId()==id)
           {
               model.addAttribute("book",b);
           }
       }
        return "updateform";
    }
@PostMapping("/modifybook")
    public String ModifyBookDetails(Book b) {
//        for(Book book: bookList)
//        {
//            if(book.getBookId()==b.getBookId())
//            {
//                book.setBookName(b.getBookName());
//                book.setBookPrice(b.getBookPrice());
//                book.setBookCategory(b.getBookCategory());
//            }
//        }
    for (int i = 0; i < bookList.size(); i++)
    {
        if (bookList.get(i).getBookId()==b.getBookId())
        {
            bookList.set(i,b);
        }
    }
        return "redirect:/books";
    }
    @GetMapping("/deletebook/{id}")
    public String DeleteBook(@PathVariable int id, Model model){
        for (int i = 0; i < bookList.size(); i++)
        {
            if (bookList.get(i).getBookId()==id)
            {
                model.addAttribute(bookList.remove(i));
            }
        }
        return "redirect:books";
    }
    @GetMapping("/sortname")
    public String sortByName(Model model)
    {
        Collections.sort(bookList,Comparator.comparing(Book::getBookName));
        return "redirect:books";
    }
@GetMapping("/sortprice")
    public String sortByPrice(Model model)
    {
        Collections.sort(bookList,Comparator.comparing(Book::getBookPrice));
        return "redirect:books";
    }
@GetMapping("/loginform")
    public String showLoginForm(Model model){
    model.addAttribute("login",new Login());
    return "login";
    }
@PostMapping("/login")
    public String login(Login login)
    {
        status = false;
        for (Login l : logins)
        {
            if ((l.getEmail().equals(login.getEmail()))&&(l.getPassword().equals(login.getPassword()))) {
                status = true;
            }
            else {
              status = false;
            }
        }
        if (status=true)
        {
            return "redirect:books";
        }
        else {
            return "redirect:loginform";
        }
    }

    @GetMapping("/logout")
    public String logout(Model model)
    {
        return "redirect:loginform";
    }
}