package com.korit.servlet_study.servlet;

import com.korit.servlet_study.datas.B_DataList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

// http://localhost:8080/servlet_study/product/register (GET)
@WebServlet("/product/register")
public class C_ProductRegisterPageServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        servletContext.setAttribute("serverName", "서블릿 학습"); //Application
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("상품으록 페이지 doGet 호출");
        HttpSession session = req.getSession();
        session.setAttribute("username", "junil"); //Session

        req.setAttribute("categories", B_DataList.getCategoryList()); //Request
        req.getRequestDispatcher("/WEB-INF/c_product_register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getParameter("category"));
        System.out.println(req.getParameter("productName"));
        System.out.println(req.getParameter("price"));
        System.out.println(req.getParameter("registerDate"));
        resp.setContentType("application/json"); //응답 타입 설정
        resp.setStatus(200);
        resp.getWriter().println("{\"name\" : \"김\"}");
//        resp.getWriter().println(
//                "<script>"
//                        + "alert(\"등록이완료되었습니다.\")"
//                        + "location.href='http://localhost:8080/servlet_study_war/product/register';"
//                        + "</script>"
//        );
        //doGet(req,resp);
    }


}
