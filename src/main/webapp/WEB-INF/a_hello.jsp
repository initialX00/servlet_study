<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2025-01-03
  Time: 오후 5:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--해당태그가 html코드를 setvlet으로 변환시켜준다--%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <% %> <!-- 스크립틀릿 : html에 자바코드를 적어 넣을 수 있는 태그 -->
    <%
        //String name = "bbb";
        //int age = 32;
    %>

    <%
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age")); //스트링으로만 값을 얻기에 형변환시켜준다
        //입력된 값이 없기 때문에 값을 입력해준다
        //html 주소에서 ? key=value를 입력할 수 있다. &로 여러개를 입력할 수 있다
        //입력된 값이 없기때문에 오류가 뜬다, 값을 입력해주자
    %>

    <h1>Hello Servlet</h1>
    <h2>이름: <%=name %></h2> <!-- 스크립트릿에 =를 통해 변수를 불러올 수 있다-->
    <h2>나이: <%=age %></h2>
<!--f12로 html 소스에서 jsp파일 다운이 가능하여 보안에 취약하다
    외부에서 접근이 불가한 WEB-INF를 활용하여 접근한다.  -->


</body>
</html>
