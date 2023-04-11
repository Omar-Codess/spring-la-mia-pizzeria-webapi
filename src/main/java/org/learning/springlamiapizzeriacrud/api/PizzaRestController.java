package org.learning.springlamiapizzeriacrud.api;

import jakarta.validation.Valid;
import org.learning.springlamiapizzeriacrud.exceptions.PizzaNotFoundException;
import org.learning.springlamiapizzeriacrud.models.Pizza;
import org.learning.springlamiapizzeriacrud.models.Sale;
import org.learning.springlamiapizzeriacrud.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/pizzas")
public class PizzaRestController {
    @Autowired
    private PizzaService pizzaService;

    //lista di tutte le pizze
    @GetMapping
    public List<Pizza> list(@RequestParam(name = "q") Optional<String> search) {
        if (search.isPresent()) {
            return pizzaService.getFilteredPizzas(search.get());
        }
        return pizzaService.getAllPizzas();
    }

    //singola pizza
    @GetMapping("/{id}")
    public Pizza getById(@PathVariable Integer id) {
        try {
            return pizzaService.getById(id);
        } catch (PizzaNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    // create pizza
    @PostMapping
    public Pizza create(@Valid @RequestBody Pizza pizza) {
        return pizzaService.createPizza(pizza);
    }

    //update pizza
    @PutMapping("/{id}")
    public Pizza update(@PathVariable Integer id, @Valid @RequestBody Pizza pizza){
        try {
            return pizzaService.updatePizza(pizza, id);
        } catch (PizzaNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    //delete pizza
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        try {
            boolean success = pizzaService.deleteById(id);
            if (!success) {
                throw new ResponseStatusException(HttpStatus.CONFLICT,
                        "Unable to delete pizza because it has sales");
            }
        } catch (PizzaNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    // lista di tutte le offerte
    @GetMapping("/{id}/sales")
    public List<Sale> getPizzaSale(@PathVariable("id") Integer pizzaId) {
        Pizza pizza = null;
        try {
            pizza = pizzaService.getById(pizzaId);
        } catch (PizzaNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return pizza.getSales();
    }
}
