//package com.Sidhu.journalApp.Service;
//
//import com.Sidhu.journalApp.Entity.User;
//import org.junit.jupiter.api.extension.ExtensionContext;
//import org.junit.jupiter.params.provider.Arguments;
//import org.junit.jupiter.params.provider.ArgumentsProvider;
//
//import java.util.stream.Stream;
//
//public class UserArguments implements ArgumentsProvider {
//
//    @Override
//    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
//        return Stream.of(
//                Arguments.of(User.builder().userName("Darshan").password("Darshan").build()),
//                Arguments.of(User.builder().userName("Prajwal").password("").build())
//        );
//    }
//}
