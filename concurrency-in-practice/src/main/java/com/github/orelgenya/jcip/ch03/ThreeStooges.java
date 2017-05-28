package com.github.orelgenya.jcip.ch03;

import com.github.orelgenya.jcip.annotations.Immutable;

import java.util.HashSet;
import java.util.Set;

/**
 * @author OrelGenya
 */
@Immutable
public final class ThreeStooges {
    private final Set<String> stooges = new HashSet<>();
    public ThreeStooges() {
        stooges.add("Moe");
        stooges.add("Larry");
        stooges.add("Curly");
    }
    public boolean isStooge(String name) {
        return stooges.contains(name);
    }
}
