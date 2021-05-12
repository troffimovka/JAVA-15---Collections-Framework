package ru.netology.exceptions;

//выбрасывает исключение в методе findByID

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }

}
