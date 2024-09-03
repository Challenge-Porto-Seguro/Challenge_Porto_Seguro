package com.example.dao;

import com.example.domain.Automovel;

import java.util.List;
import java.util.Optional;

public interface AutomovelDao {

    void insert(Automovel automovel);
    void update(Automovel automovel);
    void deleteById(int id);
    Optional<Automovel> findById(int id);
}
