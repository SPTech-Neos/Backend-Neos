package school.sptech.neosspringjava.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ConflitoException extends RuntimeException{

    public ConflitoException(String data) {
      super(String.format("%s com conflito ", data));
    }
}
