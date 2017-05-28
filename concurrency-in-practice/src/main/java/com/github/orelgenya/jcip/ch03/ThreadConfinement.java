package com.github.orelgenya.jcip.ch03;

import java.util.Collection;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * @author OrelGenya
 */
public class ThreadConfinement {
    final Ark ark = new Ark();

    public int loadTheArk(Collection<Animal> candidates) {
        SortedSet<Animal> animals;
        int numPairs = 0;
        Animal candidate = null;

        // animals confined to method, don't let them escape!
        animals = new TreeSet<Animal>(new SpeciesGenderComparator());
        animals.addAll(candidates);
        for (Animal a : animals) {
            if (candidate == null || !candidate.isPotentialMate(a))
                candidate = a;
            else {
                ark.load(new AnimalPair(candidate, a));
                ++numPairs;
                candidate = null;
            }
        }
        return numPairs;
    }

    class Animal {
        public boolean isPotentialMate(Animal a) {
            return a.hashCode() % 2 == 0;
        }
    }

    class SpeciesGenderComparator implements Comparator<Animal> {

        @Override
        public int compare(Animal o1, Animal o2) {
            if (o1 == o2) return 0;
            if (o1 == null) return -1;
            if (o2 == null) return 1;
            return o2.hashCode() - o1.hashCode();
        }
    }

    class AnimalPair {
        final Animal a, b;

        AnimalPair(Animal a, Animal b) {
            this.a = a;
            this.b = b;
        }
    }

    class Ark {
        void load(AnimalPair pair) {}
    }
}
