package com.example.baicuangu.controller;

import com.example.baicuangu.model.Category;
import com.example.baicuangu.model.Product;
import com.example.baicuangu.service.CategoryService;
import com.example.baicuangu.service.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet(name = "ProductServlet", value = "/products")
public class ProductServlet extends HttpServlet {
    private final ProductService productService = ProductService.getInstance();
    private final CategoryService categoryService = CategoryService.getInstance();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                createGet(request, response);
                break;
            case "update":
                updateGet(request, response);
                break;
            case "delete":
                delete(request, response);
                break;
            default:
                findAll(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                createPost(request, response);
                break;
            case "update":
                updatePost(request, response);
                break;
            case "search":
                search(request, response);
                break;
        }
    }

    private void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("products", productService.getProducts());
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/product/home.jsp");
        requestDispatcher.forward(request, response);
    }

    private void createGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("category", categoryService.getCategoryList());
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/product/create.jsp");
        requestDispatcher.forward(request, response);
    }

    private void createPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        Double price = Double.parseDouble(request.getParameter("price"));
        String timeString = request.getParameter("outOfDate");
        LocalDateTime outOfDate = LocalDateTime.parse(timeString, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        int categoryId = Integer.parseInt(request.getParameter("category"));

        Category category = categoryService.getById(categoryId);
        if (category != null) {
            Product product = new Product(id, name, price, outOfDate, category);
            productService.addProduct(product);
            response.sendRedirect("/products");
        } else {
            response.sendRedirect("/404.jsp");
        }
    }

    private void updateGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productService.getById(id);
        if (product != null) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/product/update.jsp");
            request.setAttribute("product", product);
            request.setAttribute("category", categoryService.getCategoryList());
            requestDispatcher.forward(request, response);
        } else {
            response.sendRedirect("/404.jsp");
        }
    }
    private void updatePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        Double price = Double.parseDouble(request.getParameter("price"));
        String timeString = request.getParameter("outOfDate");
        LocalDateTime outOfDate = LocalDateTime.parse(timeString, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        int categoryId = Integer.parseInt(request.getParameter("category"));

        Category category = categoryService.getById(categoryId);
        Product product = productService.getById(id);

        if (product != null && category != null) {
            product.setName(name);
            product.setPrice(price);
            product.setOutOfDate(outOfDate);
            product.setCategory(category);
            response.sendRedirect("/products");
        } else {
            response.sendRedirect("/404.jsp");
        }
    }
    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        productService.deleteById(id);
        response.sendRedirect("/products");
    }
    private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String search = request.getParameter("search");
        List<Product> products = productService.searchByName(search);
        request.setAttribute("products", products);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/product/home.jsp");
        requestDispatcher.forward(request, response);
    }
}
