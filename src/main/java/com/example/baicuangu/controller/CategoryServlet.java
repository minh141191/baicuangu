package com.example.baicuangu.controller;

import com.example.baicuangu.model.Category;
import com.example.baicuangu.model.Product;
import com.example.baicuangu.service.CategoryService;
import com.example.baicuangu.service.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CategoryServlet", value = "/category")
public class CategoryServlet extends HttpServlet {
    private final CategoryService categoryService = CategoryService.getInstance();
    private final ProductService productService = ProductService.getInstance();
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
        }
    }

    private void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> productList = ProductService.getInstance().getProducts();
        List<Category> categoryList = CategoryService.getInstance().getCategoryList();
        for (Category category : categoryList) {
            int i = 0;
            for (Product product : productList) {
                if (category.getName().equals(product.getCategory().getName())) {
                    ++i;
                    category.setProductSum(i);
                }
            }
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/category/home.jsp");
        request.setAttribute("category", categoryService.getCategoryList());
        requestDispatcher.forward(request, response);
    }

    private void createGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("/category/create.jsp");
    }

    private void createPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");

        Category category = new Category(id, name);
        categoryService.addClasses(category);
        response.sendRedirect("/category");
    }

    private void updateGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        Category category = categoryService.getById(id);

        if (category != null) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/category/update.jsp");
            request.setAttribute("category", category);
            requestDispatcher.forward(request, response);
        } else {
            response.sendRedirect("/404.jsp");
        }
    }

    private void updatePost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");

        Category category = categoryService.getById(id);

        if (category != null) {
            category.setName(name);
            response.sendRedirect("/category");
        } else {
            response.sendRedirect("/404.jsp");
        }
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        Category category = categoryService.getById(id);
        if (category != null) {
            categoryService.deleteById(id);
            productService.deleteByCategory(category);
            response.sendRedirect("/category");
        } else {
            response.sendRedirect("/404.jsp");
        }
    }
}
