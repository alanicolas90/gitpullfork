package dao;


import modelo.Clone;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

abstract class DaoBase {

    protected <T> List<T> dameListaClonadaInmutable(Collection<? extends Clone<T>> collection){
        return collection.stream()
                .map(Clone::clone)
                .collect(Collectors.toUnmodifiableList());
    }

    protected <T> T dameElementoClonado(Clone<T> t){
        return t.clone();
    }

}
