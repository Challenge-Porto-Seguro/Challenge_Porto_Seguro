package com.example.service;

import com.example.db.dao.AutomovelDao;
import com.example.db.dao.DaoFactory;
import com.example.model.Automovel;

public class AutomovelService {

    private final AutomovelDao repository = DaoFactory.createAutomovelDao();

    public void cadastraAutomovel(Automovel automovel) {
        repository.insert(automovel);
    }

    public void alteraAutomovel(Automovel automovel) {
        repository.update(automovel);
    }

    public void excluiAutomovel(Long id) {
        repository.deleteById(id);
    }

    public Automovel buscaAutomovelPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Automovel não encontrado"));
    }

}
