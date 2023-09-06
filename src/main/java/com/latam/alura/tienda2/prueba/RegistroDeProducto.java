package com.latam.alura.tienda2.prueba;

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

        Producto celular = new Producto("Sansung","telefono usado",new BigDecimal("1000"), Categoria.CELULARES);


        EntityManager em = JPAUtils.getEntityManager();

        ProductoDao productoDao = new ProductoDao(em);


        em.getTransaction().begin();

        productoDao.guardar(celular);


        //comit envia los valores de esa instancia a la base de datos
        em.getTransaction().commit();
        em.close();






    }

}
