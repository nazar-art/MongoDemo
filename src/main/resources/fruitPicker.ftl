<html>
<head>
    <title>Fruit Piker</title>
</head>
<body>
<form action="/favorite_fruit" method="post">
    <p>What is your favrite fruit?</p>
<#list fruits as fruit>
    <p>
        <input type="radio" name="fruit" value="${fruit}">${fruit}</input>
    </p>
</#list>
    <input type="submit" value="Submit">
</form>
</body>
</html>