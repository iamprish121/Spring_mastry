package jornalApp.Service;

import journalApp.Entity.UserEntry;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class UserArgumentsProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        return Stream.of(
                Arguments.of(UserEntry.builder().userName("test").password("test").build()),
                Arguments.of(UserEntry.builder().userName("test1").password("").build())
                );
    }
}
