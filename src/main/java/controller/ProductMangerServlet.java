package controller;

import model.Category;
import model.Product;
import service.category.CategoryService;
import service.category.ICategoryService;
import service.product.IProductService;
import service.product.ProductService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "controller.AdminProductServlet", value = "/admin-product")
public class ProductMangerServlet extends HttpServlet {
    IProductService productService = new ProductService();
    ICategoryService categoryService = new CategoryService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "product-create": {
                showProductCreateForm(request, response);
                break;
            }
            case "product-update": {
                showProductUpdateForm(request, response);
                break;
            }
            case "product-delete": {
                deleteProduct(request, response);
                break;
            }
            default: {
                showProductView(request, response);
            }
        }
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productService.findById(id);
        if (product == null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("error-404.jsp");
        } else {
            productService.delete(id);
            try {
                response.sendRedirect("/admin-product");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void showProductUpdateForm(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productService.findById(id);
        RequestDispatcher dispatcher;
        if (product == null) {
            dispatcher = request.getRequestDispatcher("error-404.jsp");
        } else {
            request.setAttribute("product", product);
            List<Category> categories = categoryService.show();
            request.setAttribute("categories", categories);
            dispatcher = request.getRequestDispatcher("admin/jsp/product-update.jsp");
        }
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void showProductCreateForm(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("admin/jsp/product-create.jsp");
        try {
            List<Category> categories = categoryService.show();
            request.setAttribute("categories", categories);
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void showProductView(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        List<Product> products;
        if (name == null || name.equals("")) {
            products = productService.show();
        } else {
            products = productService.searchProductByName(name);
        }
        request.setAttribute("products", products);
        RequestDispatcher dispatcher = request.getRequestDispatcher("admin/jsp/product-view.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "product-create": {
                createNewProduct(request, response);
                break;
            }
            case "product-update": {
                updateProductInfo(request, response);
                break;
            }
        }
    }

    private void updateProductInfo(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String size = request.getParameter("size");
        String image = request.getParameter("image");
        double price = Double.parseDouble(request.getParameter("price"));
        String color = request.getParameter("color");
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        String description = request.getParameter("description");
        productService.update(id, new Product(name, size, image, price,color, categoryId, description));
        List<Category> categories = categoryService.show();
        request.setAttribute("categories", categories);
        try {
            response.sendRedirect("/admin-product");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createNewProduct(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String size = request.getParameter("size");
        String image = request.getParameter("image");
        double price = Double.parseDouble(request.getParameter("price"));
        String color = request.getParameter("color");
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        String description = request.getParameter("description");
        productService.create(new Product(name, size, image, price,color, categoryId, description));
        List<Category> categories = categoryService.show();
        request.setAttribute("categories", categories);
        try {
            response.sendRedirect("/admin-product");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
