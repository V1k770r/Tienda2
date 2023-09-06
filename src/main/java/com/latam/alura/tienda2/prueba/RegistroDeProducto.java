package com.latam.alura.tienda2.prueba;

import com.latam.alura.tienda2.dao.CategoriaDao;
import com.latam.alura.tienda2.dao.ProductoDao;
import com.latam.alura.tienda2.modelo.Categoria;
import com.latam.alura.tienda2.modelo.Producto;
import com.latam.alura.tienda2.utils.JPAUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.math.BigDecimal;

public class RegistroDeProducto {
    public static void main(String[] args) {

        Categoria celulares = new Categoria("Celulares");

        EntityManager em = JPAUtils.getEntityManager();

        em.getTransaction().begin();

        em.persist(celulares);

        celulares.setNombre("LIBROS");

        //comit envia los valores de esa instancia a la base de datos
        em.flush();
        em.clear();

        celulares = em.merge(celulares);
        celulares.setNombre("SOFTWARES");

        em.flush();
        em.clear();
        em.remove(celulares);
        em.flush();

    }

}
