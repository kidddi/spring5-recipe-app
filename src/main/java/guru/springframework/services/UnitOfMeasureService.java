package guru.springframework.services;

import guru.springframework.commands.UnitOfMeasureCommand;

import java.util.Set;

/**
 * Created by oleksandr.kydiuk on 12/26/2017.
 */
public interface UnitOfMeasureService {

    public Set<UnitOfMeasureCommand> listOfAllUom();
}
