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
import java.util.List;

public class RegistroDeProducto {
    public static void main(String[] args) {

        //metodo abstracto
        registrarProducto();
        EntityManager em = JPAUtils.getEntityManager();
        ProductoDao productoDao = new ProductoDao(em);
        Producto producto = productoDao.consultaPorId(1l);
        System.out.println(producto.getNombre());

        BigDecimal precio = productoDao.consultarPrecioPorNombreDeProducto("Xiaomi Redmi");
        System.out.println(precio);

        //productos.forEach(prod -> System.out.println(prod.getDescripcion()));


    }

    private static void registrarProducto() {
        Categoria celulares = new Categoria("CELULARES");

        Producto celular = new Producto("Xiaomi Redmi", "Muy bueno", new BigDecimal("800"),celulares);

        EntityManager em = JPAUtils.getEntityManager();
        ProductoDao productoDao = new ProductoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);

        em.getTransaction().begin();

        categoriaDao.guardar(celulares);
        productoDao.guardar(celular);

        em.getTransaction().commit();
        em.close();
    }

}
