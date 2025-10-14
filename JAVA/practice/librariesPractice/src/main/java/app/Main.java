package app;

import controller.BookController;
import controller.LoanController;
import controller.MemberController;
import controller.UserController;
import dao.impl.*;
import service.impl.BookServiceImpl;
import service.impl.LoanServiceImpl;
import service.impl.MemberServiceImpl;
import service.impl.UserServiceImpl;
import view.*;

public class Main {
    public static void main(String[] args) {
        // 1. DAO
        BookDaoImpl bookDao = new BookDaoImpl();
        MemberDaoImpl memberDao = new MemberDaoImpl();
        UserDaoImpl userDao = new UserDaoImpl();
        StatusDaoImpl statusDao = new StatusDaoImpl();
        LoanDaoImpl loanDao = new LoanDaoImpl();

        // 2. Service
        BookServiceImpl bookService = new BookServiceImpl(bookDao);
        MemberServiceImpl memberService = new MemberServiceImpl(memberDao);
        UserServiceImpl userService = new UserServiceImpl(userDao);
        LoanServiceImpl loanService = new LoanServiceImpl(loanDao, bookDao, memberDao, userDao, statusDao);

        // 3. Controller
        BookController bookController = new BookController(bookService);
        LoanController loanController = new LoanController(loanService);
        MemberController memberController = new MemberController(memberService);
        UserController userController = new UserController(userService);

        // 4. View - ¡ESTA ES LA PARTE CORREGIDA!
        // Vistas de entidades específicas
        BookView bookView = new BookView(bookController);
        LoanView loanView = new LoanView(loanController);

        MemberView memberView = new MemberView(memberController, loanController, bookView);
        UserView userView = new UserView(userController);

        // Vistas de menús que usan las vistas de entidades
        AdminMenuView adminMenuView = new AdminMenuView(bookView, loanView, memberView);

        // El MainView es el menú de más alto nivel
        MainView mainView = new MainView(userView, adminMenuView, memberView);

        // --- INICIO DE LA APLICACIÓN ---
        mainView.start(); // Se llama al método que inicia el menú principal
    }
}