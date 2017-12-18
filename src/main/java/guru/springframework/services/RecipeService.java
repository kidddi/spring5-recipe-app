package guru.springframework.services;

import guru.springframework.domain.Recipe;

import java.util.Set;

/**
 * Created by oleksandr.kydiuk on 12/18/2017.
 */
public interface RecipeService {
    public Set<Recipe> getRecipes();
}
