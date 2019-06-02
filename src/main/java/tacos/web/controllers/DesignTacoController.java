package tacos.web.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tacos.model.Ingredient;
import tacos.model.Taco;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static tacos.model.Ingredient.Type;
import static tacos.model.Ingredient.Type.*;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {

    @GetMapping
    public String showDesignForm(Model model) {
        List<Ingredient> ingredients = Arrays.asList(
                Ingredient.builder()
                        .id("FLTO")
                        .name("Flour Tortilla")
                        .type(WRAP)
                        .build(),
                Ingredient.builder()
                        .id("COTO")
                        .name("Corn Tortilla")
                        .type(WRAP)
                        .build(),
                Ingredient.builder()
                        .id("GRBF")
                        .name("Ground Beef")
                        .type(PROTEIN)
                        .build(),
                Ingredient.builder()
                        .id("CARN")
                        .name("Carnitas")
                        .type(PROTEIN)
                        .build(),
                Ingredient.builder()
                        .id("TMTO")
                        .name("Diced Tomatoes")
                        .type(VEGGIES)
                        .build(),
                Ingredient.builder()
                        .id("LETC")
                        .name("Lettuce")
                        .type(VEGGIES)
                        .build(),
                Ingredient.builder()
                        .id("CHED")
                        .name("Cheddar")
                        .type(CHEESE)
                        .build(),
                Ingredient.builder()
                        .id("JACK")
                        .name("Pepper Jack")
                        .type(CHEESE)
                        .build(),
                Ingredient.builder()
                        .id("SLSA")
                        .name("Salsa")
                        .type(SAUCE)
                        .build(),
                Ingredient.builder()
                        .id("SRCR")
                        .name("Sour Cream")
                        .type(SAUCE)
                        .build()
        );

        Type[] types = values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }

        model.addAttribute("design", new Taco());

        return "design";
    }

    @PostMapping
    public String processDesign(@Valid Taco design, Errors errors){

        if(errors.hasErrors()){
            return "design";
        }

        log.info("Processing design:" + design);

        return "redirect:/orders/current";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Ingredient.Type type) {
        return ingredients
                .stream()
                .filter(ingredient -> ingredient.getType().equals(type))
                .collect(Collectors.toList());
    }
}

