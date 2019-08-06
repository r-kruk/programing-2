package com.github.rkruk.hashset;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StringHashSet implements Set<String> {

    private List<List<String>> buckets;    // to jest tablica list
    private int currentSize = 0;

    public StringHashSet() {
        buckets = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            buckets.add(new ArrayList<>());
        }
    }

    @Override
    public int size() {
        int counter = 0;
        for (int i = 0; i < this.buckets.size(); i++) {
            List<String> bucket = this.buckets.get(i);

            counter = counter + bucket.size();
        }
        return counter;

    }

    @Override
    public boolean isEmpty() {

        return this.currentSize == 0;
        // powyzej wersja zoptymalizowana

//        boolean include = false;
//
//        for (int i = 0; i < this.buckets.size(); i++) {
//            if (this.buckets == null) {
//                include = true;
//            }
//        }
//        return include;
    }

    @Override
    public boolean contains(Object o) {
        int hash = o.hashCode();
        int index = Math.abs(hash % this.buckets.size());
        List<String> bucket = this.buckets.get(index);
        return bucket.contains(o);    // to zwraca true lub false
    }


    @Override
    public Iterator<String> iterator() {
        return null;
    }

    @Override
    public void forEach(Consumer<? super String> action) {

    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(String potentialNumber) {
        int hash = potentialNumber.hashCode();
        int index = Math.abs(hash % this.buckets.size());
        List<String> bucket = this.buckets.get(index);
        if (bucket.contains(potentialNumber)) {
            return false;
        }

        bucket.add(potentialNumber);
        currentSize++;
        return true;

// ponizsza linijka była oryginalnie
//        this.buckets.get(index).add(potentialNumber);
        //   dodac kod zapewniajacy brak powtorzen
        // tak to dodał Adam - patrz wyzej - pozniej poprzesuwalem

    }

    @Override
    public boolean remove(Object o) {

        boolean removed = false;

        int hash = o.hashCode();
        int index = Math.abs(hash % this.buckets.size());
        List<String> bucket = this.buckets.get(index);
        return bucket.remove(o);      // tutaj zastosowano delegowanie tj. stosuje metode remove, ktora jest juz dostepna w klasie List

//        for (int i = 0; i < bucket.size(); i++) {
//            if (this.buckets.get(i).equals(o)) {
//                this.buckets.get(i).clear();
//                return true;       // co wtedy jesli taki obikt bedzie wystepowal kilka razy
//            }
//        }
//        return false;
    }



    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends String> incommingStrings) {
        boolean changed = false;
        for (String s : incommingStrings) {
            boolean result = this.add(s);
            currentSize++;
            changed = true;
        }

        return changed;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeIf(Predicate<? super String> filter) {
        return false;
    }

    @Override
    public void clear() {

        for (List<String> bucket : this.buckets) {
            bucket.clear();
        }
        return;
    }

    @Override
    public Spliterator<String> spliterator() {
        return null;
    }

    @Override
    public Stream<String> stream() {
        return null;
    }

    @Override
    public Stream<String> parallelStream() {
        return null;
    }

    @Override
    public String toString() {
        return this.buckets.stream()
                .flatMap(bucket -> bucket.stream())
                .collect(Collectors.joining(", "));
    }
}
