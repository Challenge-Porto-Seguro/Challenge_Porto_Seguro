package com.example.db.dao;

import com.example.model.Automovel;

import java.util.List;
import java.util.Optional;

public interface AutomovelDao {

    void insert(Automovel automovel);
    void update(Automovel automovel);
    void deleteById(long id);
    Optional<Automovel> findById(long id);
    List<Automovel> findAll();
}
