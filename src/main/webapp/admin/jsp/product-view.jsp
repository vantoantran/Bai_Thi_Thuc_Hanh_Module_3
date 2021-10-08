<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>Admin Page</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:400,700"/>
    <link rel="stylesheet" href="/admin/css/fontawesome.min.css"/>
    <link rel="stylesheet" href="/admin/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="/admin/css/templatemo-style.css">
</head>
<body id="reportsPage">



<%--BREADCRUMB--%>
<nav>
    <ol class="breadcrumb" style="background-color: #4e657a">
        <li class="breadcrumb-item">
            <a href="/admin-product" style="text-decoration: none; color: #fff">Product</a>
        </li>
        <li class="breadcrumb-item active" aria-current="page">
            <a href="#" style="text-decoration: none; color: #fff">List</a>
        </li>
    </ol>
</nav>

<%--CONTENT--%>
<div class="col-sm-12 col-md-12 col-lg-8 col-xl-8 tm-block-col" style="margin: auto">
    <div class="tm-bg-primary-dark tm-block tm-block-products">
        <h2 class="tm-block-title">Products</h2>

        <%--SEARCH--%>
        <form action="/admin-product">
            <div class="d-flex" style="margin-bottom: 11px">
                <input class="form-control me-3" type="search" placeholder="Search by Name" name="name"
                       aria-label="Search">
                <button type="submit" class="btn btn-primary btn-block text-uppercase mb-1"
                        style="padding: 12.5px; border-width: 1.7px">Search
                </button>
            </div>
        </form>

        <%--LIST--%>
        <div class="tm-product-table-container">
            <table class="table table-hover tm-table-small tm-product-table">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">NAME</th>
                    <th scope="col">PRICE</th>
                    <th scope="col">SIZE</th>
                    <th scope="col">COLOR</th>
                    <th scope="col">CATEGORY</th>
                    <th scope="col">&nbsp;</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${products}" var="product">
                    <tr>
                        <td>
                            <a href="/admin-product?action=product-update&id=${product.id}" style="color: #fff">
                                    ${product.id}
                            </a>
                        </td>
                        <td>
                            <a href="/admin-product?action=product-update&id=${product.id}" style="color: #fff">
                                    ${product.name}
                            </a>
                        </td>
                        <td>
                            <a href="/admin-product?action=product-update&id=${product.id}" style="color: #fff">
                                    ${product.price}
                            </a>
                        </td>
                        <td>
                            <a href="/admin-product?action=product-update&id=${product.id}" style="color: #fff">
                                    ${product.size}
                            </a>
                        </td>
                        <td>
                            <a href="/admin-product?action=product-update&id=${product.id}" style="color: #fff">
                                    ${product.color}
                            </a>
                        </td>
                        <td>
                            <a href="/admin-product?action=product-update&id=${product.id}" style="color: #fff">
                                    ${product.category.name}
                            </a>
                        </td>
                        <td>
                            <a href="/admin-product?action=product-delete&id=${product.id}"
                               class="tm-product-delete-link">
                                <i class="far fa-trash-alt tm-product-delete-icon"></i>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <%--ADD NEW BUTTON--%>
        <a href="/admin-product?action=product-create"
           class="btn btn-primary btn-block text-uppercase mb-3">Add new product
        </a>
    </div>
</div>

<%--FOOTER--%>
<footer class="tm-footer row tm-mt-small">
    <div class="col-12 font-weight-light">
        <p class="text-center text-white mb-0 px-4 small">
            Copyright &copy; <b>2018</b> All rights reserved.
            Design: <a rel="nofollow noopener" href="https://templatemo.com" class="tm-footer-link">Template Mo</a>
        </p>
    </div>
</footer>

<script src="/admin/js/jquery-3.3.1.min.js"></script>
<script src="/admin/js/bootstrap.min.js"></script>
</body>
</html>
