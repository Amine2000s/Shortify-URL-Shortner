
<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>URL Shortener</title>
  <!--  <link rel="stylesheet" th:href="@{/css/styles.css}" />
    <script th:src="@{/js/scripts.js}"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/js/all.min.js"></script> -->
</head>
<style>
    /* General Styling */
    body {
        font-family: 'Poppins', sans-serif;
        margin: 0;
        padding: 0;
        background-color: #f4f4f4;
        color: #333;
    }

    /* Dashboard Container */
    .dashboard-container {
        display: flex;
        height: 100vh;
    }

    /* Sidebar Styling */
    .sidebar {
        width: 260px;
        background: #1e1e2f;
        color: white;
        padding: 20px;
        display: flex;
        flex-direction: column;
        gap: 15px;
        box-shadow: 2px 0 5px rgba(0, 0, 0, 0.2);
    }

    .sidebar h3 {
        font-size: 1.5em;
        text-align: center;
        margin-bottom: 20px;
        font-weight: 600;
    }

    .sidebar ul {
        list-style: none;
        padding: 0;
    }

    .sidebar ul li {
        margin: 10px 0;
    }

    .sidebar ul li a {
        color: white;
        text-decoration: none;
        display: block;
        padding: 12px;
        border-radius: 8px;
        transition: all 0.3s ease-in-out;
        font-size: 1.1em;
        text-align: center;
    }

    .sidebar ul li a:hover {
        background: #2f2f46;
    }

    /* Content Area */
    .content {
        flex-grow: 1;
        padding: 25px;
        background-color: #fff;
        overflow-y: auto;
        border-radius: 15px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        margin: 20px;
    }

    /* Overview Section */
    #overview {
        background: #e3f2fd;
        padding: 20px;
        border-radius: 12px;
        margin-bottom: 25px;
        box-shadow: 0 3px 5px rgba(0, 0, 0, 0.1);
    }

    #overview h2 {
        margin-bottom: 15px;
        font-weight: 600;
    }

    #overview p {
        font-size: 1.1em;
    }

    /* Table Styling */
    table {
        width: 100%;
        border-collapse: collapse;
        background: white;
        box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        border-radius: 10px;
        overflow: hidden;
    }

    table, th, td {
        border: none;
    }

    th, td {
        padding: 12px;
        text-align: left;
        font-size: 1em;
    }

    th {
        background: #007bff;
        color: white;
        font-weight: 600;
    }

    tbody tr:nth-child(even) {
        background-color: #f8f9fa;
    }

    tbody tr:hover {
        background-color: #e2e6ea;
        transition: 0.3s ease-in-out;
    }

    /* Create URL Section */
    #create-url {
        background: #f9f9f9;
        padding: 25px;
        border-radius: 12px;
        box-shadow: 0 3px 6px rgba(0, 0, 0, 0.1);
        max-width: 450px;
        margin: 25px auto;
        text-align: center;
    }

    #create-url h2 {
        color: #333;
        font-size: 1.4em;
        margin-bottom: 20px;
    }

    #create-url .form-group {
        margin-bottom: 15px;
        text-align: left;
    }

    #create-url label {
        font-weight: 600;
        display: block;
        margin-bottom: 5px;
        color: #555;
    }

    #create-url input[type="url"] {
        width: 100%;
        padding: 12px;
        border: 1px solid #ddd;
        border-radius: 8px;
        font-size: 1em;
        transition: border 0.3s ease-in-out;
    }

    #create-url input[type="url"]:focus {
        border-color: #007bff;
        outline: none;
    }

    #create-url button {
        width: 100%;
        padding: 12px;
        background-color: #007bff;
        color: white;
        border: none;
        border-radius: 8px;
        font-size: 1.1em;
        font-weight: 500;
        cursor: pointer;
        transition: background 0.3s ease-in-out;
    }

    #create-url button:hover {
        background-color: #0056b3;
    }

    /* Responsive Design */
    @media (max-width: 768px) {
        .dashboard-container {
            flex-direction: column;
            height: auto;
        }

        .sidebar {
            width: 100%;
            text-align: center;
            padding: 15px;
        }

        .sidebar ul {
            display: flex;
            justify-content: space-around;
            padding: 0;
        }

        .sidebar ul li {
            display: inline;
        }

        .content {
            padding: 15px;
            margin: 10px;
        }

        #create-url {
            max-width: 100%;
            padding: 15px;
        }
    }
    .copy-icon, .more-icon {
        cursor: pointer;
        margin: 0 8px;
        color: #555;
        transition: color 0.3s ease-in-out;
    }

    .copy-icon:hover {
        color: #2196F3;
    }

    .more-icon:hover {
        color: #666;
    }


</style>
<body>
<div th:if="${isDashboardPage}" class="dashboard-container">
    <div class="sidebar">
        <h3>Dashboard</h3>
        <ul>
            <li><a href="#overview">Overview</a></li>
            <li><a href="#url-list">URLs</a></li>
            <li><a href="CreateUrl">Create URL</a></li>
        </ul>
    </div>

    <div class="content">
        <section id="overview">
            <h2>Overview</h2>
            <p>Total URLs: <span th:text="${totalUrls}"></span></p>
            <p>Clicks: <span th:text="${totalClicks}"></span></p>
            <p>Active URLs: <span th:text="${activeUrls}"></span></p>
        </section>

        <section id="url-list">
            <h2>Your URLs</h2>
            <table>
                <thead>
                <tr>
                    <th>Short URL</th>
                    <th>Original URL</th>
                    <th>Clicks</th>
                    <th>Date Created</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                    <c:forEach items="${urlsListJsp}" var="url">
                        <tr>
                            <td>${url.short_URL}</td>
                            <td>${url.original_URL}</td>
                            <td>${url.number_of_visits}</td>
                            <td><fmt:formatDate value="${url.date_Created}" pattern="yyyy-MM-dd"/> </td>

                            <td>
                                <i class="fas fa-copy copy-icon" onclick="copyToClipboard('short.ly/abc123')" title="Copy"></i>
                                <i class="fas fa-ellipsis-h more-icon" title="More Options"></i>
                            </td>

                        </tr>
                    </c:forEach>
                </tr>
                    <td>short.ly/abc123</td>
                    <td>https://example.com/article-1</td>
                    <td>45</td>
                    <td>2025-02-01</td>
                    <td>
                        <i class="fas fa-copy copy-icon" onclick="copyToClipboard('short.ly/abc123')" title="Copy"></i>
                        <i class="fas fa-ellipsis-h more-icon" title="More Options"></i>
                    </td>
                </tr>
                <tr>
                    <td>short.ly/xyz789</td>
                    <td>https://example.com/product-page</td>
                    <td>23</td>
                    <td>2025-02-02</td>
                    <td>
                        <i class="fas fa-copy copy-icon" onclick="copyToClipboard('short.ly/abc123')" title="Copy"></i>
                        <i class="fas fa-ellipsis-h more-icon" title="More Options"></i>
                    </td>
                </tr>
                </tbody>
            </table>
        </section>
    </div>
</div>

<script>
    function copyToClipboard(text) {
        navigator.clipboard.writeText(text).then(() => {
            alert("Copied: " + text);
        });
    }
</script>
</body>
</html>
