package com.latam.alura.tienda2.dao;

import com.latam.alura.tienda2.modelo.Categoria;
import com.latam.alura.tienda2.modelo.Producto;

import javax.persistence.EntityManager;

public class CategoriaDao {

    private EntityManager em;

    public CategoriaDao(EntityManager em) {
        this.em = em;
    }

    public void guardar(Categoria categoria){
        this.em.persist(categoria);
    }

    public void actualizar(Categoria categoria){
        this.em.merge(categoria);
    }

}