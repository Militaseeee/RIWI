package util;

import java.util.List;
import java.util.function.Function;

public class FindItems {

    public static <T> T findIdByString(List<T> inputs, String value, Function<T,String> extractMethodString){
        return  inputs.stream().filter(input -> extractMethodString.apply(input).equals(value))
                .findFirst()
                .orElse(null);
    }
}
