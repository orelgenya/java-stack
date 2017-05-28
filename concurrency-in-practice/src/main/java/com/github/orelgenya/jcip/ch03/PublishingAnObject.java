package com.github.orelgenya.jcip.ch03;

import com.github.orelgenya.jcip.annotations.NotThreadSafe;

import java.util.HashSet;
import java.util.Set;

/**
 * @author OrelGenya
 */
@NotThreadSafe
public class PublishingAnObject {
    public static Set<Secret> knownSecrets;

    public void initialize() {
        knownSecrets = new HashSet<>();
    }

    public class Secret {}
}
