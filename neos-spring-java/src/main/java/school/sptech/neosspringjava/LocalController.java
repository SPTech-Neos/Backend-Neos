package school.sptech.neosspringjava;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/Local")
public class LocalController {
    List<Local> locals = new ArrayList<>();

    @GetMapping("/")
    public ResponseEntity <List<Local>> getAllLocal() {
        if (locals.isEmpty()){return ResponseEntity.status(204).build();} else {
        return ResponseEntity.status(200).body(locals);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Local> getLocalById(@PathVariable int id) {
        if (locals.isEmpty()){return ResponseEntity.status(204).build();} else
        if (id >= 0 && id < locals.size()) {
            return ResponseEntity.status(200).body(locals.get(id));
        } else {
            return  ResponseEntity.status(404).build();
        }
    }

        @PostMapping("/")
        public ResponseEntity<Local> createLocal(@RequestBody Local local) {
            Local show = null;
            int finish = 0;

            for (int i = 0; i < locals.size(); i++) {
                show = locals.get(i);
                if (locals.get(i).equals(local)) {
                    finish++;
                    break;
                }
            }

            if (finish > 0) {
                return ResponseEntity.status(200).body(show);
            } else {
                locals.add(local);
                return ResponseEntity.status(201).body(local);
            }
        }

    @PutMapping("/id")
    public ResponseEntity<List<Local>> updateLocal(@PathVariable int id, @RequestBody Local local){
        if (locals.isEmpty()){return ResponseEntity.status(204).build();} else
        if (id >= 0 && id < locals.size()) {
            List<Local> oldAndNewLocal = new ArrayList<>();
            oldAndNewLocal.add(locals.get(id));
            oldAndNewLocal.add(local);
            return ResponseEntity.status(200).body(oldAndNewLocal);
        } else {
            return  ResponseEntity.status(404).build();
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Local> deleteLocal (@PathVariable int id) {
        if (id >= 0 && id < locals.size()) {
            return ResponseEntity.status(200).body(locals.remove(id));
        }else{ return ResponseEntity.status(204).build();
        }
    }
}
