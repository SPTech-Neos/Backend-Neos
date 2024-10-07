package school.sptech.neosspringjava.utils.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NaoEncontradoException extends RuntimeException{


    public NaoEncontradoException(String data) {
      super(String.format("Não foi possível encontrar %s", data));
    }
}
