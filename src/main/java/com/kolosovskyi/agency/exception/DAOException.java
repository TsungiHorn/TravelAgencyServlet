package com.kolosovskyi.agency.exception;

public class DAOException extends RuntimeException{

}
//        да, зроби DaoException extends RuntimeException і кидай його кожен раз
//        компілятор не буде скаржитись якщо ти не обгорнеш цей метод і тому коли він випаде то користувач побачить 500
