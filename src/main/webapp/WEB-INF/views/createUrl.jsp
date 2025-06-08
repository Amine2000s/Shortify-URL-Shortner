


<!DOCTYPE html>
<html >
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<style>
    /* General Styling */
    body {
        font-family: 'Poppins', sans-serif;
        background-color: #f4f4f4;
        margin: 0;
        padding: 0;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
    }

    /* Create URL Section */
    #create-url {
        background: white;
        padding: 25px;
        border-radius: 12px;
        box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
        max-width: 400px;
        width: 100%;
        text-align: center;
    }

    #create-url h2 {
        margin-bottom: 20px;
        color: #333;
    }

    /* Form Styling */
    .form-group {
        margin-bottom: 15px;
        text-align: left;
    }

    .form-group label {
        display: block;
        font-weight: 600;
        margin-bottom: 5px;
        color: #555;
    }

    .form-group input {
        width: 100%;
        padding: 10px;
        border: 1px solid #ddd;
        border-radius: 6px;
        font-size: 16px;
        transition: border 0.3s ease-in-out;
    }

    .form-group input:focus {
        border-color: #2196F3;
        outline: none;
    }

    /* Button Styling */
    button {
        width: 100%;
        padding: 12px;
        background-color: #1f7ad2;
        color: white;
        border: none;
        border-radius: 6px;
        font-size: 16px;
        cursor: pointer;
        transition: background 0.3s;
    }

    button:hover {
        background-color: #45a049;
    }

    /* Responsive Design */
    @media (max-width: 600px) {
        #create-url {
            width: 90%;
        }
    }




</style>
<body>

    <section id="create-url">
        <h2>Create a Short URL</h2>
        <form th:action="addUrl" method="post">
            <div class="form-group">
                <label for="name">name </label>
                <input type="text" id="name" name="name" required />
            </div>
            <div class="form-group">
                <label for="originalUrl">Original URL</label>
                <input type="url" id="originalUrl" name="original_URL" required />
            </div>

            <div class="form-group">
                <button type="submit" value="Save">Shorten URL</button>
            </div>
        </form>
        <a href="Home">UrlList</a>

    </section>

</body>
</html>
