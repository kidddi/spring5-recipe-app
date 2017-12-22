package guru.springframework.services;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.converters.IngredientToIngredientCommand;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by oleksandr.kydiuk on 12/22/2017.
 */
@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final RecipeRepository recipeRepository;

    public IngredientServiceImpl(IngredientToIngredientCommand ingredientToIngredientCommand, RecipeRepository recipeRepository) {
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public IngredientCommand findCommandByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if (!recipeOptional.isPresent()){
            //todo impl error handling
            log.error("recipe id not found. ID: " + recipeId);
        }

        Recipe recipe = recipeOptional.get();

//        old school method
        IngredientCommand ingredientCommand = new IngredientCommand();
        for (Ingredient ingredient : recipe.getIngredients()){
            if (ingredient.getId().equals(ingredientId)){
                 ingredientCommand = ingredientToIngredientCommand.convert(ingredient);
            }
        }

//        Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredients().stream()
//                .filter(ingredient -> ingredient.getId().equals(ingredientId))
//                .map(ingredientToIngredientCommand::convert).findFirst();
//
//        if (!ingredientCommandOptional.isPresent()){
//            todo impl error handling
//            log.error("ingredient id not found. ID:" + ingredientId);
//        }

        return ingredientCommand;
    }
}
